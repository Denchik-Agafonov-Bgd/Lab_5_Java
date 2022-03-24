package com.company;

public class Student
{
    public String id;
    public String name;
    public String surname;
    public String patronymic;
    public String school;
    public String clas;
    public String age;
    public String grade;

    Student(String id, String name,String surname,String patronymic,String school,String clas,String age,String grade)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.school = school;
        this.clas = clas;
        this.age = age;
        this.grade = grade;
    }

    public Student() {

    }

    public void id(String id) {this.id=id;
    }

    public void name(String name)
    {
        this.name=name;
    }

    public void surname(String surname) {this.surname=surname;
    }

    public void patronymic(String patronymic) {this.patronymic=patronymic;
    }

    public void school(String school) {this.school=school;
    }

    public void clas(String clas) {this.clas=clas;
    }

    public void age(String age) {this.age=age;
    }

    public void grade(String grade) {this.grade=grade;
    }

    @Override
    public String toString() {
        return "Student: id =" + this.id + " name = " + this.name + " surname = " + this.surname+ " patronymic = " + this.patronymic+ " school = " + this.school+ " clas = " + this.clas+ " age = " + this.age+ " grade = " + this.grade;
    }
}
