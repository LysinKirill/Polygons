import java.awt.*;
public class Bot extends Player{
    double moving_timer = 3;
    Bot(){
        super();
    }
    Bot(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color){
        super(pos, r, speed, angular_velocity, maxVertexes, color);
        this.pos.setX(Math.random()*1500);
        this.pos.setY(Math.random()*1000);
    }
    void wander(){
        speed = new Vec2d((Math.random()*10-5)/100f, (Math.random()*10-5)/100f);
        double moving_timer = 3;
    }
    // a random food or player is chosen to move towards
    void ai(Player p, Food f){
        if (p.energy > 10){
            if(p.pos.getX()-this.pos.getX() > 200 || p.pos.getY()-this.pos.getY() > 200){
                speed = new Vec2d((p.pos.getX()-this.pos.getX())/1000f, (p.pos.getY()-this.pos.getY())/1000f);
            } else {
                wander();
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
            wander();
        }
        super.move();
    }
    void update(){
        super.update();
        moving_timer -= (1/60f);
    }
}
