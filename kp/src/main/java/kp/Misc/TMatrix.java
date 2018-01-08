package kp.Misc;

public class TMatrix {
    private double[][] matrix;

    public TMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public TVector transform(TVector vector) {
        double x = vector.getX() * matrix[0][0] + vector.getY() * matrix[1][0] + vector.getZ() * matrix[2][0] + vector.getH() * matrix[3][0];
        double y = vector.getX() * matrix[0][1] + vector.getY() * matrix[1][1] + vector.getZ() * matrix[2][1] + vector.getH() * matrix[3][1];
        double z = vector.getX() * matrix[0][2] + vector.getY() * matrix[1][2] + vector.getZ() * matrix[2][2] + vector.getH() * matrix[3][2];
        double h = vector.getX() * matrix[0][3] + vector.getY() * matrix[1][3] + vector.getZ() * matrix[2][3] + vector.getH() * matrix[3][3];
        return new TVector(x, y, z, h);
    }

    public void multiply(TMatrix m) {
        double[][] t = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    t[i][j] += this.matrix[i][k] * m.getMatrix()[j][k];
                }
            }
        }
        this.matrix = t;
    }

    public static TMatrix rotationY(double angle) {
        double rad = Math.toRadians(angle % 360);
        double[][] t = new double[][]{
                {Math.cos(rad), 0, -Math.sin(rad), 0},
                {0, 1, 0, 0},
                {Math.sin(rad), 0, Math.cos(rad), 0},
                {0, 0, 0, 1}};
        return new TMatrix(t);
    }

    public static TMatrix rotationX(double angle) {
        double rad = Math.toRadians(angle % 360);
        double[][] t = new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(rad), Math.sin(rad), 0},
                {0, -Math.sin(rad), Math.cos(rad), 0},
                {0, 0, 0, 1}};
        return new TMatrix(t);
    }

    public double[][] getMatrix() {
        return matrix;
    }
}