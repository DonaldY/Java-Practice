package cn.donald.dp.composite;

/**
 * Created by DonaldY on 2017/7/27.
 */
public abstract class ShapeComponent{

    public void add(ShapeComponent shapeComponent) {
        throw new UnsupportedOperationException();
    }
    public void remove(ShapeComponent shapeComponent) {
        throw new UnsupportedOperationException();
    }
    public ShapeComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public void draw() {
        throw new UnsupportedOperationException();
    }

}
