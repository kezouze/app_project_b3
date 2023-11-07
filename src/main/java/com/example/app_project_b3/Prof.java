package com.example.app_project_b3;

public class Prof {

    private final int id;
    private String firstName;
    private String lastName;

    public Prof(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getId() { return id; }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
