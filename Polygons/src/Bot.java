import java.awt.*;
public class Bot extends Player{
    double moving_timer = 0;
    Bot(){
        super();
    }
    Bot(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color){
        super(pos, r, speed, angular_velocity, maxVertexes, color);
    }
    void wander(){
        if(this.moving_timer <= 0){
            this.speed = new Vec2d((Math.random()*10-5)/1000f, (Math.random()*10-5)/1000f);
            this.moving_timer = 3;
        }
    }
    void follow(Player player){
        if(moving_timer <= 0){
            this.speed = new Vec2d((player.pos.getX()-this.pos.getX())/1000f, (player.pos.getY()-this.pos.getY())/1000f);
            this.moving_timer = 3;
        }
    }
    // a random food or player is chosen to move towards
    void ai(Player p, Food f){
        if (p.energy > 10){
            if(p.pos.getX()-this.pos.getX() > 100 && p.pos.getX()-this.pos.getX() < 400 || p.pos.getY()-this.pos.getY() > 100 && p.pos.getY()-this.pos.getY() < 400){
                if(moving_timer <= 0) {
                    speed = new Vec2d((p.pos.getX()-this.pos.getX())/1000f, (p.pos.getY()-this.pos.getY())/1000f);
                }
            } else {
                if(moving_timer <= 0) {
                    wander();
                }
                if (Math.random() * 6 > 4) {
                    for(int i = 0;i<this.shooting_vertexes.size();i++){
                        shoot(i);
                    }
                }
            }
        }
        else if(this.energy <= 10){
            speed.setX(speed.getX()*3);
            speed.setY(speed.getY()*3);
            speed = new Vec2d(f.pos.getX()-this.pos.getX(), f.pos.getY()-this.pos.getY());
            //moving_timer = 6;
            moving_timer = 0;
        }
        else
        {
            wander();
        }
        super.move();
    }
    void move(){
        super.move();
    }
    void update(){
        super.update();
        moving_timer -= (1/60f);
    }
    double get_range_to_player(double player_pos_x, double player_pos_y){
        return Math.sqrt(Math.pow(player_pos_x-pos.getX(), 2) + Math.pow(player_pos_y-pos.getY(), 2));
    }
}
