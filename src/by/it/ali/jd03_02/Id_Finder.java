package by.it.ali.jd03_02;

import by.it.ali.jd03_01.INF;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

public class Id_Finder {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL,INF.USER,INF.PASSWORD);
                Statement statement = connection.createStatement();)
        {ResultSet resultSet=null;
            Scanner sc = new Scanner(System.in);
            System.out.println("ID какой роли Вас интересует? Administrator, User, Guest?");
            String role=sc.nextLine();
            switch (role){
                case "Administrator":
                    resultSet=statement.executeQuery("SELECT ID from roles where Role='Administrator' ");
                    break;
                case "User":
                    resultSet=statement.executeQuery("SELECT ID from roles where Role='User' ");
                    break;
                case "Guest":
                    resultSet=statement.executeQuery("SELECT ID from roles where Role='Guest' ");
                    break;
                default:
                    System.out.println("Такой роли не существует!");
                    break;
            }

            while (resultSet.next()){
            System.out.println("ID:"+resultSet.getString("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//            while (resultSet.next()){
//                String out=resultSet.getString("Login")+", "+resultSet.getString("Email")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("FKroles_additional");
//                System.out.println(out);
//            }