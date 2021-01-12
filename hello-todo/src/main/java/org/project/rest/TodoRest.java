package org.project.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.project.entity.Todo;
import org.project.service.TodoService;

/**
 * https://www.udemy.com/course/java-enterprise-edition-8/learn/lecture/11004986#questions/8486950
 *
 * @author HAL9000
 */
@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest {

    @Inject
    TodoService todoService;

    // api/v1/todo/new
    @Path("new")
    @POST
    public Response createTodo(Todo todo) {
        todoService.createTodo(todo);
        return Response.ok(todo).build();
    }

    @Path("update")
    @PUT
    public Response updateTodo(Todo todo) {
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }

    @Path("{id}")
    @GET
    public Todo getTodo(@PathParam("id") Long id) {
        return todoService.findTodoById(id);
    }

    @Path("list")
    @GET
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @Path("status")
    @POST
    public Response markAsComplete(@QueryParam("id") Long id) {
        Todo todo = todoService.findTodoById(id);
        todo.setIsCompleted(true);
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }

}
