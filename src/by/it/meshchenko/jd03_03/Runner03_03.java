package by.it.meshchenko.jd03_03;

import by.it.meshchenko.jd03_02.TaskB;
import by.it.meshchenko.jd03_03.beans.User;
import by.it.meshchenko.jd03_03.dao.DAO;

public class Runner03_03 {

    public static void main(String[] args) {
        // А.
        // Обязательно! Для разработанных таблиц и страниц курсового проекта реализуйте
        // набор DAO-классов с CRUD.
        // Опционально: используйте разработанные макеты страниц для формирования
        //      Нестандартных запросов к базе.
        //      Нестандартных beans.
        //  Разместите наборы операций DAO в одноименном пакете
        //      by.it.ваша_фамилия.JD03_03.dao
        //  Разработайте программу, которая выводит всех пользователей с их ролями на
        // консоль.
        TaskB.main(null);
        // Для корректной проверки используйте следующий порядок (как в примере выше):
        //      Read (вывод наконсоль всех записей).
        //      Create+ Read (вывод наконсоль созданной записи).
        //      Update+ Read (вывод наконсоль обновленной записи).
        //      Delete+ Read (вывод наконсоль всех оставшихся записей).

        System.out.println("\nTEST UserDAO");
        System.out.println("**** ALL Users");
        DAO dao = DAO.getInstance();
        User user = new User(0,"testUser","qwerty",null,
                "TEST@guru.ru",false, 1);

        for(User item : dao.user.getAll("")){
            System.out.println(item);
        }
        System.out.println("**** Create User");
        dao.user.create(user);
        dao.user.read(user.getId());
        for(User item : dao.user.getAll("")){
            System.out.println(item);
        }
        System.out.println("**** Update User");
        user.setName("test#1");
        dao.user.update(user);
        dao.user.read(user.getId());
        System.out.println(user);

        System.out.println("**** Delete User");
        dao.user.delete(user);
        for(User item : dao.user.getAll("")){
            System.out.println(item);
        }


        // B.
        //  В классах DAO предусмотрите объект singletonдля экземпляра DAO и
        // общий интерфейсDAO для beans.

        // C.
        //  Реализуйте универсальный DAO<T> CRUD + R(всех) + R(по id) для произвольных
        // beans со стандартными полями (примитивы, дата, строки).
    }
}
