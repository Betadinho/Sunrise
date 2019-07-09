package entity.mob;

import entity.Combat.Projectile;
import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import entity.sprites.Spritesheet;
import input.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.handler.Handler.entities;

public class Wizard extends Entity {
    
    private BufferedImage skin;
    
    public Wizard(int x, int y, ID id, Handler handler, Spritesheet s_S) {
        super(x,y,id,handler,s_S);
        health = 100;
        skin = s_S.grabImage(1,1,32,48);
        
    }
    
    public void tick() { //Update movement
        x += velocity_X;
        y += velocity_Y;
        
        collision();
        
        if(handler.isUp()) velocity_Y = -5;
            else if(!handler.isDown())velocity_Y = 0;
        
        if(handler.isDown()) velocity_Y = 5;
            else if(!handler.isUp())velocity_Y = 0;
        
        if(handler.isLeft()) velocity_X = -5;
            else if(!handler.isRight())velocity_X = 0;
        
        if(handler.isRight()) velocity_X = 5;
            else if(!handler.isLeft())velocity_X = 0;
            
        updateShooting();
    }
    
    private void collision() {
        synchronized(entities) {
            for(Entity e : entities) {
                if(e.getId() == ID.Block) {
                    if(getBounds().intersects(e.getBounds())) {
                        x += velocity_X * -1;
                        y += velocity_Y * -1;
                    }
                } else if(e.getId() == ID.Enemy) {
                    if(getBounds().intersects(e.getBounds())) {
                        setHealth(getHealth() - e.damage);
                        x += (velocity_X * 2) * -1;
                        y += (velocity_Y * 2) * -1;
                        velocity_X = 0;
                        velocity_Y = 0;
                        System.out.println("You've bin hit by: " + e.getName() + " for " + e.damage + " damage! Remaining: " + health);
                    }
                }
            }
        }
    }
    
    private void updateShooting() {
        if(MouseInput.getMouseB() == 1) {
            double dir_x = MouseInput.getMouseX();
            double dir_y = MouseInput.getMouseY();
            double dir = Math.atan2(dir_y, dir_x);
            shoot(x, y, dir);
        }
    }
    
    private void shoot(int x, int y, double dir) {
        dir = (dir * (180 / Math.PI));
        for(Entity en : entities) { //TODO: Mega inefficient
            if(en.getId() == ID.Player) { //+16 on x and +24 on y to account for sprite positions
                handler.addEntity((new Projectile(en.getX()+24, en.getY()+16, ID.Projectile, handler, dir)));
            }
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(skin, x, y, null);
    }
    
    public Rectangle getBounds() { return new Rectangle(x,y,32,48); }
    
    public Rectangle getBoundsWithPadding() { return new Rectangle(x-16,y-16,64,96); }

    public Rectangle getBoundsCore() { return new Rectangle(x,y,4,4); }
    
    public boolean isDead() {
        return health == 0;
    }
}
