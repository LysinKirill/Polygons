import java.awt.*;
import java.util.ArrayList;

public class Player extends Crystal {
    double delay = 2;
    double shooting_timer = 0; //?
    //double timer = 0; //int?
    double maxSpeed = 4;
    double acceleration = 0.2;
    double maxAngularVelocity = 0.03;
    double angularAcceleration = 0.001;
    double decelerationRate = 0.99;
    double energy = 20;
    double bulletSpeedAmplifier = 5;
    //boolean shot = false;



    ArrayList<Integer> shooting_vertexes = new ArrayList<>();

    void update(){
        super.update();
        shooting_timer -= (1/60f);

    }
    /*void update(){
        super.update();
        timer += (1/60f);
        if(shot){
            shooting_timer += 0.0167;//(1/60);
        }
        if(shooting_timer >= delay) {
            shooting_timer = 0;
            this.shot = false;
        }
    }*/

    void decelerate(double x, double y, double angle){
        this.speed.setX(speed.getX() * x);
        this.speed.setY(speed.getY() * y);
        this.angular_velocity *= angle;
    }


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
    void eat(){}
    void hurt(double damage){
        energy -= damage;
    }

    void move(double x, double y){
        super.move(x, y);
        energy -= Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2))/1000;
    }

    void move(){
        super.move();
        energy -= Math.sqrt(Math.pow(speed.getX(), 2)+Math.pow(speed.getY(), 2))/1000;
    }


}



