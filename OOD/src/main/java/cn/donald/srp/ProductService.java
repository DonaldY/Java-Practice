package cn.donald.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DonaldY on 2017/6/22.
 */
public class ProductService {

    public List<Product> getPromotionProduct() throws IOException {

        File file = new File("D:\\tools\\Code\\Y_Repository\\Java-Practice\\OOD\\src\\cn\\donald\\srp" +
            "\\product_promotion.txt");


        return this.readFile(file);

    }

    private List<Product> readFile(File file) throws IOException {

        BufferedReader br = null;

        try {
            List<Product> list = new ArrayList<>();

            br = new BufferedReader(new FileReader(file));

            while (br.read() != -1) {

                String temp = br.readLine();

                String[] data = temp.split(" ");

                System.out.println("产品ID = " + data[0]);
                System.out.println("产品描述 = " + data[1] + "\n");



                list.add(new Product(data[0], data[1]));

            }

            return list;

        } catch (IOException e) {

            e.printStackTrace();

            return Collections.emptyList();

        } finally {

            br.close();

        }

    }


}
