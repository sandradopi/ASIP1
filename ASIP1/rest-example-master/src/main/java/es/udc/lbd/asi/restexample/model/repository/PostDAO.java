package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Post;

public interface PostDAO {
    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);

    void deleteById(Long id);
}
