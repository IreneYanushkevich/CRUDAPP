package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Label;
import com.irinayanushkevich.crud.repository.jsonrep.JsonLabelRepositoryImpl;

import java.util.*;

public class LabelController {

    private final Scanner sc = new Scanner(System.in);
    private final JsonLabelRepositoryImpl jsonRep = new JsonLabelRepositoryImpl();

    public Label create() {
        String name = askName();
        long id = -1;
        Label l = new Label(id, name);
        return jsonRep.create(l);
    }

    public Label getById() {
        long id = askId();
        return jsonRep.getById(id);
    }

    private String askName() {
        return sc.nextLine();
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

    public Label edit() {
        long id = askId();
        String newName = askName();
        Label editLabel = new Label(id, newName);
        return jsonRep.edit(editLabel);
    }

    public Label delete() {
        long delId = askId();
        return jsonRep.delete(delId);
    }

    public List<Label> getAll() {
        return jsonRep.getAll();
    }
}
