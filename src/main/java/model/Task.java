package model;

import java.sql.Date;

public class Task {

    private Integer id;
    private Date date;
    private String priority;
    private String name;

    public Task() {

    }

    public Task(int id, String name, Date estimation_time, String priority) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
}
