package cn.ninetailfox.weixin;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ssmdemo?useSSL=false";
        String user = "root";
        String password = "123456";

        String sql = "select * from tb_user where id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
