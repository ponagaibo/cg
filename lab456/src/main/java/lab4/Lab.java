package lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lab4.OpenGlMisc.Shader;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Lab extends Application {
    private long window;
    private Matrix4f matrix = new Matrix4f().identity();
    private double prevX, prevY;
    public static float red = 40, green = 50, blue = 80;
    public static float ambK = 1, difK = 1, specK = 1;
    public static Cylinder cylinder;
    public static Shader shader;

    private void startPoint() {
        initialize();
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void initialize() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(600, 600, "Лабораторная работа №4-6", 0, 0);
        glfwSetWindowSizeLimits(window, 400, 400, 900, 900);

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
                System.exit(0);
            }
        });

        glfwSetFramebufferSizeCallback(window, (win, w, h) -> {
            glViewport(0, 0, w, h);
            glOrtho(0, w, h, 0, -1, 1);
        });

        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS) {
                DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
                DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
                glfwGetCursorPos(win, posX, posY);
                double x = posX.get(0);
                double y = posY.get(0);
                prevX = x;
                prevY = y;
            }
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0, 0, 0, 0.0f);
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        matrix.scale(0.8f);
        shader = new Shader("first");
        cylinder = new Cylinder(20, 15, 10);
        int angle = 0;
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadMatrixf(matrix.get(fb));
            if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
                DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
                DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
                glfwGetCursorPos(window, posX, posY);
                double curX = posX.get(0);
                double curY = posY.get(0);
                int dy = (int) (curX - prevX);
                int dx = (int) (curY - prevY);
                matrix.rotateX((float) Math.toRadians(dx));
                matrix.rotateY((float) Math.toRadians(dy));
                prevX = curX;
                prevY = curY;
            }

            shader.bind();
            shader.setUniform("project", matrix);
            shader.setUniform("alpha", (float) Math.sin(Math.toRadians(angle)));
            shader.setUniform("ambK", ambK);
            shader.setUniform("diffK", difK);
            shader.setUniform("specK", specK);
            cylinder.draw(matrix, shader);
            angle++;
            angle %= 360;
            glfwSwapBuffers(window);
            glfwPollEvents();
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/scheme/menu.fxml"));
        final Scene scene = new Scene(root);
        stage.setTitle("Лабораторная работа №4-6(Цилиндр), Понагайбо Анастасия");
        stage.setScene(scene);
        stage.setMinWidth(scene.getWindow().getWidth());
        stage.setMinHeight(scene.getWindow().getHeight());
        stage.show();
        Thread t = new Thread(() -> new Lab().startPoint());
        t.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}