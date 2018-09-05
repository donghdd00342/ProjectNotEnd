package com.project.notend.notend.entities;

public class User {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int avatar;

    public User(int id, String name, int age, String gender, int avatar) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
