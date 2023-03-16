package View;

import java.util.Scanner;
import Controller.Controller;
import Metod.Fields;
import Metod.Toy;
import Valid.Validate;

public class ViewUser {
    private final Controller сontroller;
    private final Validate validate;

    public ViewUser(Controller сontroller, Validate validate) {
        this.сontroller = сontroller;
        this.validate = validate;
    }

    public void run() {
        Menu com = Menu.NONE;
        showHelp();
        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Menu.valueOf(command.toUpperCase());
                if (com == Menu.EXIT)
                    return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case UPDATE:
                        update();
                        break;
                    case DELETE:
                        delete();
                        break;
                    case LIST:
                        list();
                        break;
                    case PRIZE:
                        prize();
                        break;
                    case HELP:
                        showHelp();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void showHelp() {
        System.out.println("Список команд:");
        for (Menu c : Menu.values()) {
            System.out.println(c);
        }
    }

    private Scanner scan;

    public String scanString(String s) {
        System.out.println(s);
        scan = new Scanner(System.in, "Cp866");
        return scan.nextLine();
    }

    private void create() throws Exception {
        try {
            String name = scanString("Введите наименование игрушки");
            String count = prompt("количество: ");
            validate.checkToy(name, count);
            String dropping = prompt("частота выпадения: ");
            if (dropping.isEmpty()) {
                dropping = "---";
            }
            сontroller.saveToy(new Toy(name, count, dropping), name, count);

        } catch (Exception ex) {
            System.out.println("Произошла ошибка " + ex.toString());
        }
    }

    private void update() throws Exception {
        try {
            String userid = prompt("ID: ");
            String field_name = prompt("Какое поле (NAME,COUNT,DROPPING): ");
            String param = scanString("Введите новые данные:");
            Toy toyUp = сontroller.readToy(userid);
            сontroller.updateToy(toyUp, Fields.valueOf(field_name.toUpperCase()), param);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка " + ex.toString());
        }
    }

    private void delete() throws Exception {
        try {
            String id = prompt("Введите ID игрушки, которую нужно удалить: ");
            System.out.println("Хотите удалить: ");
            System.out.println(сontroller.readToy(id));
            String consent = prompt("Введите y - удалить, n - не удалять: ");
            if (consent.equals("y")) {
                сontroller.deleteToy(id);
            } else {
                System.out.println("Удаление отклонено.");
            }
        } catch (Exception ex) {
            System.out.println("Произошла ошибка " + ex.toString());
        }
    }

    private void list() throws Exception {
        try {
            for (Toy toy : сontroller.getToys()) {
                System.out.println(toy);
            }
        } catch (Exception ex) {
            System.out.println("Произошла ошибка " + ex.toString());
        }
    }

    private void prize() {
        try {
            System.out.println("Разиграем забирите игрушку!!!");
            сontroller.prizeToy();
        } catch (Exception ex) {
            System.out.println("\nРозыгрыш не состоялся. Попробуйте еще раз!");
        }
    }

   
}
