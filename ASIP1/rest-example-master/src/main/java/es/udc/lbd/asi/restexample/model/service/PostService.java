package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.repository.PostDAO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PostService {

    @Autowired
    private PostDAO postDAO;

    public List<Post> findAll() {
        return postDAO.findAll();
    }

    public Post findById(Long id) {
        return postDAO.findById(id);
    }

    public Post save(Post post) {
        return postDAO.save(post);
    }

    public void deleteById(Long id) {
        postDAO.deleteById(id);
    }
}
