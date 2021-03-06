package l13;

import connect.HikariUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    private final static String INSERT_SQL = "insert tb_order (`user_id`,`merch_id`, `order_no`,`status`" +
            ",`product_amount_total`,`order_amount_total`, `logistics_fee`,`pay_time`" +
            ", `postcode`,`delivery_addr`,`version`,`create_user`,`create_date`,`update_user`,`update_date`,`is_del`"+
            ") values(?,1,'2021022812020000',0,123,123,0,null,'1234','addr0',0,1,null,1,null,0)";


    private final static  String LOAD_DATA_SQL = "LOAD DATA LOCAL INFILE  'C:\\\\Users\\\\Leo\\\\Desktop\\\\W7\\\\order.csv' " +
                                                 " INTO TABLE tb_order FIELDS TERMINATED BY ',' ENCLOSED BY '\"' IGNORE 1 LINES;";


    public static void insertList() {
        Long start = System.currentTimeMillis();
        //从连接池中获取connection
        Connection conn =  HikariUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL);

            for (int i = 0; i < 1000000; i++) {
                ps.setInt(1,1);
                ps.addBatch();
                //小批量提交,避免OOM
                if((i+1) % 5000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            //提交剩余的数据
            ps.executeBatch();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //使用完以后放回连接池
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("insert cost time = " + (System.currentTimeMillis()-start) + "ms");
    }


    public static void loadData() {
        Long start = System.currentTimeMillis();
        //从连接池中获取connection
        Connection conn =  HikariUtil.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(LOAD_DATA_SQL);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //使用完以后放回连接池
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("loadData cost time = " + (System.currentTimeMillis()-start) + "ms");
    }


}
