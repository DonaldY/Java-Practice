package cn.donald.dp.bridge;

/**
 * Created by DonaldY on 2017/7/25.
 */
public class Circle extends Shape {

    public Circle(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void draw() {
        this.drawing.drawCircle();
    }
}
