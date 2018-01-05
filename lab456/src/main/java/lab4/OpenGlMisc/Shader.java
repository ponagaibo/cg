package lab4.OpenGlMisc;

import org.joml.Vector4f;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import java.io.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int program;
    private int vertShader;
    private int fragShader;

    public Shader(String fileName) {
        program = glCreateProgram();
        vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, readFile(fileName + ".vs"));
        glCompileShader(vertShader);
        if (glGetShaderi(vertShader, GL_COMPILE_STATUS) != 1) {
            System.err.println(glGetShaderInfoLog(vertShader));
            System.exit(1);
        }
        fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, readFile(fileName + ".fs"));
        glCompileShader(fragShader);
        if (glGetShaderi(fragShader, GL_COMPILE_STATUS) != 1) {
            System.err.println(glGetShaderInfoLog(fragShader));
            System.exit(1);
        }

        glAttachShader(program, vertShader);
        glAttachShader(program, fragShader);
        glBindAttribLocation(program, 0, "vert");
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            System.exit(1);
        }
        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            System.exit(1);
        }
    }

    public void setUniform(String name, float value) {
        int location = glGetUniformLocation(program, name);
        if (location != -1) {
            glUniform1f(location, value);
        }
    }

    public void setUniform(String name, Matrix4f value) {
        int location = glGetUniformLocation(program, name);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        value.get(buffer);
        if (location != -1) {
            glUniformMatrix4fv(location, false, buffer);
        }
    }

    public void setUniform(String name, Vector4f value) {
        int location = glGetUniformLocation(program, name);
        if (location != -1) {
            glUniform3f(location, value.x, value.y, value.z);
        }
    }

    public void bind() {
        glUseProgram(program);
    }

    private String readFile(String fileName) {
        StringBuilder string = new StringBuilder();
        try {
            InputStream is = Shader.class.getResourceAsStream("/shaders/" + fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                string.append(line);
                string.append("\n");
            }
            is.close();
            isr.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }
}
