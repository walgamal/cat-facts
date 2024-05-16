package com.example.catfactsapp;

public class Cat {
    String name;
    String origin;
    String temperament;
    String imageUrl;

    public Cat(){}

    public Cat(String n, String o, String t, String i) {
        name = n;
        origin = o;
        temperament = t;
        imageUrl = i;
    }

    public void setName(String inputName){
        name = inputName;
    }

    public void setOrigin(String inputOrigin){
        origin = inputOrigin;
    }

    public void setTemperament(String inputTemperament){
        temperament = inputTemperament;
    }

    public String getName(){
        return name;
    }

    public String getOrigin(){
        return origin;
    }

    public String getTemperament(){
        return temperament;
    }
}
