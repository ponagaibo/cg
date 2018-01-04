package lab2;

public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix() {
        double[][] tmp = { {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1} };
        matrix = tmp;
    }

    public void RotationX(double angle) {
        double ang = (angle % 360) * Math.PI / 180;
        double[][] tmp = { {1, 0, 0, 0},
                           {0, Math.cos(ang), Math.sin(ang), 0},
                           {0, -Math.sin(ang), Math.cos(ang), 0},
                           {0, 0, 0, 1}
                         };
        matrix = tmp;
    }

    public void RotationY(double angle) {
        double ang = (angle % 360) * Math.PI / 180;
        double[][] tmp = { {Math.cos(ang), 0, -Math.sin(ang), 0},
                {0, 1, 0, 0},
                {Math.sin(ang), 0, Math.cos(ang), 0},
                {0, 0, 0, 1}
        };
        matrix = tmp;
    }

    public void RotationZ(double angle) {
        double ang = (angle % 360) * Math.PI / 180;
        double[][] tmp = { {Math.cos(ang), Math.sin(ang), 0, 0},
                           {-Math.sin(ang), Math.cos(ang), 0, 0},
                           {0, 0, 1, 0},
                           {0, 0, 0, 1}
                         };
        matrix = tmp;
    }

    public void PrintMatrix() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public double[][] GetMatrix() {
        return matrix;
    }

    public Point GetPointC() {
        Point tmp = new Point(matrix[0][0], matrix[1][0], matrix[2][0]);
        return tmp;
    }

    public Point GetPointS() {
        Point tmp = new Point(matrix[0][0], matrix[0][1], matrix[0][2]);
        return tmp;
    }

    public void Myltiply(Matrix m) {
        double[][] temp = { {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    temp[i][j] += matrix[i][k] * m.GetMatrix()[k][j];
                }
            }
        }
        matrix = temp;
    }
}
