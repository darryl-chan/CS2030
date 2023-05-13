import java.lang.Math;

class Sphere implements Shape3D {
    private final double radius;
    private static final double numerator = 4;
    private static final double denominator = 3;
    private static final double multiplier = 3;

    Sphere(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double volume() {
        double coefficient = this.numerator * Math.PI / this.denominator;
        return coefficient * Math.pow(this.radius, this.multiplier);
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }
}
