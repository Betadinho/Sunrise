package input;

import entity.Combat.Projectile;
import entity.Entity;
import entity.handler.Handler;
import entity.handler.ID;
import view.Camera;

import java.awt.event.*;

import static entity.handler.Handler.entities;

public class MouseInput extends MouseAdapter implements MouseListener, MouseMotionListener {
    
    private Handler handler;
    private Camera camera;
    private static int mouseY = -1;
    private static int mouseX = -1;
    private static int mouseB = -1;

    public MouseInput(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }


    public MouseInput() {
        super();
    }

//    public void mousePressed(MouseEvent e)  { //Attack / Fire projectile Hopefully Deprecated by end of today
//
//
//        for(Entity en : entities) {
//            if(en.getId() == ID.Player) { //+16 on x and +24 on y to account for sprite positions
//                handler.addEntity((new Projectile(en.getX()+16, en.getY()+24, ID.Projectile, handler)));
//            }
//        }
//
//    }
    
    public void mousePressed(MouseEvent e)  { // The cherno Option
        mouseB = e.getButton();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (e.getX());
        mouseY = (e.getY());
    }

    
    //------------Getter / Setter
    public static int getMouseY() {
        return mouseY;
    }
    
    public static void setMouseY(int mouseY) {
        MouseInput.mouseY = mouseY;
    }
    
    public static int getMouseX() {
        return mouseX;
    }
    
    public static void setMouseX(int mouseX) {
        MouseInput.mouseX = mouseX;
    }
    
    public static int getMouseB() {
        return mouseB;
    }
    
    public static void setMouseB(int mouseB) {
        MouseInput.mouseB = mouseB;
    }
}
