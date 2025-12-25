package com.example.finals.Frag1ListView;


public class ListData  {
    String name;
    int age;

    public ListData(String n, int a){
        age = a;
        name = n;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
