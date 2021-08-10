import java.awt.*;
import java.util.ArrayList;

public class Player extends Crystal {
    double energy = 20;
    ArrayList<Integer> shooting_vertexes = new ArrayList<>();
    double shooting_timer = 0;
    double delay = 2;
    double bulletSpeedAmplifier = 5;
    double maxSpeed = 4;
    double acceleration = 0.2;
    double maxAngularVelocity = 0.03;
    double angularAcceleration = 0.001;
    double decelerationRate = 0.99;
    Player(){
        super(new Vec2d(Math.random()*1500, Math.random()*1000), (int)(Main.width * 0.15), (int)(Main.height * 0.15), new Vec2d(), 0,3, new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
        //this.color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        shooting_vertexes.add((int) (Math.random()) * n);
    }
    public Player(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color) {
        super(pos, r, speed, angular_velocity, maxVertexes, color);
    }
    Player(Color color){
        this();
        this.color = color;
        shooting_vertexes.add((int) (Math.random()) * n);
    }

    Player(Vec2d pos, Color color){
        //super(pos, 200, 200, new Vec2d(), 0,3);
        this();
        this.pos = pos;
        this.color = color;
        shooting_vertexes.add((int) (Math.random()) * n);
    }



    Bullet shoot(int index){
        shooting_timer = delay;
        this.energy -= 0.3;
        //this.shot = true;
        int ind1 = index - 1;
        int ind2 = index + 1;
        if(ind1 < 0){
            ind1 = arrX.length-1;
        }
        if(ind2 >= arrX.length){
            ind2 = 0;
        }
        return new Bullet(1, new Vec2d(arrX[index], arrY[index]), Vec2d.bisector(new Vec2d(arrX[ind1] - arrX[index], arrY[ind1] - arrY[index]), new Vec2d(arrX[ind2] - arrX[index], arrY[ind2] - arrY[index])).inv().mult(bulletSpeedAmplifier));
    }
    void hurt(double damage){
        energy -= damage;
    }

    void move(double x, double y){
        super.move(x, y);
        energy -= Math.sqrt(Math.pow(speed.getX(), 2)+Math.pow(speed.getY(), 2))/1000;
    }

    void move(){
        super.move();
        energy -= Math.sqrt(Math.pow(speed.getX(), 2)+Math.pow(speed.getY(), 2))/1000;
    }
    double overall_speed(){
        return Math.sqrt(Math.pow(this.speed.getX(),2)+Math.pow(this.speed.getY(),2));
    }
    void speed_cap() {
        if (this.overall_speed() > 3) {
            if (this.speed.getX() < 0) this.speed.setX(this.speed.getX() + 0.1);
            else this.speed.setX(this.speed.getX() - 0.1);
            if (this.speed.getY() < 0) this.speed.setY(this.speed.getY() + 0.1);
            else this.speed.setY(this.speed.getY() - 0.1);
        }
        if (this.overall_speed() > 5) speed_cap();
    }

    void update(){
        super.update();
        shooting_timer -= (1/60f);
        setPos();
    }
    void decelerate(double x, double y, double angle){
        this.speed.setX(speed.getX() * x);
        this.speed.setY(speed.getY() * y);
        this.angular_velocity *= angle;
    }
}