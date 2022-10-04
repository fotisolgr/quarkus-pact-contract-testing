package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Person {

    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format(
                "%s:{name:\"%s\", age:\"%d\"}", getClass().getSimpleName(), name, age);
    }
}
