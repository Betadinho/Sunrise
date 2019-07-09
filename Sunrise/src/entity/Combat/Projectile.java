package entity.Combat;

import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.mob.Wizard;

import java.awt.*;

import static entity.handler.Handler.entities;

public class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected double nX, nY;
    protected int speed, range;
    
    public Projectile(int x, int y, ID id, Handler handler, double dir) {
        super(x, y, id, handler);
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        speed = 8;
        range = 20;
        damage = 20;
        
        nY = speed * Math.cos(angle);
        nX = speed * Math.sin(angle);
    }
    
    public void tick() {
        moveWithCollision();
    }
    
    public void moveWithCollision() {
        x += nX;
        y += nY;
        collision();
    }
    
    public void moveWithoutCollision() {
        x += nX;
        y += nY;
    }
    
    

    private void collision() {
        for(Entity en : entities) {
            if(en.getId() == ID.Block) {
                if(getBounds().intersects(en.getBounds())) {
                    handler.removeEntity(this);
                }
/*                else {
                    if(getBounds().intersects(en.getBounds())) {
                        handler.removeEntity(this);
                        handler.removeEntity(en);
                        System.out.println("Entity to remove:  " + en);
                    }
                }*/
            }
        }
    }
    
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, 8, 8);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
    public Rectangle getBoundsWithPadding() { return new Rectangle(x, y, 16, 16); }
    public Rectangle getBoundsCore() { return new Rectangle(x, y, 4, 4); }
    public int getDamage() {
        return damage;
    }
}
