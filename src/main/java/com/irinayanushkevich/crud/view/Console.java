package com.irinayanushkevich.crud.view;

import java.util.Scanner;

public class Console {

    private final LabelView lv = new LabelView();
    private final Scanner scanner = new Scanner(System.in);

    public void activateConsole() {

        int choice;
        boolean isFinish = false;

        while (!isFinish) {
            printMenu();
            choice = getAnswer();
            isFinish = workWithCategory(choice);
        }
    }

    public void printMenu() {
        System.out.println("""
                What category would you like to work with? Print 1, 2, 3 or 0:
                1 - Writer
                2 - Post
                3 - Label
                0 - Exit""");
    }

    public int getAnswer() {

        int answer;

        try {
            answer = Integer.parseInt(scanner.nextLine());
            if (answer < 0 || answer > 3) {
                System.out.println("You can choose only 1, 2, 3 or 0!");
            } else {
                return answer;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
        return -1;
    }

    public boolean workWithCategory(int answer) {
        switch (answer) {
            // case 1 -> WriterView.showMenu();
            // case 2 -> PostView.showMenu();
            case 3 -> lv.run("label");
            case 0 -> {
                return true;
            }
        }
        return false;
    }
}
