package entity.mob;

import com.sun.media.sound.AiffFileReader;
import entity.Combat.Projectile;
import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.sprites.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static entity.handler.Handler.entities;

public class Enemy extends Entity {
    private int timer;
    private BufferedImage skin;
    Random random = new Random(); //Define a variable named "random" containing an object of the type random using its default Constructor
    private int choose;


    public Enemy() {}
    
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }
    
    public Enemy(int x, int y, ID id, Handler handler, String name, Spritesheet ss) {
        super(x, y, id, handler, name, ss);
        health = 100;
        timer = 0;
        damage = 5;
        skin = ss.grabImage(4,1,32,32);
    }

    public void tick() { //Update movement where velocity is the speed
            this.timer++;
            x += velocity_X;
            y += velocity_Y;
    
            AI_Move_Random();
            collision();
            

        }

    private void collision() {
        for(Entity e : entities) {
            if(e.getId() == ID.Block) { //Collides with wall
                if(getBoundsWithPadding().intersects(e.getBoundsWithPadding())) {
                    x += (velocity_X * 2) * -1;
                    y += (velocity_Y * 2) * -1;
                    velocity_X = 0;
                    velocity_Y = 0;
                }
            }
/*            else if(e.getId() == ID.Enemy && e != this) {
                if(getBounds().intersects(e.getBounds())) {
                    x += (velocity_X * 2) * -1;
                    y += (velocity_Y * 2) * -1;
                    velocity_X = 0;
                    velocity_Y = 0;
                }
            } */
            else if(e.getId() == ID.Projectile) { //Getting hit by a projectile: take away HP and "die" (remove from existing entities List)
                int d = e.getDamage(); //Set damage of the incoming projectile
                if((getBounds().intersects(e.getBounds()) && (this.getHealth() <= e.damage))) {
                    handler.removeEntity(this);
                    handler.removeEntity(e);
                    System.out.println("Player killed " + this.getName() + "\n");
                } else if(getBounds().intersects(e.getBounds())) { //Enemy getting hit by player
                    System.out.println("Hit: " + this.getName() + " for " + d + " damage! Remaining: " + this.getHealth());
                    this.setHealth(this.getHealth() - d);
                    handler.removeEntity(e);
                } else if(e.getId() == ID.Player) {
                    if(getBounds().intersects(e.getBoundsCore())) {
                        e.setHealth(e.getHealth() - this.getDamage());
                        System.out.println("You've bin hit by: " + this.getName() + " for " + d + " damage! Remaining: " + e.getHealth());
                    }
                }
            }
        }
    }
    
    public void AI_Move_Random() {
        choose = random.nextInt(30);
        if(choose == 0) {
            velocity_X = (random.nextInt(4 - -4) + -4);
            velocity_Y = (random.nextInt(4 - -4) + -4);
        }
    }
    //TODO: Build actual logic for enemy to notice, locate and shoot in the direction of the player
    public void AI_Shoot_Random(int timer, double dir) {
        for(Entity en : entities) {
            timer++;
            if(en.getId() == ID.Enemy) {
                handler.addEntity((new Projectile(en.getX()+16, en.getY()+24, ID.Projectile, handler, dir)));
            } if(timer > 100) {
                timer = 0;
            }
        }
    }

    public void render(Graphics g) {

/*      g.setColor(Color.RED);
        g.fillRect(x,y,33,45);
        
        g.setColor(Color.RED);
        g2d.draw(getBoundsWithPadding());*/
        g.drawImage(skin, x, y, null);
    }
    
    public String getName() { return name; }

    public Rectangle getBounds() { return new Rectangle(x,y,32,32); }
    
    public Rectangle getBoundsWithPadding() { return new Rectangle(x -16 ,y -16 ,60,80); }

    public Rectangle getBoundsCore() { return new Rectangle(x,y,4,4); }

    public int getHealth() { return health; }
    public void setHealth(int h) { this.health = h; }
}
