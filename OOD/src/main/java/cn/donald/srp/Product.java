package cn.donald.srp;

/**
 * Created by DonaldY on 2017/6/14.
 */
public class Product {

    private String id;
    private String desc;

    public Product(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id='" + id + '\'' +
            ", desc='" + desc + '\'' +
            '}';
    }
}
