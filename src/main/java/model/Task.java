package model;

import java.sql.Date;

public final class Task {

    private Integer id;
    private String name;
    private Date date;
    private Priority priority;
    private boolean done;

    public Task() {

    }

    public Task(int id, String name, Date estimation_time, Priority priority) {
        this.id = id;
        this.name = name;
        date = estimation_time;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
