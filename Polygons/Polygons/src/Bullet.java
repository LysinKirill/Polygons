import java.awt.geom.Ellipse2D;

public class Bullet {
    double damage;
    Vec2d pos;
    Vec2d speed;
    Ellipse2D shape;


    Bullet(double damage, Vec2d pos, Vec2d speed) {
        this.damage = damage;
        this.pos.setX(pos.getX());
        this.pos.setY(pos.getY());
        this.speed.setX(speed.getX());
        this.speed.setY(speed.getY());
        this.shape = new Ellipse2D.Double(pos.getX() - 10, pos.getY() - 10, 10, 10);
    }

    void move(){
        pos.setX(this.pos.getX()+this.speed.getX());
        pos.setY(this.pos.getY()+this.speed.getY());
    }

    void move(Vec2d pos, Vec2d speed){
        pos.setX(pos.getX()+speed.getX());
        pos.setY(pos.getY()+speed.getY());
    }
}
