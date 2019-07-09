package game;

import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.mob.Enemy;
import entity.mob.Wizard;
import entity.terrain.Block;
import gameUtils.BufferedImageLoader;
import input.KeyInput;
import input.MouseInput;
import entity.sprites.Spritesheet;
import view.Camera;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static entity.handler.Handler.entities;

public class Game extends Canvas implements Runnable{
    
    private static final Long serialVersionUID = 1L ;
    
    public static final float width = 1200;
    public static final float height = (int)(width / 1.8);
    private  static final String title = "Sunrise";
    
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private BufferedImage level;
    private Spritesheet ss;
    private BufferedImage sprite_sheet;
    private BufferedImage floor;

    
    private Game() {
        new Window(width, height, title, this);
        SoundThread bgm_E = new SoundThread("/audio/Ellipse.wav");
        bgm_E.run();
        start();
    
        handler = new Handler();
        camera = new Camera(0,0);
        this.addKeyListener(new KeyInput(handler));
        MouseInput mouse = new MouseInput(handler, camera);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/sprite_sheet.png"); //Load sprite sheet
        ss = new Spritesheet(sprite_sheet); //Initialize sprite_sheet
        floor = ss.grabImage(4,2,32,32);
        
        level = loader.loadImage("/test_level.png"); //Load level
        loadLevel(level);
    }

    private synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace(); //TODO: ADO logging Functions as done in Project "Dawn"
        }
    }
    
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while(delta >= 1) {
                synchronized(entities) {
                    tick();
                    
                }
                updates++;
                delta--;
            }
            render();
            frames++;
            //System.out.println(frames); //TODO: Add frame counter to window top bar or whatever its called
        
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }
    
    private void tick() {
        handler.tick();
        for(Entity e : entities) {
            if(e.getId() == ID.Player){
                camera.tick(e);
            }
        }
        
        if(handler.playerIsDead()) {
            stop();
            //TODO: Add actual handling for end of game e.g. option to restart or even just reset automatically
        }
    }
    
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        
        //----------------------- Draw stuff
        {
            
            
            g2d.translate(-camera.getX(), -camera.getY());
    
            for(int xx = 0; xx < 30*72; xx +=32) {
                for(int yy = 0; yy < 30*72; yy +=32) {
                    g.drawImage(floor, xx, yy, null);
                }
            }
            
            handler.render(g);
            
            g2d.translate(camera.getX(), camera.getY());
            //----------------------- Draw stuff
            
            //Mouse input test TODO: Remove
            //g.setColor(Color.WHITE);
            //g.drawString("Button:" + MouseInput.getMouseB(),80,80);
            //g.fillRect(MouseInput.getMouseX()-16, MouseInput.getMouseY()-16, 32,32);
        }
        g.dispose();
        bs.show();
    }
    
    //Load Level from image and add entities based on predetermined colors representing said entities (Green: Wall, Blue: the player, green, enemies)
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int enemyCounter = 0;
        //Loop through the given map image row by row, column by column
        for(int xx = 0; xx < w; xx++) {
            for(int yy = 0; yy < h; yy++) {
                //current pixel
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(red == 255 && blue == 0 && green == 0) { handler.addEntity(new Block(xx*32, yy*32, ID.Block, handler, ss)); }
                if(blue == 255 && green == 0 && red == 0) { handler.addEntity(new Wizard(xx*32, yy*32, ID.Player, handler, ss)); }
                if(green == 255 && red == 0 && blue == 0) {
                    handler.addEntity(new Enemy(xx*32, yy*32, ID.Enemy, handler, "test"+enemyCounter, ss));
                    enemyCounter++; //Count and tag every enemy with a number
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}
