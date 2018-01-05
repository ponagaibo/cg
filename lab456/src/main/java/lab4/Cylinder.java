package lab4;

import lab4.OpenGlMisc.Triangle;
import lab4.OpenGlMisc.Shader;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import java.util.ArrayList;
import java.util.List;

public class Cylinder {
    private double height;
    private double radius;
    private int approx;
    private int sides;
    private List<Triangle> triangles;

    public Cylinder(double r, int sides, int approx) {
        this.sides = sides;
        this.radius = r;
        this.height = 50;
        this.approx = approx;
        triangles = new ArrayList<>();
        generateTriangles();
    }

    private void generateTriangles() {
        float part = 0;
        ArrayList<ArrayList<Vector4f>> list = new ArrayList<>();
        for (int i = 0; i <= approx; i++) {
            double radius = getR();
            double cur = 0;
            ArrayList<Vector4f> layer = new ArrayList<>();
            for (int j = 0; j < sides; j++) {
                float x = (float) (radius * Math.cos(cur * Math.PI / 180));
                float y = (float) (radius * Math.sin(cur * Math.PI / 180));
                layer.add(new Vector4f(x / 50, y / 50, part / 50, 1));
                cur += 360. / sides;
            }
            part += height / approx;
            list.add(layer);
        }
        float last = list.get(list.size() - 1).get(0).z;
        Vector4f center1 = new Vector4f(0, 0, 0, 1);
        Vector4f center2 = new Vector4f(0, 0, last, 1);
        for (int i = 0; i < sides; i++) {
            Vector4f v1 = list.get(0).get(i % sides);
            Vector4f v2 = list.get(0).get((i + 1) % sides);
            triangles.add(new Triangle(new float[]{
                    center1.x, center1.y, center1.z,
                    v1.x, v1.y, v1.z,
                    v2.x, v2.y, v2.z,
            }));

            v1 = list.get(list.size() - 1).get(i % sides);
            v2 = list.get(list.size() - 1).get((i + 1) % sides);
            triangles.add(new Triangle(new float[]{
                    center2.x, center2.y, center2.z,
                    v1.x, v1.y, v1.z,
                    v2.x, v2.y, v2.z,
            }));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.get(0).size(); j++) {
                Vector4f v1 = list.get(i).get(j % sides);
                Vector4f v2 = list.get(i).get((j + 1) % sides);
                Vector4f v3 = list.get(i + 1).get(j % sides);
                triangles.add(new Triangle(new float[]{
                        v1.x, v1.y, v1.z,
                        v2.x, v2.y, v2.z,
                        v3.x, v3.y, v3.z,
                }));
                v1 = list.get(i + 1).get(j % sides);
                v2 = list.get(i + 1).get((j + 1) % sides);
                v3 = list.get(i).get((j + 1) % sides);
                triangles.add(new Triangle(new float[]{
                        v1.x, v1.y, v1.z,
                        v2.x, v2.y, v2.z,
                        v3.x, v3.y, v3.z,
                }));
            }
        }
    }

    void draw(Matrix4f matrix, Shader shader) {
        for (Triangle tr : triangles) {
            tr.render(matrix, shader);
        }
    }

    public double getR() {
        return radius;
    }
}
