package cn.donald.dp.bridge;

/**
 * Created by DonaldY on 2017/7/25.
 */
public class Rectangle extends Shape {

    public Rectangle(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void draw() {
        this.drawing.drawLine();
    }
}
