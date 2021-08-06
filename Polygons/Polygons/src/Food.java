import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Food {
    double energy; // radius = energy*15
    Vec2d pos;
    Ellipse2D shape;
    Color color = Color.YELLOW;
    Food(){
        this.energy = 1;
        this.pos = new Vec2d(Math.random()*Main.width, Math.random()*Main.height);
        this.shape = new Ellipse2D.Double(pos.getX() - energy*15, pos.getY() - energy*15, energy*15, energy*15);
    }
    Food(double energy, Vec2d pos){
        this.energy = energy;
        this.pos = pos;
        this.shape = new Ellipse2D.Double(pos.getX() - energy*15, pos.getY() - energy*15, energy*15, energy*15);
    }
}
