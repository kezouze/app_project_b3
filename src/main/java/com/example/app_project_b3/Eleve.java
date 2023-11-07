package com.example.app_project_b3;

public class Eleve {

    private final int id;
    private String firstName;
    private String lastName;

    public Eleve(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
