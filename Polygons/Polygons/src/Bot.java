import java.awt.*;

public class Bot extends Player{

    Bot(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color){
        super(pos, r, speed, angular_velocity, maxVertexes, color);
    }


    // a random food or player is chosen to move towards
    /*void wander(Player p, Food f){
        if (p.energy > 10){
            speed = new Vec2d(p.pos.getX()-this.pos.getX(), p.pos.getY()-this.pos.getY());
            if(Math.random()*6 > 5){
                shoot(i, p);  // shoots the player with a chance of about 16%
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
    }*/
}