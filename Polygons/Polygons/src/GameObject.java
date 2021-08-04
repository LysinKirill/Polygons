import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject{
    Rectangle collider;
    BufferedImage texture;
    Vec2d position;
    String annotation = "";
    Vec2d annotationPos = new Vec2d();
    double scaleFactor = 1;


    GameObject(String path, Vec2d position, double scaleFactor){
        this.position = position;
        this.texture = null;
        this.collider = null;
        try{
            this.texture = ImageIO.read(new File(path));
            this.collider = new Rectangle((int)position.getX(), (int)position.getY(), (int)(this.texture.getWidth() * scaleFactor), (int)(this.texture.getHeight() * scaleFactor));
        }catch(IOException e){
            e.getStackTrace();
        }

        this.scaleFactor = scaleFactor;
    }


    GameObject(BufferedImage image, Vec2d position, double scaleFactor){
        this.position = position;
        this.texture = image;
        this.collider = new Rectangle((int)position.getX(), (int)position.getY(), (int)(image.getWidth() * scaleFactor), (int)(image.getHeight() * scaleFactor));
        this.scaleFactor = scaleFactor;
    }

    GameObject(BufferedImage image, Vec2d position, Rectangle collider, double scaleFactor){
        this.position = position;
        this.texture = image;
        this.collider = collider;
        this.scaleFactor = scaleFactor;
    }
}
