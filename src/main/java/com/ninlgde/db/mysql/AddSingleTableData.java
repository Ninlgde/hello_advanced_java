package com.ninlgde.db.mysql;


import java.sql.*;
import java.util.Random;

public class AddSingleTableData {
    private static final String[] randomStrings = {"abc", "xyz", "sjfks", "sjfee", "23dakdj", "jfksli39",
                                        "jfei399", "jlksf3oi", "ji3nsv", "3ijnmd", "ioufweu",
                                        "j3fnv", "3jfifj", "jlsfien9", "3ffen", "jvi9vsv",
                                        "ksjfie034", "oijfj", "349fnvs", "a", "b", "c", "x", "y", "z"};

    private static final int len = randomStrings.length;

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        String sql;
        PreparedStatement ps = null;

        String conn_str = "jdbc:mysql://127.0.0.1:30306/demo?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");

            conn = DriverManager.getConnection(conn_str);

            sql = "insert into single_table (key1, key2, key3, key_part1, key_part2, key_part3, common_field) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            int num = 0;
            int idx = 0;
            Random r = new Random();
            for (int i = 0; i < 10000; i++) {
                //这里我只是随机赋值，可以将你想要添加的数据放在集合中，使用for插入数据
                //key1赋值
                idx = r.nextInt(len);
                ps.setString(1, randomStrings[idx]);
                //ket2赋值
                ps.setInt(2,  i);
                //key3赋值
                idx = r.nextInt(len);
                ps.setString(3, randomStrings[idx]);
                idx = r.nextInt(len);
                ps.setString(4, randomStrings[idx]);
                idx = r.nextInt(len);
                ps.setString(5, randomStrings[idx]);
                idx = r.nextInt(len);
                ps.setString(6, randomStrings[idx]);
                idx = r.nextInt(len);
                ps.setString(7, randomStrings[idx]);
                num = ps.executeUpdate();
            }

            if (num > 0) {
                //如果插入成功，则打印success
                System.out.println("Sucess");
            } else {
                //如果插入失败，则打印Failure
                System.out.println("Failure");
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
