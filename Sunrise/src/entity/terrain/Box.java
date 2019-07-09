package entity.terrain;

import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.sprites.Spritesheet;

import java.awt.*;

import static java.awt.Color.BLUE;

public class Box extends Entity {

    public Box(int x, int y, ID id, Handler handler, Spritesheet ss) {
        super(x, y, id, handler, ss);
        velocity_X = 3;
    }
    
    public void tick() {
        y += velocity_Y;
        x += velocity_X;
    }
    
    public void render(Graphics g) {
        g.setColor(BLUE);
        g.fillRect(x,y,32, 32);
    }
    
    public Rectangle getBounds() {
        return null;
    }
    public Rectangle getBoundsWithPadding() { return null; }
    public Rectangle getBoundsCore() { return new Rectangle(x, y, 4, 4); }
}
