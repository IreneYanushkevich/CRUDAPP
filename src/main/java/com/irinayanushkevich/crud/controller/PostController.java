package com.irinayanushkevich.crud.controller;

import com.irinayanushkevich.crud.model.Post;
import com.irinayanushkevich.crud.repository.PostRepository;
import com.irinayanushkevich.crud.repository.jsonrep.JsonPostRepositoryImpl;

import java.util.List;

public class PostController {

    private final PostRepository jsonRepP = new JsonPostRepositoryImpl();


    public Post create(Post post) {
        return jsonRepP.create(post);
    }

    public Post getById(Long id) {
        return jsonRepP.getById(id);
    }


    public Post edit(Post post) {
        return jsonRepP.edit(post);
    }

    public boolean delete(Long id) {
        return jsonRepP.delete(id);
    }

    public List<Post> getAll() {
        return jsonRepP.getAll();
    }
}