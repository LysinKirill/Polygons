import java.awt.*;

public class Crystal extends Poly{
    Color color;
    Rectangle collider;
    int timer = 0;



    public boolean isOutOfBounds(){
        return((pos.getX() > 1.01 * Main.width) | (pos.getX() < 0) | (pos.getY() > Main.height * 1.01) | (pos.getY() < 0));
    }
    
    
    /// КОНСТРУКТОРЫ И ПЕРЕГРУЗКИ
    Crystal(){
        super();
        this.color = new Color(17 + (int)(Math.random() * 20),180 + (int)(Math.random() * 75),190 + (int)(Math.random() * 40),50 + (int)(Math.random()*40));
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7); //Чем более вытянутый объект (больше разница между width и height), тем scale factor должен быть ближе к 1
    }


    Crystal(Color color){
        super();
        this.color = color;
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7);
    }

    Crystal(Vec2d position, double r, Color color){ //round
        this(position, r, new Vec2d(Math.random() * 7 - 3.5, Math.random() * 7 - 3.5), Math.random() * 0.02 - 0.01, 20, color);
        this.color = color;
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7);
    }

    Crystal(Vec2d position, double r){ //round
        this(position, r, new Color(7 + (int)(Math.random() * 30),160 + (int)(Math.random() * 95),180 + (int)(Math.random() * 40),50 + (int)(Math.random()*40)));
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7);
    }

    Crystal(Vec2d position, int width, int height, Vec2d speed, double angular_velocity, int maxVertexes, Color color){
        super(position, width, height, speed, angular_velocity, maxVertexes);
        this.color = color;
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7);
    }

    Crystal(Vec2d position, double r, Vec2d speed, double angular_velocity, int maxVertexes, Color color){ //round
        //r - максимальный радиус, в котором генерируются вершины,
        //position - позиция "центральной" точки
        super(position, r, speed, angular_velocity, maxVertexes);
        this.color = color;
        this.collider = Main.scaleBounds(shape.getBounds(), 0.7);
    }
}
