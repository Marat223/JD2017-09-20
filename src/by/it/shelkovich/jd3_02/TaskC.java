package by.it.shelkovich.jd3_02;

import by.it.shelkovich.jd2_10.bean.Payment;
import by.it.shelkovich.jd3_01.DataBase;
import by.it.shelkovich.jd3_03.dao.DAO;
import by.it.shelkovich.jd3_03.dao.entities.*;
import by.it.shelkovich.jd3_03.dao.jaxbBeans.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskC {

    static void createDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement statement = conn.createStatement();
            String query = "CREATE DATABASE IF NOT EXISTS shelkovich DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
            statement.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteTables(){
        DataBase.exeChangeQuery("DROP TABLE IF EXISTS comments");
        DataBase.exeChangeQuery("DROP TABLE IF EXISTS follow_links");
        DataBase.exeChangeQuery("DROP TABLE IF EXISTS posts");
        DataBase.exeChangeQuery("DROP TABLE IF EXISTS users");
        DataBase.exeChangeQuery("DROP TABLE IF EXISTS roles");
    }

    static void createTables(){
        File file = new File("src/by/it/shelkovich/jd3_02/createTables.sql");
        String[] queries = parse(file);
        for(String query: queries){
            DataBase.exeChangeQuery(query);
        }
    }

    static void createIndexesIncrementsRestricts(){
        File file = new File("src/by/it/shelkovich/jd3_02/createTableProp.sql");
        String[] queries = parse(file);
        for(String query: queries){
            DataBase.exeChangeQuery(query);
        }
    }

    private static String[] parse(File file){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                if (line.startsWith("--")) continue;
                if (line.startsWith("/*")) continue;
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] queries = sb.toString().split(";");
        return queries;
    }

    static void fillTables(){
        fillTable("src/by/it/shelkovich/jd3_02/xmlSource/Roles.xml", Roles.class);
        fillTable("src/by/it/shelkovich/jd3_02/xmlSource/Users.xml", Users.class);
        fillTable("src/by/it/shelkovich/jd3_02/xmlSource/Posts.xml", Posts.class);
        fillTable("src/by/it/shelkovich/jd3_02/xmlSource/Comments.xml", Comments.class);
        fillTable("src/by/it/shelkovich/jd3_02/xmlSource/FollowLinks.xml", FollowLinks.class);
    }

    
    private static void fillTable(String path, Class cl){
        try {
            JAXBContext jc = JAXBContext.newInstance(cl);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(path);
            List list = new ArrayList();
            switch (cl.getSimpleName()){
                case "Users":
                    Users users = (Users) u.unmarshal(reader);
                    list = users.getUsers();
                    break;
                case "Comments":
                    Comments comments = (Comments) u.unmarshal(reader);
                    list = comments.getComments();
                    break;
                case "Posts":
                    Posts posts = (Posts) u.unmarshal(reader);
                    list = posts.getPosts();
                    break;
                case "FollowLinks":
                    FollowLinks followLinks = (FollowLinks) u.unmarshal(reader);
                    list = followLinks.getFollowLinks();
                    break;
                case "Roles":
                    Roles roles = (Roles) u.unmarshal(reader);
                    list = roles.getRoles();
                    break;
            }


            DAO dao = DAO.getInstance();
            for(Object obj: list){
                switch (cl.getSimpleName()){
                    case "Users":
                        dao.userDAO.create((User)obj);
                        break;
                    case "Comments":
                        dao.commentDAO.create((Comment)obj);
                        break;
                    case "Posts":
                        dao.postDAO.create((Post)obj);
                        break;
                    case "FollowLinks":
                        dao.followLinkDAO.create((FollowLink) obj);
                        break;
                    case "Roles":
                        dao.roleDAO.create((Role)obj);
                        break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
