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
        User user;

        try {
//            //user = dao.userDAO.read(1);
//            Roles roles = new Roles(dao.roleDAO.getAll());
//            //System.out.println(users);
//
//            JAXBContext jc = JAXBContext.newInstance(Roles.class);
//            Marshaller m=jc.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            File file = new File("src/by/it/shelkovich/jd3_03/xmlSource/Roles.xml");
//            FileOutputStream os = new FileOutputStream(file);
//            m.marshal(roles,os);




        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
