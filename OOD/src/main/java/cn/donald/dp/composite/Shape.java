package cn.donald.dp.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by DonaldY on 2017/7/24.
 */
public class Shape extends ShapeComponent{

    String name;
    String description;
    ArrayList<ShapeComponent> shapes = new ArrayList<>();

    public Shape(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(ShapeComponent shape) {
        this.shapes.add(shape);
    }

    public void remove(ShapeComponent shape) {
        this.shapes.remove(shape);
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
        System.out.println("---------------------");

        Iterator iter = this.shapes.iterator();
        while (iter.hasNext()) {
            ShapeComponent shape = (ShapeComponent)iter.next();
            shape.draw();
        }
    }

}
