package by.it.shelkovich.jd3_02;

import by.it.shelkovich.jd3_03.dao.DAO;
import by.it.shelkovich.jd3_03.dao.entities.*;
import by.it.shelkovich.jd3_03.dao.jaxbBeans.Users;

import java.sql.SQLException;
import java.util.List;

public class Runner3_02 {
    public static void main(String[] args) {

        TaskC.createDB();
        TaskC.deleteTables();
        TaskC.createTables();
        TaskC.fillTables();
        TaskC.createIndexesIncrementsRestricts();
        TaskA.run();
        TaskB.run();


    }
}
