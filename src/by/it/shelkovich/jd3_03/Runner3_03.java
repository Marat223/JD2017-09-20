package by.it.shelkovich.jd3_03;

import by.it.shelkovich.jd3_03.dao.DAO;
import by.it.shelkovich.jd3_03.dao.IDAO;
import by.it.shelkovich.jd3_03.dao.UniDAO;
import by.it.shelkovich.jd3_03.dao.entities.FollowLink;
import by.it.shelkovich.jd3_03.dao.entities.Post;
import by.it.shelkovich.jd3_03.dao.entities.Role;
import by.it.shelkovich.jd3_03.dao.entities.User;
import by.it.shelkovich.jd3_03.dao.jaxbBeans.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Runner3_03 {
    public static void main(String[] args) {
        DAO dao = DAO.getInstance();
        //Задание А
        //Вывод пользователей с ролями на экран
        //В пакете дао есть CRUD юнит-тест

        try {

            List<User> users = dao.userDAO.getAll();
            for (User user: users){
                Role role = dao.roleDAO.read(user.getRole_id());
                System.out.println("User: "+user.getUsername()+" | Role: "+role.getRole());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
