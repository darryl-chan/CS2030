class Circle {
    private final Point centre;
    private final double radius;
    private static final double epsilon = 1E-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    private Point getCentre() {
        return this.centre;
    }

    private double getRadius() {
        return this.radius;
    }

    public String toString() {
        return "circle of radius " + radius + " centred at " + this.getCentre().toString();
    }

    public boolean contains(Point p) {
        return (radius + epsilon > this.getCentre().distanceTo(p));
    }
}
