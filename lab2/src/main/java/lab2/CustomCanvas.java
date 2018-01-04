package lab2;

import javafx.scene.canvas.Canvas;

public class CustomCanvas extends Canvas{

    @Override
    public void resize(double width,double height) {
        setHeight(height);
        setWidth(width);
    }

    @Override
    public boolean isResizable(){
        return true;
    }
}
