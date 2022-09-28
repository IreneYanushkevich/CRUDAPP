package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Writer;
import com.irinayanushkevich.crud.repository.WriterRepository;
import com.irinayanushkevich.crud.repository.jsonrep.JsonWriterRepositoryImpl;

import java.util.List;

public class WriterController {

    private final WriterRepository jsonRepW = new JsonWriterRepositoryImpl();

    public Writer create(Writer writer) {
        return jsonRepW.create(writer);
    }

    public Writer getById(Long id) {
        return jsonRepW.getById(id);
    }

    public Writer edit(Writer writer) {
        return jsonRepW.edit(writer);
    }

    public boolean delete(Long id) {
        return jsonRepW.delete(id);
    }

    public List<Writer> getAll() {
        return jsonRepW.getAll();
    }
}