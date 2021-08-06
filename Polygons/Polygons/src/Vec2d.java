import java.awt.*;

public class Vec2d {
    private double x;
    private double y;


    public Vec2d(){
        this.x = 0;
        this.y = 0;
    }

    public Vec2d(double x, double y){
        this.x = x;
        this.y = y;
    }


    public Vec2d add(Vec2d vec){
        this.x += vec.getX();
        this.y += vec.getY();
        return this;
    }

    static Vec2d sum(Vec2d[] vectors){
        double tempX = 0;
        double tempY = 0;
        for (Vec2d vector : vectors) {
            tempX += vector.getX();
            tempY += vector.getY();
        }
        return new Vec2d(tempX, tempY);
    }

    static Vec2d sum(Vec2d vec1, Vec2d vec2){
        return sum(new Vec2d[]{vec1, vec2});
    }

    static double scProd(Vec2d vec1, Vec2d vec2){
        return (vec1.length() * vec2.length() * getCos(vec1, vec2));
    }

    static double getCos(Vec2d vec1, Vec2d vec2){
        //double a = vec1.length();
        //double b = vec2.length();
        //double c = new Vec2d(vec2.getX() - vec1.getX(), vec2.getY() - vec1.getY()).length();
        //return ((a*a + b*b - c*c)/(2*a*b));

        return ((vec1.x*vec2.x + vec1.y*vec2.y)/(Math.sqrt(vec1.x * vec1.x + vec1.y * vec1.y) * Math.sqrt(vec2.x * vec2.x + vec2.y * vec2.y)));
    }





    public void draw(Graphics2D g2d){
        g2d.drawLine(0, 0, (int)(this.x), (int)(this.y));
    }

    public Vec2d inv(){
        return( new Vec2d(-this.x, -this.y));
    }

    public Vec2d mult(double n){
        return new Vec2d((this.x * n), (this.y * n));
    }

    double length(){
        return (double)(Math.sqrt((Math.pow(this.x, 2) + Math.pow(this.y, 2))));
    }

    void setX(double x){
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    double getX(){
        return x;
    }

    double getY(){
        return y;
    }

    @Override
    public String toString(){
        return "(" + x + ";" + y + ")";
    }


    public static boolean equals(Vec2d vec1, Vec2d vec2){
        //System.out.println(3535);
        return((vec2.x == vec1.x) & (vec1.y == vec1.y));
    }
}

