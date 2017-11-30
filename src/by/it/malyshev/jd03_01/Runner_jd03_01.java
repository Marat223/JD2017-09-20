package by.it.malyshev.jd03_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner_jd03_01 {

    public static void main(String[] args) {

        System.out.println("Создали базу");
        C_Init.main(null);
        System.out.print("\n\nНажмите ВВОД, чтобы продолжить >");

        getCommandLine();

        System.out.println("Добавили нового пользователя");
        A_AddUser.main(null);
        System.out.print("\n\nНажмите ВВОД, чтобы продолжить >");
        getCommandLine();

        System.out.println("Добавили данные в другие таблицы");
        A_AddData.main(null);
        System.out.print("\n\nНажмите ВВОД, чтобы продолжить >");
        getCommandLine();

        System.out.println("Добавили новые роли");
        B_AddRoles.main(null);
        System.out.print("\n\nНажмите ВВОД, чтобы продолжить >");
        getCommandLine();

        System.out.println("Вывели список пользователей и их ролей");
        B_ShowUsers.main(null);
        System.out.print("\n\nНажмите ВВОД, чтобы продолжить >");
        getCommandLine();

        System.out.println("Удалили базу");
        C_Reset.main(null);

    }

    private static void getCommandLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

