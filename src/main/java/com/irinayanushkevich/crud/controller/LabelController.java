package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Label;
import com.irinayanushkevich.crud.repository.LabelRepository;
import com.irinayanushkevich.crud.repository.jsonrep.JsonLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository jsonRepL = new JsonLabelRepositoryImpl();

    public Label create(String name) {
        Long id = null;
        Label l = new Label(id, name);
        return jsonRepL.create(l);
    }

    public Label getById(Long id) {
        return jsonRepL.getById(id);
    }

    public Label edit(Long id, String name) {
        Label label = new Label(id, name);
        return jsonRepL.edit(label);
    }

    public boolean delete(Long id) {
        return jsonRepL.delete(id);
    }

    public List<Label> getAll() {
        return jsonRepL.getAll();
    }
}
