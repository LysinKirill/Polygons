import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet {
    double damage;
    Vec2d pos;
    Vec2d speed;
    Ellipse2D shape;


    Bullet(double damage, Vec2d pos, Vec2d speed) {
        this.damage = damage;
        this.pos = pos;
        this.speed = speed;
        this.shape = new Ellipse2D.Double(pos.getX() - 2.5, pos.getY() - 2.5, 5, 5);
    }

    void move(){
        pos.setX(pos.getX() + this.speed.getX());
        pos.setY(pos.getY() + this.speed.getY());
    }

    void move(Vec2d speed){
        pos.setX(pos.getX() + speed.getX());
        pos.setY(pos.getY() + speed.getY());
    }
    void update(){
        move();
        this.shape = new Ellipse2D.Double(pos.getX() - 2.5, pos.getY() - 2.5, 5, 5);
    }
}