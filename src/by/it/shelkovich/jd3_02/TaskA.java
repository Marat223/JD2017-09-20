package by.it.shelkovich.jd3_02;

import by.it.shelkovich.jd3_03.dao.DAO;
import by.it.shelkovich.jd3_03.dao.entities.Role;
import by.it.shelkovich.jd3_03.dao.entities.User;

import java.util.List;

public class TaskA {
    static void run() {
        DAO dao = DAO.getInstance(); //ДАО из jd3_03

        try {
            Role role = new Role();
            role.setRole("Пользователь");
            List<Role> roles = dao.roleDAO.read(role);
            System.out.println("ID роли с именем 'Пользователь': "+roles.get(0).getId());

            User user = new User();
            user.setId(0L);
            user.setUsername("petrovich");
            user.setEmail("petrovich@gmail.com");
            user.setRole_id(2L);
            user.setPassHash("123456");
            user.setShowPersonalData(false);

            dao.userDAO.create(user);
            System.out.println("Созданный пользователь: "+user);


            user = dao.userDAO.read(user.getId());
            System.out.println("Прочитанный пользователь: "+user);

            user.setDescription("Сантехник, которых свет не видывал");
            dao.userDAO.update(user);
            user = dao.userDAO.read(user.getId());
            System.out.println("Обновлённый пользователь: "+user);
            boolean result = dao.userDAO.delete(user.getId());
            System.out.println("Результат операции удаления: "+result);

            user = dao.userDAO.read(user.getId());
            if(user==null) System.out.println("Такого пользователя в БД нет");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
