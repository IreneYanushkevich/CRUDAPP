package com.irinayanushkevich.crud.repository.jsonrep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.irinayanushkevich.crud.model.Post;
import com.irinayanushkevich.crud.model.PostStatus;
import com.irinayanushkevich.crud.repository.PostRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JsonPostRepositoryImpl implements PostRepository {

    private final File file = new File("C:\\Users\\Irene\\IdeaProjects\\CRUDAPP\\src\\main\\resources\\posts.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Post create(Post post) {
        List<Post> posts = readFile();
        post.setId(generateId(posts));
        post.setCreated(setDate());
        post.setUpdated(setDate());
        post.setStatus(PostStatus.UNDER_REVIEW);
        posts.add(post);
        writeFile(posts);
        return post;
    }

    @Override
    public Post getById(Long id) {
        List<Post> posts = readFile();
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    public Post edit(Post post) {
        List<Post> posts = readFile();
        Post postAfterEdit = null;
        for (Post p : posts) {
            if (p.getId().equals(post.getId())) {
                p.setStatus(PostStatus.ACTIVE);
                p.setUpdated(setDate());
                p.setContent(post.getContent());
                p.setLabels(post.getLabels());
                writeFile(posts);
                postAfterEdit = p;
                break;
            }
        }
        return postAfterEdit;
    }

    @Override
    public boolean delete(Long id) {
        List<Post> posts = readFile();
        boolean result = false;
        for (Post p : posts) {
            if (p.getId().equals(id)) {
                p.setStatus(PostStatus.DELETED);
                p.setUpdated(setDate());
                writeFile(posts);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Post> getAll() {
        return readFile();
    }

    private List<Post> readFile() {
        List<Post> p = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Post>>() {
            }.getType();
            p = new Gson().fromJson(reader, type);
            if (p == null) {
                p = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
        return p;
    }

    private void writeFile(List<Post> posts) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(posts, writer);
        } catch (IOException e) {
            System.out.println("Writing file error: " + e);
        }
    }

    private long generateId(List<Post> posts) {
        long id = 0;
        Optional<Post> p = posts.stream().max(Comparator.comparing(Post::getId));
        if (p.isPresent()) {
            id = p.get().getId() + 1;
        }
        return id;
    }

    private String setDate() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return now.format(formatter);
    }
}