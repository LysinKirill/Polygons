import java.awt.*;
import java.util.ArrayList;

public class Player extends Crystal {
    double delay = 2;
    double timer = 0; //int?
    double maxSpeed = 4;
    double acceleration = 0.2;
    double maxAngularVelocity = 0.03;
    double angularAcceleration = 0.001;
    double decelerationRate = 0.99;

    void update(){
        super.update();
        timer += (1/60);
    }

    void decelerate(double x, double y, double angle){
        this.speed.setX(speed.getX() * x);
        this.speed.setY(speed.getY() * y);
        this.angular_velocity *= angle;
    }
    double energy;
    ArrayList<Integer> shooting_vertexes = new ArrayList<>();
    Player(){
        super(new Vec2d(750, 500), (int)(Main.width * 0.15), (int)(Main.height * 0.15), new Vec2d(), 0,3, new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
        //this.color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        shooting_vertexes.add((int) (Math.random()) * n);
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

    public Player(Vec2d pos, int r, Vec2d speed, double angular_velocity, int maxVertexes, Color color) {
        super(pos, r, speed, angular_velocity, maxVertexes, color);
    }


    Bullet shoot(int index){
        return new Bullet(1, new Vec2d(arrX[index], arrY[index]), new Vec2d(Math.abs(arrX[index]-pos.getX())/100f, Math.abs(arrY[index]-pos.getY())/100f));
    }
    void eat(){}
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
}



