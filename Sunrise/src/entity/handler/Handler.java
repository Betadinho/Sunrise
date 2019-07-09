package entity.handler;
import entity.Entity;
import entity.mob.Wizard;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler{
    
    //create list for entities currently existent in a level in form of a construct iterable while modifying it (e.g. a CopyOnWriteArrayList)
    public static final CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();
    private boolean up = false, down = false, right = false, left = false;
    
    
    public void tick() {
        synchronized(entities) {
            for(Entity tempEntity : entities) {
                tempEntity.tick();
            }
        }
    }
    
    public void render(Graphics g) {
            for(Entity tempEntity : entities) {
                tempEntity.render(g);
        }
    }
    
    public void addEntity(Entity tempEntity) {
                entities.add(tempEntity);
    }

    public void removeEntity(Entity tempEntity) {
               entities.removeIf(en -> en == tempEntity);
    }

    public java.util.List<Entity> getEntities() {
        return entities;
    }

    public boolean isUp() {
        return up;
    }
    
    public void setUp(boolean up) {
        this.up = up;
    }
    
    public boolean isDown() {
        return down;
    }
    
    public void setDown(boolean down) {
        this.down = down;
    }
    
    public boolean isRight() {
        return right;
    }
    
    public void setRight(boolean right) {
        this.right = right;
    }
    
    public boolean isLeft() {
        return left;
    }
    
    public void setLeft(boolean left) {
        this.left = left;
    }
    
    public boolean playerIsDead() {
        for(Entity e : entities) {
            if(e.getId() == ID.Player && ((Wizard) e).isDead()) {
                System.out.println("You have been killed!");
                //Add some sort of prompt when the player dies and add option to restart the game
                return true;
            }
        } return false;
    }
}
