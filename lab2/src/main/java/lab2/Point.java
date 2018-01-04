package lab2;

public class Point {
    public Point() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point Copy(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        return this;
    }

    public void PrintPoint() {
        System.out.print("x: ");
        System.out.print(x);
        System.out.print("  y: ");
        System.out.print(y);
        System.out.print("  z: ");
        System.out.println(z);
    }

    public Point MultiplyByMatrix(Matrix matr) {
        double[][] matrix = matr.GetMatrix();
        double c1 = x * matrix[0][0] + y * matrix[1][0] + z * matrix[2][0];
        double c2 = x * matrix[0][1] + y * matrix[1][1] + z * matrix[2][1];
        double c3 = x * matrix[0][2] + y * matrix[1][2] + z * matrix[2][2];
        return new Point(c1, c2, c3);
    }

    public double ScalarProduct(Point a) {
        return x * a.GetX() + y * a.GetY() + z * a.GetZ();
    }

    public double ScalarProduct(Point a, Point b) {
        return a.GetX() * b.GetX() + a.GetY() * b.GetY() + a.GetZ() * b.GetZ();
    }

    public Point CrossProduct(Point a) {
        double c1 = y * a.GetZ() - z * a.GetY();
        double c2 = z * a.GetX() - x * a.GetZ();
        double c3 = x * a.GetY() - y * a.GetX();
        return new Point(c1, c2, c3);
    }

    public Point CrossProduct(Point a, Point b) {
        double c1 = a.GetY() * b.GetZ() - a.GetZ() * b.GetY();
        double c2 = a.GetZ() * b.GetX() - a.GetX() * b.GetZ();
        double c3 = a.GetX() * b.GetY() - a.GetY() * b.GetX();
        return new Point(c1, c2, c3);
    }

    public double MixedProduct(Point a, Point b) {
        return ScalarProduct(this, CrossProduct(a, b));
    }

    public double MixedProduct(Point a, Point b, Point c) {
        return ScalarProduct(a, CrossProduct(b, c));
    }

    public Point Add(Point p) {
        return new Point(x + p.x, y + p.y, z + p.z);
    }

    public Point Substract(Point p) {
        return new Point(x - p.x, y - p.y, z - p.z);
    }

    public double GetX() {
        return x;
    }

    public double GetY() {
        return y;
    }

    public double GetZ() {
        return z;
    }

    private double x;
    private double y;
    private double z;
}
