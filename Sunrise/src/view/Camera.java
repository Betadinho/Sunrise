package view;

import game.Game;
import entity.Entity;

public class Camera{
    private float x, y;
    private float width = Game.width;
    private float height = Game.height;
    
    
    public Camera (float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick(Entity entity) {
        x += ((entity.getX() - x) - width / 2) * 0.5f;
        y += ((entity.getY() - y) - height / 2) * 0.5f;
        
        if(x <= 0 ) x = 0;
        if(x >= width + 32)  x = width + 48;
        if(y <= 0 ) y = 0;
        if(y >= height + 48) y = height + 48;
    }

    public float getX() { return x; }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
        this.y = y;
    }
}
