import java.lang.Math;

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double getY() {
        return this.y;
    }

    private double getX() {
        return this.x;
    }
    
    public String toString() {
        String xRounded = String.format("%.3f", this.getX());
        String yRounded = String.format("%.3f", this.getY());
        return "point (" + xRounded + ", " + yRounded + ")";
    }
    
    public Point midPoint(Point p) {
        return new Point((this.getX() + p.getX()) / 2, (this.getY() + p.getY()) / 2);
    }

    public double angleTo(Point p) {
        double numerator = p.getY() - this.getY();
        double denominator = p.getX() - this.getX();
        double degree = Math.atan(Math.abs(numerator) / Math.abs(denominator));

        boolean positiveY = numerator >= 0;
        boolean positiveX = denominator >= 0;

        if (numerator == 0 && denominator == 0) { 
            return 0;
        } else if (positiveY && positiveX) {
            return degree;
        } else if (positiveY && !positiveX) { 
            return Math.PI - degree;
        } else if (!positiveY && !positiveX) {
            return degree - Math.PI;
        } else {
            return -degree;
        }
    }

    public Point moveTo(double degree, double distance) {
        double newX = this.getX() + distance * Math.cos(degree);
        double newY = this.getY() + distance * Math.sin(degree);
        return new Point(newX, newY);
    }

    public double midPointToCentre(Point p) {
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();
        double mLength = this.distanceTo(p) / 2;
        double dLength = Math.sqrt(1 - (mLength * mLength));
        return dLength;
    }

    public Point findCentre(Point p) {
        double cm = this.midPointToCentre(p);
        double theta = this.angleTo(p);
        Point midPoint = this.midPoint(p);
        return midPoint.moveTo(theta + Math.PI / 2, cm);
    }

    public double distanceTo(Point p) {
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
