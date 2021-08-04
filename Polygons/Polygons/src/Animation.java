import java.awt.*;

public class Animation {
    int interval;
    double timer;
    int step;
    int duration;
    boolean repeating;
    Polygon shape;


    //Constructors
    Animation(Polygon shape){ //simple shaking animation
        timer = 0;
        step = 0;
        duration = 1000 ; //  1 second animation
        this.shape = shape;
        while(timer < duration){
            timer += 1000/60f;

        }
    }
}
