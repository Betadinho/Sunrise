package entity.terrain;

import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.sprites.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends Entity {
    
    private BufferedImage block_Image;


    public Block(int x, int y, ID id, Handler handler, Spritesheet s_S) {
        super(x, y, id, handler, s_S);
    
        block_Image = s_S.grabImage(5,2,32,32);
    }
    
    public void tick() {
    
    }
    
    public void render(Graphics g) {
/*        g.setColor(Color.BLACK);
        g.fillRect(x, y,32,32);
    
        Graphics2D g2d = (Graphics2D) g; //draw bounds with padding
        g.setColor(Color.BLACK);
        g2d.draw(getBoundsWithPadding());*/

        g.drawImage(block_Image, x , y, null);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public Rectangle getBoundsWithPadding() { return new Rectangle(x-8,y-8, 38,38); }

    public Rectangle getBoundsCore() { return new Rectangle(x,y,4,4); }
}
