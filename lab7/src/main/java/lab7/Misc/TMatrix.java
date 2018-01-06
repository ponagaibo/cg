package lab7.Misc;

public class TMatrix {
    private final double[][] matrix;

    public TMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public TVector transform(TVector vector) {
        double x = vector.getX() * matrix[0][0] + vector.getY() * matrix[1][0] + vector.getH() * matrix[2][0];
        double y = vector.getX() * matrix[0][1] + vector.getY() * matrix[1][1] + vector.getH() * matrix[2][1];
        double h = vector.getX() * matrix[0][2] + vector.getY() * matrix[1][2] + vector.getH() * matrix[2][2];
        return new TVector(x, y, h);
    }

    public double[][] getMatrix() {
        return matrix;
    }
}