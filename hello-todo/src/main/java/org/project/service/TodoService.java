package org.project.service;

import org.project.entity.Todo;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * https://www.udemy.com/course/java-enterprise-edition-8/learn/lecture/11004984#questions/8486950
 *
 * @author HAL9000
 */
@Transactional
public class TodoService {

    private final static Logger logger = Logger.getLogger(TodoService.class.getName());

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "Logger from inside 'TodoService' calling!");
    }


    /**
     * Inject EntityManager
     * <p>
     * Persistence methods can only be invoked
     * on EntityManager inside Transaction (JPA specification!)
     * <p>
     * Dont want to manually create transactions (begin, commit, ...)
     */
    @PersistenceContext
    EntityManager entityManager;

    public Todo createTodo(Todo todo) {
        //persist into db
        entityManager.persist(todo);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        entityManager.merge(todo);
        return todo;
    }

    public Todo findTodoById(Long id) {
        return entityManager.find(Todo.class, id);
    }

    public List<Todo> getTodos() {
        return entityManager.createQuery("SELECT t from Todo t", Todo.class).getResultList();
    }

}
