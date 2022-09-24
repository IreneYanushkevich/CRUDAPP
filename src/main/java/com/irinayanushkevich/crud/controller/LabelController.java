package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Label;
import com.irinayanushkevich.crud.repository.jsonrep.JsonLabelRepositoryImpl;

import java.util.*;

public class LabelController {

    Scanner sc = new Scanner(System.in);
    private final JsonLabelRepositoryImpl jsonRep = new JsonLabelRepositoryImpl();

    public void create() {
        Label l;
        long id;
        String name;

        name = askName();

        if (name == null) {
            return;
        }
        id = jsonRep.generateId();
        l = new Label(id, name);

        System.out.println("Added label: " + jsonRep.create(l));
    }

    public void getById() {
        Label l = null;
        long id;

        System.out.println("Enter the id >>>");

        id = askId();

        if (jsonRep.checkId(id)) {
            l = jsonRep.getById(id);
        }
        printLabel(l);
    }

    public void printLabel(Label l) {
        if (l == null) {
            System.out.println("This label doesn't exist.");
        } else {
            System.out.println("The label: " + l);
        }
    }

    private String askName() {
        String name = null;

        try {
            System.out.println("Set a name of the label >>>");
            name = sc.nextLine();
            if (jsonRep.checkName(name)) {
                name = null;
                System.out.println("Label with this name already exists.");
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return name;
    }

    private long askId() {
        long id;

        while (true) {
            try {
                id = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        return id;
    }

    public void edit() {
        Label newLabel = null;
        Label editLabel;
        long id;
        String newName;

        System.out.println("Enter id of the label position you want to edit >>>");

        id = askId();
        printLabel(jsonRep.getById(id));

        if (jsonRep.checkId(id)) {
            newName = askName();
            if (newName == null) {
                return;
            } else {
                editLabel = new Label(id, newName);
                newLabel = jsonRep.edit(editLabel);
            }
        }
        System.out.println("Your changes: " + newLabel);
    }


    public void delete() {
        Label delLabel;
        long delId;

        System.out.println("Enter the index of the position you want to delete >>>");

        delId = askId();

        if (jsonRep.checkId(delId)) {
            delLabel = jsonRep.delete(delId);
            System.out.println("Deleted position: " + delLabel);
        } else {
            System.out.println("This label doesn't exist.");
        }
    }

    public void getAll() {
        List<Label> listLabel;
        try {
            listLabel = jsonRep.getAll();
            if (!listLabel.isEmpty()) {
                System.out.println("List ot all labels: " + listLabel);
            } else {
                System.out.println("All positions was deleted.");
            }
        } catch (NullPointerException e) {
            System.out.println("File with labels is empty or doesn't exist.");
        }
    }
}
