package by.it.meshchenko.jd03_02;

import by.it.meshchenko.jd03_01.A_AddData;
import by.it.meshchenko.jd03_01.A_AddUsers;
import by.it.meshchenko.jd03_01.B_AddRoles;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class TaskC_AddData {
    public static void main(String[] args){
        System.out.println("Добавляем данные");
        A_AddData.main(null);
        System.out.println("OK!");
        System.out.println("Добавляем пользователей");
        A_AddUsers.main(null);
        System.out.println("OK!");
        System.out.println("Добавляем роли и корректируем добавленных пользователей");
        B_AddRoles.main(null);
        System.out.println("OK!");
    }
}

class TaskC_Init {
    public static void main(String[ ] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {

            //пересоздание базы целиком
            statement.executeUpdate("DROP SCHEMA IF EXISTS `meshchenko` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `meshchenko` DEFAULT CHARACTER SET utf8 ;");

//            //удаление всех таблиц (если работает удаление схемы то это лишнее)
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_countries` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_countries` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_cities` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_streets` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_addresses` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`crm_roles` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`crm_users` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_shoppingcenters` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_rooms` ;");
//            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_leaserooms` ;");

            //генерация всех таблиц
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_countries` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_cities` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  `CounrtyId` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Name_CountryId_UNIQUE` (`Name` ASC, `CounrtyId` ASC),\n" +
                            "  INDEX `FK_Countries_Cities_idx` (`CounrtyId` ASC),\n" +
                            "  CONSTRAINT `FK_Countries_Cities`\n" +
                            "    FOREIGN KEY (`CounrtyId`)\n" +
                            "    REFERENCES `meshchenko`.`addr_countries` (`Id`))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_streets` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  `CityId` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Name_CityId_UNIQUE` (`Name` ASC, `CityId` ASC),\n" +
                            "  INDEX `FK_Cities_Streets_idx` (`CityId` ASC),\n" +
                            "  CONSTRAINT `FK_Cities_Streets`\n" +
                            "    FOREIGN KEY (`CityId`)\n" +
                            "    REFERENCES `meshchenko`.`addr_cities` (`Id`))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_addresses` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `StreetId` INT(11) NOT NULL,\n" +
                            "  `BuildingNumberStr` VARCHAR(255) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `BuildingNumberStr_StreetId_UNIQUE` (`BuildingNumberStr` ASC, `StreetId` ASC),\n" +
                            "  INDEX `FK_Streets_Addresses_idx` (`StreetId` ASC),\n" +
                            "  CONSTRAINT `FK_Streets_Addresses`\n" +
                            "    FOREIGN KEY (`StreetId`)\n" +
                            "    REFERENCES `meshchenko`.`addr_streets` (`Id`))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`crm_roles` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Role` VARCHAR(64) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Role_UNIQUE` (`Role` ASC))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`crm_users` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  `Password` VARCHAR(1023) NOT NULL,\n" +
                            "  `PhoneNumber` VARCHAR(255) NULL DEFAULT NULL,\n" +
                            "  `Email` VARCHAR(255) NOT NULL,\n" +
                            "  `EmailConfirmed` TINYINT(1) NOT NULL DEFAULT '0',\n" +
                            "  `RoleId` INT(11) NULL DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),\n" +
                            "  INDEX `RoleId` (`RoleId` ASC),\n" +
                            "  CONSTRAINT `FK_roles_users`\n" +
                            "    FOREIGN KEY (`RoleId`)\n" +
                            "    REFERENCES `meshchenko`.`crm_roles` (`Id`)\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_shoppingcenters` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  `AddressId` INT(11) NOT NULL,\n" +
                            "  `Lat` FLOAT NULL DEFAULT NULL,\n" +
                            "  `Lng` FLOAT NULL DEFAULT NULL,\n" +
                            "  `Description` LONGTEXT NULL DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Name_AddressId_UNIQUE` (`Name` ASC, `AddressId` ASC),\n" +
                            "  INDEX `FK_Addresses_ShoppingCenters_idx` (`AddressId` ASC),\n" +
                            "  CONSTRAINT `FK_Addresses_ShoppingCenters`\n" +
                            "    FOREIGN KEY (`AddressId`)\n" +
                            "    REFERENCES `meshchenko`.`addr_addresses` (`Id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_rooms` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Name` VARCHAR(255) NOT NULL,\n" +
                            "  `Square` FLOAT NULL DEFAULT NULL,\n" +
                            "  `ShoppingCenterId` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  UNIQUE INDEX `Name_ShoppingCenterId_UNIQUE` (`Name` ASC, `ShoppingCenterId` ASC),\n" +
                            "  INDEX `FK_ShoppingCenters_Rooms_idx` (`ShoppingCenterId` ASC),\n" +
                            "  CONSTRAINT `FK_ShoppingCenters_Rooms`\n" +
                            "    FOREIGN KEY (`ShoppingCenterId`)\n" +
                            "    REFERENCES `meshchenko`.`ls_shoppingcenters` (`Id`))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_leaserooms` (\n" +
                            "  `Id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `Price` FLOAT NULL DEFAULT NULL,\n" +
                            "  `DateStartLease` DATETIME NULL DEFAULT NULL,\n" +
                            "  `DateStopLease` DATETIME NULL DEFAULT NULL,\n" +
                            "  `RoomId` INT(11) NOT NULL,\n" +
                            "  `UserId` INT(11) NOT NULL COMMENT 'owner of room',\n" +
                            "  PRIMARY KEY (`Id`),\n" +
                            "  INDEX `FK_Rooms_LeaseRooms_idx` (`RoomId` ASC),\n" +
                            "  INDEX `FK_Users_LeaseRooms_idx` (`UserId` ASC),\n" +
                            "  CONSTRAINT `FK_Rooms_LeaseRooms`\n" +
                            "    FOREIGN KEY (`RoomId`)\n" +
                            "    REFERENCES `meshchenko`.`ls_rooms` (`Id`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE,\n" +
                            "  CONSTRAINT `FK_Users_LeaseRooms`\n" +
                            "    FOREIGN KEY (`UserId`)\n" +
                            "    REFERENCES `meshchenko`.`crm_users` (`Id`))\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = utf8;"
            );



            //вставка тестовых записей

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

class TaskC_Reset {
    public static void main(String[ ] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {

            //пересоздание базы целиком
            statement.executeUpdate("DROP SCHEMA IF EXISTS `meshchenko` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `meshchenko` DEFAULT CHARACTER SET utf8 ;");

            //удаление всех таблиц (если работает удаление схемы то это лишнее)
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_countries` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_countries` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_cities` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_streets` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`addr_addresses` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`crm_roles` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`crm_users` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_shoppingcenters` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_rooms` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `meshchenko`.`ls_leaserooms` ;");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
