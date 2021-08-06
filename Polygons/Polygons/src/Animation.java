import java.awt.*;

public class Animation {
    int timer;
    int step;
    int duration;//automatically converted to milliseconds
    boolean repeating;
    Polygon shape;


    //Constructors
    Animation(Polygon shape){
        timer = 0;
        step = 0;
        duration = 60 * 2; //  10 second animation
        this.shape = shape;

    }
}
