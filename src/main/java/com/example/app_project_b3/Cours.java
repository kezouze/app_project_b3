package com.example.app_project_b3;

public class Cours {
    private final int id;
    private String name;
    private final int prof_id;
    private String lastName;
    private String firstName;

    public Cours(int id, String name, int prof_id) {
        this.id = id;
        this.name = name;
        this.prof_id = prof_id;
    }
    public int getId() { return id;}
    public String getName() { return name; }
    public int getProf_id() { return prof_id; }
    public String getLasName() { return lastName; }
    public String getFirstName() { return firstName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
