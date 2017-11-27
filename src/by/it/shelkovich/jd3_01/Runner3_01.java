package by.it.shelkovich.jd3_01;

public class Runner3_01 {
    public static void main(String[] args) {
        //Закомментировал, потому что эти классы ничего не будут делать, пока нет таблиц, а по заданию, вроде, таблицы надо создавать в СУБД
        //A_AddUser.run();
        //A_AddData.run();

        //B_AddRole.run();
        //B_ShowUsers.run();

        //C_Reset.run();
        C_Init init = new C_Init("src/by/it/shelkovich/jd3_01/shelkovich.sql");
        init.run();
        B_ShowUsers.run();


    }
}
