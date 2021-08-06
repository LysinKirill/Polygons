public class Bullet {
    double damage;
    Vec2d pos;
    Vec2d speed;
    //    Bullet(){
//        this(1, new Vec2d(pos.getX(), pos.getY()), new Vec2d(speed.getX(), speed.getY()));
//    }
    Bullet(double damage, Vec2d pos, Vec2d speed) {
        this.damage = damage;
        this.pos = pos;
        this.speed = speed;
    }

    void move(){
        pos.setX(pos.getX()+speed.getX());
        pos.setY(pos.getY()+speed.getY());
    }
}