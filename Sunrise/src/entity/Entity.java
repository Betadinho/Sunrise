package entity;

import entity.handler.Handler;
import entity.handler.ID;
import entity.sprites.Spritesheet;

import java.awt.*;

public abstract class Entity {
    protected Point position;
    protected int x, y;
    protected float velocity_X, velocity_Y;
    protected ID id;
    protected Spritesheet spriteSheet;
    public Handler handler;
    public String name;
    public int health;
    public int damage;
    public Entity owner;
    public Point destination;



public Entity() {}

public Entity(int x, int y, ID id) {
    this.x = x;
    this.y = y;
    this.id = id;
}

public Entity(int x, int y, ID id, Handler handler, Spritesheet spriteSheet) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
    this.spriteSheet = spriteSheet;
}

public Entity(int x, int y, ID id, Handler handler, String name, Spritesheet spriteSheet) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
    this.name = name;
    this.spriteSheet = spriteSheet;
    this.health = 100;
}
//Super constructor used for projectiles
public Entity(int x, int y, ID id, Handler handler, Entity owner, Point position, Point destination) {
    this.owner = owner;
    this.position = position;
    this.destination = destination;
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
    this.health = 100;
}

public Entity(int x, int y, ID id, Handler handler) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
    this.health = 100;
}

public abstract void tick();

    public ID getId() {
        return id;
    }
    
    public void setId(ID id) {
        this.id = id;
    }

    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract Rectangle getBoundsWithPadding();
    public abstract Rectangle getBoundsCore();
    
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position) { this.position = position; }

    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public float getVelocity_X() {
        return velocity_X;
    }
    
    public void setVelocity_X(float velocity_X) {
        this.velocity_X = velocity_X;
    }
    
    public float getVelocity_Y() {
        return velocity_Y;
    }
    
    public void setVelocity_Y(float velocity_Y) {
        this.velocity_Y = velocity_Y;
    }

    public Handler getHandler() {
        return handler;
    }
    
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getDamage() {
        return damage;
    }
    
}
