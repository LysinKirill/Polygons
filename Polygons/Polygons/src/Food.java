public class Food {
    double energy;
    Vec2d pos;
    Food(){
        this.energy = 1;
        this.pos = new Vec2d(Math.random()*Main.width, Math.random()*Main.height);
    }
    Food(double energy, Vec2d pos){
        this.energy = energy;
        this.pos = pos;
    }
}