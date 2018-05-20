package cn.donald.dp.composite;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by DonaldY on 2017/7/26.
 */
public class PictureTest {

    @Test
    public void testPicture() {

        ShapeComponent picShape = new Shape("Geshow", "This is a picture of GeGe.");

        ShapeComponent line = new ShapeItem("Straight-line", "The only straight line.");

        ShapeComponent rectangle = new ShapeItem("Rectangle", "The only rectangle");

        picShape.add(line);
        picShape.add(rectangle);

        picShape.draw();
    }

    @Test
    public void testBigDecimal() {

        Double a = 111111111111111111111111.11111;

        long l = Double.doubleToLongBits(a);

        System.out.println(a);

        System.out.println(Long.toBinaryString(l));

    }


}
