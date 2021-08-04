import java.awt.*;
import java.util.ArrayList;

public class Player extends Poly {
    ArrayList<Integer> shooting_vertexes = new ArrayList<>();
    Color color;
    int energy;
    Player(){
        super(new Vec2d(750, 500), (int)(Main.width * 0.15), (int)(Main.height * 0.15), new Vec2d(), 0,3);
        this.color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        shooting_vertexes.add((int) (Math.random()) * n);
    }

    Player(Color color){
        super(new Vec2d(750, 500), 200, 200, new Vec2d(), 0,3);
        this.color = color;
        shooting_vertexes.add((int) (Math.random()) * n);
    }

    Player(Vec2d pos, Color color){
        super(pos, 200, 200, new Vec2d(), 0,3);
        this.color = color;
        shooting_vertexes.add((int) (Math.random()) * n);
    }








    Bullet shoot(int index){
        return new Bullet(1, new Vec2d(arrX[index], arrY[index]), new Vec2d(Math.abs(arrX[index]-pos.getX())/100f, Math.abs(arrY[index]-pos.getY())/100f));
    }
    void eat(){}
    void hurt(double damage){
        energy -= damage;
    }

    void move(double x, double y){
        pos.setX(pos.getX() + x);
        pos.setY(pos.getY() + y);
        for(int i = 0; i < n; i++){
            arrX[i] += x;
            arrY[i] += y;
        }
        energy -= Math.sqrt(Math.pow(speed.getX(), 2)+Math.pow(speed.getY(), 2))/1000;
        //Main.printArr(arrX);
        //update();
    }

    void move(){
        pos.setX(pos.getX() + this.speed.getX());
        pos.setY(pos.getY() + this.speed.getY());
        for(int i = 0; i < n; i++){
            arrX[i] += this.speed.getX();
            arrY[i] += this.speed.getY();
        }
        energy -= Math.sqrt(Math.pow(speed.getX(), 2)+Math.pow(speed.getY(), 2))/1000;
        //Main.printArr(arrX);
        //update();
    }
}



