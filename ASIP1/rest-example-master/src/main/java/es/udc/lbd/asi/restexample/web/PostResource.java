package es.udc.lbd.asi.restexample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.service.PostService;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findOne(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) throws IdAndBodyNotMatchingOnUpdateException {
        if (id != post.getId()) {
            throw new IdAndBodyNotMatchingOnUpdateException(Post.class);
        }
        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        postService.deleteById(id);
    }
}
