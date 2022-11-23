package com.example.app2;

public class Pessoa {

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getHouse() {
        return house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getPatronus() {
        return patronus;
    }

    public String getActor() {
        return actor;
    }

    private String name;
    private String uri;



    private String species;
    private String gender;
    private String house;
    private String dateOfBirth;
    private String ancestry;
    private String patronus;
    private String actor;
    public Pessoa(String name, String uri, String species, String gender, String house, String dateOfBirth, String ancestry, String patronus, String actor) {
        this.name = name;
        this.uri = uri;
        this.species = species;
        this.gender = gender;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.ancestry = ancestry;
        this.patronus = patronus;
        this.actor = actor;
    }
}
