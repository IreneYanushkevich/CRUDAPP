package com.irinayanushkevich.crud.view;

import java.util.Scanner;

public class CommonView {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelView lv = new LabelView(this);

    //private final PostView pv = new PostView();
    //private final WriterView wv = new WriterView();

    public void run(String category) {

        int act;
        boolean isFinish = false;

        while (!isFinish) {
            printActions(category);
            act = chooseAction();
            isFinish = lv.workWithActions(act);
        }
    }

    private void printActions(String category) {
        System.out.println("""
                Choose what you want to do with\t""" + category + """
                \nPrint 1, 2, 3, 4 or 0:
                1 - Create
                2 - Find by index
                3 - Edit
                4 - Delete
                5 - See the whole list
                0 - Exit""");
    }

    private int chooseAction() {

        int act;

        try {
            act = Integer.parseInt(scanner.nextLine());
            if (act < 0 || act > 5) {
                System.out.println("You can choose only 1, 2, 3, 4, 5 or 0!");
            } else {
                return act;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
        return -1;
    }

    public String askName() {
        System.out.println("Set a name of the label >>>");
        return scanner.nextLine();
    }

    public long askId() {
        long id;
        System.out.println("Enter the id of the position >>>");
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        return id;
    }
}
