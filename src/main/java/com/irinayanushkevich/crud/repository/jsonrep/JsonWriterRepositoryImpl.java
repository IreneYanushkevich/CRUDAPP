package com.irinayanushkevich.crud.repository.jsonrep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.irinayanushkevich.crud.model.Writer;
import com.irinayanushkevich.crud.repository.WriterRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JsonWriterRepositoryImpl implements WriterRepository {
    private final File file = new File("C:\\Users\\Irene\\IdeaProjects\\CRUDAPP\\src\\main\\resources\\writers.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Writer create(Writer writer) {
        List<Writer> writers = readFile();
        writer.setId(generateId(writers));
        writers.add(writer);
        writeFile(writers);
        return writer;
    }

    @Override
    public Writer getById(Long id) {
        List<Writer> writers = readFile();
        return writers.stream().filter(writer -> writer.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Writer edit(Writer writer) {
        List<Writer> writers = readFile();
        long id = writer.getId();
        Writer oldWriter = getById(id);
        writers.remove(oldWriter);
        writers.add(writer);
        writeFile(writers);
        return writer;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = false;
        List<Writer> writers = readFile();
        Writer wDel = getById(id);
        if (wDel != null) {
            writers.remove(wDel);
            writeFile(writers);
            result = true;
        }
        return result;
    }

    @Override
    public List<Writer> getAll() {
        return readFile();
    }

    private List<Writer> readFile() {
        List<Writer> w = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Writer>>() {
            }.getType();
            w = new Gson().fromJson(reader, type);
            if (w == null) {
                w = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
        return w;
    }

    private void writeFile(List<Writer> writers) {
        try (java.io.Writer writer = new FileWriter(file)) {
            gson.toJson(writers, writer);
        } catch (IOException e) {
            System.out.println("Writing file error: " + e);
        }
    }

    private long generateId(List<Writer> writers) {
        long id = 0;
        Optional<Writer> w = writers.stream().max(Comparator.comparing(Writer::getId));
        if (w.isPresent()) {
            id = w.get().getId() + 1;
        }
        return id;
    }
}
