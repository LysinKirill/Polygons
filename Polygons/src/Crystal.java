import java.awt.*;

public class Crystal extends Poly{
    double energy;
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

    ///




    /*public Crystal split(Vec2d mousePos){

        double minDist = 10000;
        int i1, i2;
        i1 = -1;
        i2 = -1;
        for(int k = 0; k < arrX.length; k++){
            double x1, x2, y1, y2, b, c, dist, a;
            a = -1;
            x1 = arrX[k];
            y1 = arrY[k];
            //for(int i = k + 2; i < ((k + arrX.length - 1) % arrX.length); i++) {//цикл идет неправильно, в некоторых случаях пытается "идти" от большего к меньшему
            //for(int i = 0; i < arrX.length; i++){// i = k; i < arrX.length + k
            for(int i = k; i < arrX.length + k; i++){
                int index = i;
                if(index >= arrX.length){
                    index %= arrX.length;
                }
                if((i == (k - 1)) | (i == (k)) | (i == (k + 1))){
                    continue;
                }
                x2 = arrX[index];
                y2 = arrY[index];
                b = ((y2 - y1) / (x2 - x1));
                c = y2 - b * x2;
                dist = (Math.abs(a * mousePos.getX() + b * mousePos.getY() + c) / (Math.sqrt(a * a + b * b)));
                System.out.println(dist);
                if (dist < minDist) {
                    minDist = dist;
                    i1 = k;
                    i2 = index;
                }
            }
        }
        Main.x1 = (int)arrX[i1];
        Main.y1 = (int)arrY[i1];
        Main.x2 = (int)arrX[i2];
        Main.y2 = (int)arrY[i2];
        System.out.println("MinDist = "+minDist);
        return new Crystal();
    }*/

}
