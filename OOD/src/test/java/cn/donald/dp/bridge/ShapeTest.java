package cn.donald.dp.bridge;

import org.junit.Test;

/**
 * Created by DonaldY on 2017/7/25.
 */
public class ShapeTest {

    @Test
    public void testDraw() {
        Shape circleShape = new Circle(new GraphicLibrary1());
        circleShape.draw();

        Shape retangleShape = new Rectangle(new GraphicLibrary2());
        retangleShape.draw();
    }
}
