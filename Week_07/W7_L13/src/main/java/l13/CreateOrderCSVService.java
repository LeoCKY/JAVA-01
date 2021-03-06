package l13;

import java.io.File;
import java.io.FileOutputStream;

public class CreateOrderCSVService {
    private final static String TITLE = "user_id,merch_id,order_no,status,product_amount_total,order_amount_total, logistics_fee,pay_time, postcode,delivery_addr,version,create_user,create_date,update_user,update_date,is_del";


    private final static String INSERT_SQL = "\"1\",\"1\",\"2021022812020000\",\"0\",\"123\",\"123\",\"0\",\"2021-03-01 11:05:00\",\"1234\",\"addr0\",\"0\",\"1\",\"2021-03-01 11:05:00\",\"1\",\"null\",\"0\"";

    public static void createFile() {
        File csvFile = new File("C:\\Users\\Leo\\Desktop\\W7\\order.csv");
        try {
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }
            StringBuilder sb = new StringBuilder(TITLE);

            for (int i = 0; i < 1000000; i++) {
                sb.append("\n").append(INSERT_SQL);
            }

            FileOutputStream fos = new FileOutputStream(csvFile);
            fos.write(sb.toString().getBytes());

            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        createFile();
    }
}
