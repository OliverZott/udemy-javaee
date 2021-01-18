package org.project.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.project.entity.Todo;

/**
 * https://www.udemy.com/course/java-enterprise-edition-8/learn/lecture/11004984#questions/8486950
 *
 * @author HAL9000
 */
@Transactional
public class TodoService {

    
    /**
     * Inject EntityManager
     * 
     * Persistence methods can only be invoked 
     * on EntityManager inside Transaction (JPA specification!)
     * 
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
