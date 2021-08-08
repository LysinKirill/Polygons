import java.awt.*;
public class Bot extends Player{
    Bot(){
        super();
    }
    Bot(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color){
        super(pos, r, speed, angular_velocity, maxVertexes, color);
        this.pos.setX(Math.random()*1500);
        this.pos.setY(Math.random()*1000);
        //this.pos =
    }
    void wander(){
        speed = new Vec2d(Math.random()*5, Math.random()*5);
    }
    // a random food or player is chosen to move towards
    void ai(Player p, Food f){
        if (p.energy > 10){
            if(p.pos.getX()-this.pos.getX() > 200 || p.pos.getY()-this.pos.getY() > 200){
                speed = new Vec2d(p.pos.getX()-this.pos.getX(), p.pos.getY()-this.pos.getY());
            } else {
                if (Math.random() * 6 > 4) {
                    //shoot((int)Math.random()*n, p);  // shoots the player with a chance of 20%
                }
            }
        }
        else if(this.energy <= 10){
            speed.setX(speed.getX()*3);
            speed.setY(speed.getY()*3);
            speed = new Vec2d(f.pos.getX()-this.pos.getX(), f.pos.getY()-this.pos.getY());
        }
        else
        {
            speed = new Vec2d(Math.random(), Math.random());
        }
        super.move();
    }
}
