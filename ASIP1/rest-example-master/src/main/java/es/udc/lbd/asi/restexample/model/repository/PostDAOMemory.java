package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Post;

@Repository
public class PostDAOMemory implements PostDAO {

    @Override
    public List<Post> findAll() {
        return InMemoryDB.posts.values().stream().collect(Collectors.toList());
    }

    @Override
    public Post findById(Long id) {
        return InMemoryDB.posts.get(id);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(InMemoryDB.idGenerator.addAndGet(1));
        }
        InMemoryDB.posts.put(post.getId(), post);
        return post;
    }

    @Override
    public void deleteById(Long id) {
        InMemoryDB.posts.remove(id);
    }
}
