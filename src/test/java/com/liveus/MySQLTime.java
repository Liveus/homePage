package com.liveus;

import java.sql.*;

public class MySQLTime
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?useUnicode\\=true&characterEncoding\\=utf-8&useSSL\\=false","root","shen0410");
            String sql = "insert  into Redis value ('1','2')";
            Statement statement = connection.createStatement();
            long startTime=System.currentTimeMillis();
            System.out.println(startTime);
            for(int i = 0;i<1000;i++){
                statement.executeUpdate(sql);
            }
            long endTime=System.currentTimeMillis();
            System.out.println(endTime);
            System.out.println(endTime-startTime);

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}