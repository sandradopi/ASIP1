package es.udc.lbd.asi.restexample.model.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.domain.User;

public class InMemoryDB {
    public static Map<Long, User> users = new HashMap<Long, User>();
    public static Map<Long, Post> posts = new HashMap<Long, Post>();

    public static AtomicLong idGenerator = new AtomicLong(0);

    static {
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "pepe"));
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "maría"));
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "ramón"));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título1", "body1", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título2", "body2", users.get(2L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título3", "body3", users.get(3L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título4", "body4", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título5", "body5", users.get(2L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título6", "body6", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título7", "body7", users.get(3L)));
    }
}
