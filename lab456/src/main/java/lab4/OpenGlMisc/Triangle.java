package lab4.OpenGlMisc;

import lab4.Lab;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Triangle {
    private int descript;
    private float[] verts;

    public Triangle(float[] vertices) {
        verts = vertices;
        FloatBuffer fBuffer = BufferUtils.createFloatBuffer(vertices.length);
        fBuffer.put(vertices);
        fBuffer.flip();

        descript = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, descript);
        glBufferData(GL_ARRAY_BUFFER, fBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private double scalarProduct(Vector4f a, Vector4f b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    private Vector4f vectorProduct(Vector4f a, Vector4f b) {
        return new Vector4f(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x,
                1);
    }

    public void render(Matrix4f matrix, Shader shader) {
        double d = -1;
        if (matrix != null) {
            Vector4f toObserver = new Vector4f(0, 0, 1, 0);
            Vector4f inner = new Vector4f(0, 0, 0.01f, 0);
            Vector4f v1 = new Vector4f(verts[0], verts[1], verts[2], 0);
            Vector4f v2 = new Vector4f(verts[3], verts[4], verts[5], 0);
            Vector4f v3 = new Vector4f(verts[6], verts[7], verts[8], 0);
            matrix.transform(v1);
            matrix.transform(v2);
            matrix.transform(v3);
            matrix.transform(inner);

            Vector4f sb1 = v2.sub(v1);
            Vector4f sb2 = v3.sub(v1);
            Vector4f norm = vectorProduct(sb1, sb2);
            Vector4f toInner = inner.sub(v1);
            if (scalarProduct(norm, toInner) > 0) {
                norm.negate();
            }
            norm.normalize();
            if (scalarProduct(norm, toObserver) > 0) {
                d = -1;
                shader.setUniform("Normal", norm);
            } else {
                d = 1;
            }
        }
        if (d < 0) {
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, descript);
            glVertexPointer(3, GL_FLOAT, 0, 0);
            shader.setUniform("col", new Vector4f(Lab.red / 255.f, Lab.green / 255.f, Lab.blue / 255.f, 1));
            int drawCount = 3;
            glDrawArrays(GL_TRIANGLES, 0, drawCount);
            shader.setUniform("col", new Vector4f(0, 0, 0, 1));
            glDrawArrays(GL_LINE_LOOP, 0, drawCount);
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glDisableClientState(GL_VERTEX_ARRAY);
        }
    }
}
