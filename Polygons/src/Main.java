import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main extends JComponent implements KeyListener, ActionListener, MouseMotionListener, MouseListener, ComponentListener {
    Timer timer = new Timer(0, this); //    delay = ?
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension dimension = toolkit.getScreenSize();
    static int width = (int)(dimension.width);
    static int height = (int)(dimension.height*0.98);

    public static String path = new File("").getAbsolutePath() + "/src/img/";
    static int crystalCounter = 0;
    //static ArrayList<Crystal> crystals = new ArrayList<>();
    static ArrayList<Bot> bots = new ArrayList<>();
    static ArrayList<GameObject> gameObjects = new ArrayList<>();
    static Player player = new Player();
    static ArrayList<Food> food = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    //static int X = 0;
    //static int Y = 0;
    static boolean[] pressedKeys = new boolean[1024];
    static int x1 = 0;
    static int x2 = 0;
    static  int y1 = 0;
    static int y2 = 0;


    //static Vec2d[] arr = Main.getRandArrCircle(13000, 500, 500, 160);

    //@Override
    protected void paintComponent(Graphics2D g) {
        for(int i = 0; i < gameObjects.size(); i++){
            try{
                g.setFont(new Font("Serif", Font.BOLD, 25));
                BufferedImage image = gameObjects.get(i).texture;
                g.drawImage(image.getScaledInstance((int)(image.getWidth() * gameObjects.get(i).scaleFactor * (width/2560f)), (int)(image.getHeight() * gameObjects.get(i).scaleFactor * (height/1440f)), BufferedImage.SCALE_SMOOTH), (int)gameObjects.get(i).position.getX(), (int)gameObjects.get(i).position.getY(), null);
                g.drawString(gameObjects.get(i).annotation, (int)(gameObjects.get(i).position.getX() + gameObjects.get(i).annotationPos.getX()), (int)(gameObjects.get(i).position.getY() + gameObjects.get(i).annotationPos.getY()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args){
        gameObjects.add(new GameObject(path + "crystal.png", new Vec2d(width * 0.05, height * 0.02), 0.07));
        gameObjects.get(0).annotation = Integer.toString(crystalCounter);
        gameObjects.get(0).annotationPos = new Vec2d(-width * 0.025, height * 0.03);
        gameObjects.add(new GameObject(path + "energy.png", new Vec2d(width * 0.15, height * 0.02), 0.13));
        gameObjects.get(1).annotation = Integer.toString((int)Math.round(player.energy));
        gameObjects.get(1).annotationPos = new Vec2d(-width * 0.025, height * 0.03);


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main simulation = new Main();


                JFrame frame = new JFrame("Simulation");
                frame.setSize(width, height);
                frame.setVisible(true);
                //frame.setLocation(750, 400);
                frame.setLocation(0, 0);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


                //frame.getContentPane().add()
                frame.getContentPane().add(simulation);
                //frame.getContentPane().addKeyListener(simulation);
                frame.addKeyListener(simulation);
                frame.getContentPane().addComponentListener(simulation);
                frame.getContentPane().addMouseListener(simulation);

            }
        });


        while(true){//    game cycle
            //System.out.println(bullets.size());
            //qqSystem.out.println(player.speed);
            gameObjects.get(1).annotation = Integer.toString((int)Math.round(player.energy));
            Main.controls();


            if(Math.random() + 0.0007 * bots.size() < 0.01){    // максимум 15 кристаллов. Вероятность спавна нового кристалла падает при спауне очередного кристалла
                double posX, posY;
                posX = Math.random()*(width*0.3) - width*0.15;
                if(posX > -width*0.02){posX += (1.06 * width);}
                posY = Math.random() * height*1.6 - height*0.3;
                Vec2d speed = new Vec2d(((((Math.random() * width * 1.4 - 0.7 * width) + width)/2f - posX)/(width*0.5)* (16/9f)), (((Math.random() * height * 1.4 - 0.7*height) + height)/2f - posY)/(height*0.5));
                //if(posY > -height*0.05){posY += (1.1 * height);}

                //crystals.add(new Crystal(new Vec2d(posX, posY), 100, new Vec2d(((width/2f - posX)/(width*0.5)* (16/9f) + Math.random() * 1.6 - 0.8), (height/2f - posY)/(height*0.5) + Math.random() * 1.6 - 0.8), Math.random()*0.02 - 0.01, 20, new Color(7 + (int)(Math.random() * 30),160 + (int)(Math.random() * 95),180 + (int)(Math.random() * 40),50 + (int)(Math.random()*40))));
                //
                bots.add(new Bot(new Vec2d(posX, posY), 100, speed, Math.random()*0.02 - 0.01, 20, new Color(7 + (int)(Math.random() * 30),160 + (int)(Math.random() * 95),180 + (int)(Math.random() * 40),50 + (int)(Math.random()*40))));
                food.add(new Food(1, new Vec2d(Math.random()*width, Math.random()*height)));
                //bots.add(new Bot());
            }


            for(int i = 0; i < bots.size(); i++){
                bots.get(i).move();
                bots.get(i).rotate();
                //bots.get(i).ai();
                bots.get(i).update();
                if(bots.get(i).isOutOfBounds()){
                    bots.get(i).timer++;}else{
                    bots.get(i).timer = 0;}
                if((bots.get(i).timer / 60) > 5){
                    bots.remove(i);
                    //System.out.println("removed " + i +"  crystal");
                }
            }






            //collisions with food


            for (int i = 0; i < food.size(); i++){
                if((food.get(i).shape.getBounds2D().intersects(player.shape.getBounds2D())) && (Main.inBounds(player.arrX, player.arrY, food.get(i).pos))){
                    player.energy += food.get(i).energy;
                    food.remove(i);
                }
            }
            //collisions of bots with bullets
            for (int i = 0; i < bots.size(); i++){
                for (int j = 0; j < bullets.size(); j++) {
                    if ((bots.get(i).shape.getBounds2D().intersects(bullets.get(j).shape.getBounds2D())) && (Main.inBounds(bots.get(i).arrX, bots.get(i).arrY, bullets.get(j).pos))) {
                        bots.get(i).energy -= bullets.get(j).damage;
                        bots.get(i).color = Color.RED;
                        bullets.remove(j);
                    }
                }
            }
            //collisions of player with bullets
            for (int j = 0; j < bullets.size(); j++) {
                bullets.get(j).update();
                if ((bullets.get(j).shape.getBounds2D().intersects(player.shape.getBounds2D())) && (Main.inBounds(player.arrX, player.arrY, bullets.get(j).pos))) {
                    player.energy -= bullets.get(j).damage;
                    bullets.remove(j);
                    System.out.println("Collision");
                }
            }


            //bots AI???
            for (int i = 0;i < bots.size();i++){
                if(bots.get(i).energy <= 0){
                    for(int k = 0;k<Math.random()*6;k++){
                        food.add(new Food(0.1*Math.random()*15+0.5, new Vec2d(bots.get(i).pos.getX() + Math.random()*50, bots.get(i).pos.getY() + Math.random()*50)));
                    }
                    bots.remove(i);
                    continue;
                }
                //bots.get(i).ai(player, food.get((int)(Math.random()*food.size())));


                if(bots.get(i).get_range_to_player(player.pos.getX(), player.pos.getY()) <= 400 && bots.get(i).get_range_to_player(player.pos.getX(), player.pos.getY()) >= 100){
                    bots.get(i).follow(player); // режим преследования игрока
                } else {
                    bots.get(i).wander(); // режим блуждания
                }
                if (Math.random() * 100 + bots.get(i).get_range_to_player(player.pos.getX(), player.pos.getY())/300f > 95) {
                    for(int f = 0;f<bots.get(i).shooting_vertexes.size();f++){
                        bullets.add(bots.get(i).shoot(f));
                    }
                }
                bots.get(i).move();
                bots.get(i).update();
            }

            player.move();
            player.rotate();
            player.update(); // Обновление игрока
            try{
                TimeUnit.MILLISECONDS.sleep(16);}catch(InterruptedException e){
                e.getStackTrace();
            };
        }
    }

    //@Override
    public void paint(Graphics g){


        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();

        for(int i = 0; i < food.size(); i++){
            g2d.setColor(Color.YELLOW);
            g2d.fill(food.get(i).shape);
            g2d.setColor(Color.BLACK);
            g2d.draw(food.get(i).shape);
        }


        for(int i = 0; i < bots.size(); i++){
            g2d.setColor(bots.get(i).color);
            g2d.fill(bots.get(i).shape);
            g2d.setColor(Color.BLACK);
            //g2d.drawString(Integer.toString(i), (int)crystals.get(i).pos.getX(), (int)crystals.get(i).pos.getY());
        }
        //g2d.drawLine(x1, y1, x2, y2);

        for(int i = 0; i < bullets.size(); i++){
            g2d.setColor(Color.BLACK);
            g2d.fill(bullets.get(i).shape);
        }

        g2d.setColor(player.color);
        g2d.fill(player.shape);
        g2d.setColor(oldColor);
        Rectangle2D.Double rect = new Rectangle2D.Double(50, 50, 100, 100);
        //g2d.fill(rect);




        g2d.setStroke(new BasicStroke(3));







        g2d.setStroke(new BasicStroke(1));
        g2d.draw(new Rectangle(5, 5, width-30, height-50));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(-10, 0, width + 20, 5);
        g2d.fillRect(-10, height - 44, width + 20, 100);
        g2d.fillRect(0, -10, 5, height + 50);
        g2d.fillRect(width - 24, -10, 50, height + 50);
        g2d.setColor(oldColor);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 1f));
        paintComponent(g2d);
        timer.start();
    }






    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
        //System.out.println(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println(new Vec2d(mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Vec2d position = new Vec2d(mouseEvent.getX(), mouseEvent.getY());
        int id = Main.getSelectedCrystal(position);
        if(id!= -1){
            //crystals.get(id).split(position);
            crystalCounter += 1;
            gameObjects.get(0).annotation = Integer.toString(crystalCounter);
        }


        //X = (int)position.getX();
        //Y = (int)position.getY();
    }


    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void componentResized(ComponentEvent componentEvent) {
        width = this.getWidth();
        height = this.getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {

    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {

    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {

    }


    //@Override
    //protected void paintComponent(Graphics g) {

    //}




    public static Vec2d[] getRandArr(int n, double min, double max){
        Vec2d[] arr = new Vec2d[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Vec2d(Math.random() * (max - min) + min, Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static Vec2d[] getRandArr(int n, double minX, double maxX, double minY, double maxY){
        Vec2d[] arr = new Vec2d[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Vec2d(Math.random() * (maxX - minX) + minX, Math.random() * (maxY - minY) + minY);
        }
        return arr;
    }

    public static Vec2d[] getRandArrCircle(int n, double x, double y, double r){
        Vec2d[] arr = new Vec2d[n];
        for(int i = 0; i < n; i++){
            double angle = Math.random() * Math.PI * 2;
            arr[i] = new Vec2d(x + Math.cos(angle) *  (Math.random() * r), y + Math.sin(angle) * (Math.random() * r));
        }
        return arr;
    }

    public static void printArr(Vec2d[] a){
        for(int i = 0; i < a.length; i++){
            System.out.println(i + ":  " + a[i]);
        }
    }

    public static Rectangle scaleBounds(Rectangle rect, double sc){
        double centerX, centerY;
        centerX = (rect.getX() + rect.getWidth() / 2f);
        centerY = (rect.getY() + rect.getHeight() / 2f);
        return new Rectangle((int)(centerX - rect.getWidth()*sc / 2), (int)(centerY - rect.getHeight()*sc / 2), (int)(rect.getWidth() * sc), (int)(rect.getHeight() * sc));
    }

    public static boolean intersects123(Vec2d p1, Vec2d p2, Vec2d p3, Vec2d p4){
        double ax1, ax2, ay1, ay2, bx1, bx2, by1, by2;
        ax1 = p1.getX();
        ay1 = p1.getY();
        ax2 = p2.getX();
        ay2 = p2.getY();

        bx1 = p3.getX();
        by1 = p3.getY();
        bx2 = p4.getX();
        by2 = p4.getY();

        double v1 = ((bx2-bx1)*(ay1-by1)-(by2-by1)*(ax1-bx1));
        double v2 = ((bx2-bx1)*(ay2-by1)-(by2-by1)*(ax2-bx1));
        double v3 = ((ax2-ax1)*(by1-ay1)-(ay2-ay1)*(bx1-ax1));
        double v4 = ((ax2-ax1)*(by2-ay1)-(ay2-ay1)*(bx2-ax1));
        return ((v1*v2<0) & (v3*v4<0));
    }


    public static boolean intersects(Vec2d p1, Vec2d p2, Vec2d p3, Vec2d p4){
        double a1, a2, c1, c2;
        a1 = ((p2.getY() - p1.getY())/(p2.getX() - p1.getX()));
        a2 = ((p4.getY() - p3.getY())/(p4.getX() - p3.getX()));
        c1 = p1.getY() - a1 * p1.getX();
        c2 = p3.getY() - a2 * p3.getX();
        double x = (c2 - c1)/(a1 - a2);
        double y = a1 * x + c1;
        return (((Math.min(p1.getX(), p2.getX()) <= x) & (Math.max(p1.getX(), p2.getX()) >= x)) & ((Math.min(p1.getY(), p2.getY()) <= y) & (Math.max(p1.getY(), p2.getY()) >= y))) & (((Math.min(p3.getX(), p4.getX()) <= x) & (Math.max(p3.getX(), p4.getX()) >= x)) & ((Math.min(p3.getY(), p4.getY()) <= y) & (Math.max(p3.getY(), p4.getY()) >= y)));
    }

    public static int getSelectedCrystal(Vec2d position){
        for(int i = 0; i < bots.size(); i++){
            int intersections = 0;
            for(int j = 1; j < bots.get(i).arrX.length; j++){

                if(Main.intersects(position, new Vec2d(position.getX() + 10000, position.getY()), new Vec2d(bots.get(i).arrX[j - 1], bots.get(i).arrY[j - 1]), new Vec2d(bots.get(i).arrX[j], bots.get(i).arrY[j]))){
                    intersections++;
                    //System.out.println("intersection with " + (j - 1) + " - " + j + "      (" + intersections +")     " + i + " crystal");
                }
            }
            if(Main.intersects(position, new Vec2d(position.getX() + 10000, position.getY()), new Vec2d(bots.get(i).arrX[bots.get(i).arrX.length - 1], bots.get(i).arrY[bots.get(i).arrX.length - 1]), new Vec2d(bots.get(i).arrX[0], bots.get(i).arrY[0]))){
                intersections++;
                //System.out.println("intersection with " + (crystals.get(i).arrX.length) + " - " + 0 + "      (" + intersections +")     " + i + " crystal");
            }
            if(intersections % 2 == 1){
                return i;
            }
        }
        return -1;

    }

    public static boolean inBounds(double[] arrX, double[] arrY, Vec2d pos){
        int intersections = 0;
        for(int i = 1; i < arrX.length; i++){
            if(Main.intersects(pos, new Vec2d(pos.getX() + 10000, pos.getY()), new Vec2d(arrX[i - 1], arrY[i - 1]), new Vec2d(arrX[i], arrY[i]))){
                intersections++;
            }
        }
        if(Main.intersects(pos, new Vec2d(pos.getX() + 10000, pos.getY()), new Vec2d(arrX[arrX.length - 1], arrY[arrX.length - 1]), new Vec2d(arrX[0], arrY[0]))){
            intersections++;
        }
        return (intersections % 2 == 1);
    }

    public static Ellipse2D.Double getCircle(double x, double y, double r){
        return new Ellipse2D.Double(x - r, y - r, 2*r, 2*r);
    }



    static void controls(){
        if(pressedKeys[87]){ // w pressed
            if(player.speed.getY() - player.acceleration < -player.maxSpeed){
                player.speed.setY(-player.maxSpeed);
            }else{player.speed.setY(player.speed.getY() - player.acceleration);}
        }else{player.decelerate(1, player.decelerationRate, 1);}

        if(pressedKeys[83]){ // s pressed
            if(player.speed.getY() + player.acceleration > player.maxSpeed){
                player.speed.setY(player.maxSpeed);
            }else{player.speed.setY(player.speed.getY() + player.acceleration);}
        }else{player.decelerate(1, player.decelerationRate, 1);}

        if(pressedKeys[65]){ // a pressed
            if(player.speed.getX() - player.acceleration < -player.maxSpeed){
                player.speed.setX(-player.maxSpeed);
            }else{player.speed.setX(player.speed.getX() - player.acceleration);}
        }else{player.decelerate(player.decelerationRate, 1, 1);}

        if(pressedKeys[68]){ // d pressed
            if(player.speed.getX() + player.acceleration > player.maxSpeed){
                player.speed.setX(player.maxSpeed);
            }else{player.speed.setX(player.speed.getX() + player.acceleration);}
        }else{player.decelerate(player.decelerationRate, 1, 1);}

        if(pressedKeys[81]){ // q pressed
            if(player.angular_velocity - player.angularAcceleration < -player.maxAngularVelocity){
                player.angular_velocity = -player.maxAngularVelocity;
            }else{player.angular_velocity -= player.angularAcceleration;}
        }else{player.decelerate(1, 1, player.decelerationRate);}

        if(pressedKeys[69]){ // e pressed
            if(player.angular_velocity + player.angularAcceleration > player.maxAngularVelocity){
                player.angular_velocity = player.maxAngularVelocity;
            }else{player.angular_velocity += player.angularAcceleration;}
        }else{player.decelerate(1, 1, player.decelerationRate);}

        if(pressedKeys[32]){ // space pressed
            if(player.shooting_timer <= 0){
                for(int i = 0; i < player.shooting_vertexes.size(); i++){
                    bullets.add(player.shoot(i));
                }
            }
        }
    }
}

