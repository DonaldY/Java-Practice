package cn.donald.dp.composite;

/**
 * Created by DonaldY on 2017/7/27.
 */
public class ShapeItem extends ShapeComponent {
    String name;
    String description;

    public ShapeItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public void draw() {
        System.out.print(getName());
        System.out.println(", " + getDescription());
    }
}
