package fr.megagame.demotheme;

import java.util.Date;

/**
 * Created by mehdi on 30/07/2017.
 */

public class Todo {

    private int id;
    private String todo;
    private String dateCreated;

    public Todo(){}

    public Todo(String todo) {
        this.todo = todo;
        this.dateCreated = new Date().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
