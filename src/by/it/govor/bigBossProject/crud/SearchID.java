package by.it.govor.bigBossProject.crud;



import by.it.govor.bigBossProject.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchID {
    public static void main(String[] args) {
       try(Connection connection = ConnectionCreator.getConnection();
           Statement statement = connection.createStatement()){
           ResultSet resourceSet = statement.executeQuery("SELECT role_ID,Name FROM govor.user where Name='Kto-to'");
           while (resourceSet.next()){
               System.out.println(resourceSet.getString("role_ID")+":"+resourceSet.getString("Name"));
           }
       }catch (Exception e){}
    }
}


