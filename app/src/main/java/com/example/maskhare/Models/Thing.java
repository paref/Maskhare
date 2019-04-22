package com.example.maskhare.Models;

public class Thing {
    private int Id;
    private String Title;
    private int Category_Id;
    private int Level;

    public Thing(int id, String title, int category_Id, int level) {
        Id = id;
        Title = title;
        Category_Id = category_Id;
        Level = level;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int category_Id) {
        Category_Id = category_Id;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
