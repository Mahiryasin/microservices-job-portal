public class Radius extends Figure {

    private double radius;
    private static final double CONSTANT_PI=Math.PI;
    

    public Radius(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return CONSTANT_PI*Math.pow(radius, 2);
    }

}
