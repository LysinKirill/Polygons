import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Poly {
    Vec2d pos;   //Position of the center point  среднее арифметическое сделать
    double[] arrX;
    double[] arrY;
    int n;
    double angle;
    Polygon shape;
    Vec2d leftBottom;
    Vec2d speed;
    double angular_velocity;
    
    Poly(){
        this(new Vec2d(500 + Math.random() * 1000, 250 + Math.random() * 750), 200, 200, new Vec2d(Math.random() * 7 - 3.5, Math.random() * 7 - 3.5), Math.random() * 0.02 - 0.01,20);
    }


    Poly(Vec2d position){
        this(position, 200, 200, new Vec2d(Math.random() * 7 - 7/2f, Math.random() * 7 - 7/2f), Math.random() * 0.02 - 0.01, 10);
    }

    Poly(Vec2d position, Vec2d speed){
        this(position, 200, 200, speed, Math.random() * 0.02 - 0.01, 10);
    }

    Poly(Vec2d position, Vec2d speed, double angular_velocity){
        this(position, 200, 200, speed, angular_velocity, 10);
    }

    Poly(Vec2d position, int width, int height, Vec2d speed, double angular_velocity, int maxVertexes){ //Конструктор


        this.speed = speed;
        this.angular_velocity = angular_velocity;
        Vec2d[] points = Main.getRandArr(maxVertexes, position.getX() - width/2f, position.getX() + width/2f, position.getY() - height/2f, position.getY() + height/2f);
        ArrayList<Vec2d> vertexes = new ArrayList<>();
        ArrayList<Integer> vertexes_ids = new ArrayList<>();
        Vec2d left_bottom = points[0];
        int left_bottom_ID = 0;
        for(int i = 0; i < maxVertexes; i++){  // Finding left bottom point / WORKS
            if(points[i].getY() > left_bottom.getY()){
                left_bottom = points[i];    //Link to an object, not a new one. Might cause some problems
                left_bottom_ID = i;
            }else if((points[i].getY() == left_bottom.getY()) & (points[i].getX() < left_bottom.getX())){
                left_bottom = points[i];    //Link to an object, not a new one. Might cause some problems
                left_bottom_ID = i;
            }
        }


        vertexes.add(left_bottom);
        vertexes_ids.add(left_bottom_ID);



        Vec2d second_vertex = new Vec2d();
        int second_vertex_ID = 0;
        double maxCos = -2;
        for(int i = 0; i < points.length; i++ ){
            if(vertexes.contains(points[i])){
                continue;
            }
            double cos = Vec2d.getCos(new Vec2d(1, 0), new Vec2d(points[i].getX() - left_bottom.getX(), points[i].getY() - left_bottom.getY()));
            if(cos > maxCos){
                maxCos = cos;
                second_vertex_ID = i;
                second_vertex = points[i];
            }

        }



        vertexes.add(second_vertex);
        vertexes_ids.add(second_vertex_ID);

        double minCos = 10;
        int tempID = -1;
        Vec2d tempVec = new Vec2d();
        double cos = 0;
        while(true){
            for(int i = 0; i < points.length; i++){
                if(vertexes_ids.contains(i) & (i != left_bottom_ID)){
                    continue;
                }
                cos = Vec2d.getCos(new Vec2d(vertexes.get(vertexes.size() - 2).getX() - vertexes.get(vertexes.size() - 1).getX(), vertexes.get(vertexes.size() - 2).getY() - vertexes.get(vertexes.size() - 1).getY()), new Vec2d(points[i].getX() - vertexes.get(vertexes.size() - 1).getX(), points[i].getY() - vertexes.get(vertexes.size() - 1).getY()));
                if(cos < minCos){
                    minCos = cos;
                    tempID = i;
                    tempVec = points[i];
                }
            }
            if(tempID == left_bottom_ID){
                break;
            }
            vertexes.add(tempVec);
            vertexes_ids.add(tempID);
            minCos = 10;
        }


        double[] arrX = new double[vertexes.size()];
        double[] arrY = new double[vertexes.size()];
        int[] tempX = new int[arrX.length];
        int[] tempY = new int[arrY.length];

        for(int i = 0; i < vertexes.size(); i++){
            arrX[i] = (int)(vertexes.get(i).getX());
            arrY[i] = (int)(vertexes.get(i).getY());
            tempX[i] = (int)(arrX[i]);
            tempY[i] = (int)(arrY[i]);
        }
        this.arrX = arrX;
        this.arrY = arrY;
        this.shape = new Polygon(tempX, tempY, vertexes.size());
        this.leftBottom = left_bottom;
        this.n = vertexes.size();
        this.pos = new Vec2d(position.getX(), position.getY());
        this.angle = 0;
    }

    Poly(Vec2d position, double r, Vec2d speed, double angular_velocity, int maxVertexes){ //Конструктор round


        this.speed = speed;
        this.angular_velocity = angular_velocity;
        Vec2d[] points = Main.getRandArrCircle(maxVertexes, position.getX(), position.getY(), r);
        ArrayList<Vec2d> vertexes = new ArrayList<>();
        ArrayList<Integer> vertexes_ids = new ArrayList<>();
        Vec2d left_bottom = points[0];
        int left_bottom_ID = 0;
        for(int i = 0; i < maxVertexes; i++){  // Finding left bottom point / WORKS
            if(points[i].getY() > left_bottom.getY()){
                left_bottom = points[i];    //Link to an object, not a new one. Might cause some problems
                left_bottom_ID = i;
            }else if((points[i].getY() == left_bottom.getY()) & (points[i].getX() < left_bottom.getX())){
                left_bottom = points[i];    //Link to an object, not a new one. Might cause some problems
                left_bottom_ID = i;
            }
        }


        vertexes.add(left_bottom);
        vertexes_ids.add(left_bottom_ID);



        Vec2d second_vertex = new Vec2d();
        int second_vertex_ID = 0;
        double maxCos = -2;
        for(int i = 0; i < points.length; i++ ){
            if(vertexes.contains(points[i])){
                continue;
            }
            double cos = Vec2d.getCos(new Vec2d(1, 0), new Vec2d(points[i].getX() - left_bottom.getX(), points[i].getY() - left_bottom.getY()));
            if(cos > maxCos){
                maxCos = cos;
                second_vertex_ID = i;
                second_vertex = points[i];
            }

        }



        vertexes.add(second_vertex);
        vertexes_ids.add(second_vertex_ID);

        double minCos = 10;
        int tempID = -1;
        Vec2d tempVec = new Vec2d();
        double cos = 0;
        while(true){
            for(int i = 0; i < points.length; i++){
                if(vertexes_ids.contains(i) & (i != left_bottom_ID)){
                    continue;
                }
                cos = Vec2d.getCos(new Vec2d(vertexes.get(vertexes.size() - 2).getX() - vertexes.get(vertexes.size() - 1).getX(), vertexes.get(vertexes.size() - 2).getY() - vertexes.get(vertexes.size() - 1).getY()), new Vec2d(points[i].getX() - vertexes.get(vertexes.size() - 1).getX(), points[i].getY() - vertexes.get(vertexes.size() - 1).getY()));
                if(cos < minCos){
                    minCos = cos;
                    tempID = i;
                    tempVec = points[i];
                }
            }
            if(tempID == left_bottom_ID){
                break;
            }
            vertexes.add(tempVec);
            vertexes_ids.add(tempID);
            minCos = 10;
        }


        double[] arrX = new double[vertexes.size()];
        double[] arrY = new double[vertexes.size()];
        int[] tempX = new int[arrX.length];
        int[] tempY = new int[arrY.length];

        for(int i = 0; i < vertexes.size(); i++){
            arrX[i] = (int)(vertexes.get(i).getX());
            arrY[i] = (int)(vertexes.get(i).getY());
            tempX[i] = (int)(arrX[i]);
            tempY[i] = (int)(arrY[i]);
        }
        this.arrX = arrX;
        this.arrY = arrY;
        this.shape = new Polygon(tempX, tempY, vertexes.size());
        this.leftBottom = left_bottom;
        this.n = vertexes.size();
        this.pos = new Vec2d(position.getX(), position.getY());
        this.angle = 0;
    }

    void update(){
        int[] tempX = new int[arrX.length];
        int[] tempY = new int[arrY.length];
        for(int i = 0; i < arrX.length; i++){
            tempX[i] = (int) arrX[i];
            tempY[i] = (int) arrY[i];
        }
        shape = new Polygon(tempX, tempY, tempX.length);
    }
    void move(double x, double y){
        pos.setX(pos.getX() + x);
        pos.setY(pos.getY() + y);
        for(int i = 0; i < n; i++){
            arrX[i] += x;
            arrY[i] += y;
        }
    }

    void move(){
        pos.setX(pos.getX() + this.speed.getX());
        pos.setY(pos.getY() + this.speed.getY());
        for(int i = 0; i < n; i++){
            arrX[i] += this.speed.getX();
            arrY[i] += this.speed.getY();
        }
    }



    void rotate(double angle){
        for(int i = 0; i < n; i++){
            double cos = Vec2d.getCos(new Vec2d(1, 0), new Vec2d(arrX[i] - pos.getX(), arrY[i] - pos.getY()));
            double aux_angle;
            if(arrY[i] > pos.getY()){
                aux_angle = Math.acos(cos);
            }else{
                aux_angle = -Math.acos(cos);
            }
            aux_angle += angle;
            double len = new Vec2d(arrX[i] - pos.getX(), arrY[i] - pos.getY()).length();
            arrX[i] = pos.getX() + (len * Math.cos(aux_angle));
            arrY[i] = pos.getY() + (len * Math.sin(aux_angle));
        }
        this.angle += angle;
        //update();
    }

    void rotate(){
        rotate(angular_velocity);
    }

    void setAngle(double new_angle){
        rotate(new_angle - angle);
    }
    void scale(double sc){
        for(int i = 0; i < arrX.length; i++){
            arrX[i] = pos.getX() + (arrX[i] - pos.getX()) * sc;
            arrY[i] = pos.getY() + (arrY[i] - pos.getY()) * sc;
        }
    }

}
