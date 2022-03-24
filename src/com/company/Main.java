package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main
{
    private static Statement stmt;
    private static ResultSet rs;

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get("settings.properties"))){
            props.load(in);
        }

        String url = props.getProperty("connectionUrl");
        String username = props.getProperty("userName");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public static void start()
    {
        int type = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите с чем работать: \n" +
                "1: XML\n" +
                "2: БД\n" +
                "3: Конвертировать данные из XML в БД\n" +
                "4: Конвертировать данные из БД в XML\n" +
                "5: Завершить работу.");
        type = scanner.nextInt();

        var DB = new DataBase();
        switch (type) {
            case 1:
                System.out.println("Выберите действие: \n" +
                        "1: Вывести всё содержимое\n" +
                        "2: Найти содержимое по параметру\n" +
                        "3: Добавить новую запись\n" +
                        "4: Изменить запись\n" +
                        "5: Удалить запись\n" +
                        "0: Завершить работу c XML\n");

                type = scanner.nextInt();

                if (type == 0)
                    start();

                List<Student> langList = new ArrayList<Student>();
                //DomParse DOM = new DomParse();
                SaxParse SAX = new SaxParse();

                switch (type) {
                    case 1: {
                        langList = new ArrayList<Student>();
                        //DomParse DOM = new DomParse();
                        SAX = new SaxParse();
                        langList = SAX.workXML(1);

                        System.out.println("Вывод списка \n");

                        for (Student lang : langList) {
                            System.out.println(lang.toString());
                        }

                        break;
                    }

                    case 2: {
                        langList = SAX.workXML(2);
                        System.out.println("Вывод списка по поиску\n");

                        for (Student lang : langList) {
                            System.out.println(lang.toString());
                        }
                        break;
                    }

                    case 3: {
                        langList = SAX.workXML(3);
                        System.out.println("Вывод списка \n");

                        for (Student lang : langList) {
                            System.out.println(lang.toString());
                        }

                        DomParse DOM = new DomParse();
                        DOM.DomParseWrite(langList);
                        break;
                    }
                    case 4: {
                        langList = SAX.workXML(4);
                        System.out.println("Вывод списка \n");

                        for (Student lang : langList) {
                            System.out.println(lang.toString());
                        }

                        DomParse DOM = new DomParse();
                        DOM.DomParseWrite(langList);

                        break;
                    }
                    case 5: {
                        langList = SAX.workXML(5);
                        System.out.println("Вывод списка \n");

                        for (Student lang : langList) {
                            System.out.println(lang.toString());
                        }

                        DomParse DOM = new DomParse();
                        DOM.DomParseWrite(langList);

                        break;
                    }
                }

                break;
            case 2:
            {
                System.out.println("Выберите действие: \n" +
                        "1: Вывести всё содержимое\n" +
                        "2: Найти содержимое по параметру\n" +
                        "3: Добавить новую запись\n" +
                        "4: Изменить запись\n" +
                        "5: Удалить запись\n" +
                        "0: Завершить работу c БД\n");

                type = scanner.nextInt();

                if (type == 0)
                    start();


                switch (type) {
                    case 1: {

                        rs = DB.workDataBase(1);

                        try {
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String name = rs.getString(2);
                                String surname = rs.getString(3);
                                String patronymic = rs.getString(4);
                                String school = rs.getString(5);
                                String clas = rs.getString(6);
                                String age = rs.getString(7);
                                String grade = rs.getString(8);
                                System.out.printf("id: %d, name: %s, surname: %s, patronymic: %s, school: %s, clas: %s, age: %s, grade: %s \n", id, name, surname, patronymic, school, clas, age, grade);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        break;
                    }
                    case 2: {

                        rs = DB.workDataBase(2);

                        try {
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String name = rs.getString(2);
                                String surname = rs.getString(3);
                                String patronymic = rs.getString(4);
                                String school = rs.getString(5);
                                String clas = rs.getString(6);
                                String age = rs.getString(7);
                                String grade = rs.getString(8);
                                System.out.printf("id: %d, name: %s, surname: %s, patronymic: %s, school: %s, clas: %s, age: %s, grade: %s \n", id, name, surname, patronymic, school, clas, age, grade);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        break;
                    }

                    case 3: {
                        rs = DB.workDataBase(3);


                        try {
                            System.out.printf("Добавлен ученик \n");
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String name = rs.getString(2);
                                String surname = rs.getString(3);
                                String patronymic = rs.getString(4);
                                String school = rs.getString(5);
                                String clas = rs.getString(6);
                                String age = rs.getString(7);
                                String grade = rs.getString(8);
                                System.out.printf("id: %d, name: %s, surname: %s, patronymic: %s, school: %s, clas: %s, age: %s, grade: %s \n", id, name, surname, patronymic, school, clas, age, grade);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        break;
                    }

                    case 4: {
                        rs = DB.workDataBase(4);

                        try {
                            System.out.printf("Изменен ученик \n");
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String name = rs.getString(2);
                                String surname = rs.getString(3);
                                String patronymic = rs.getString(4);
                                String school = rs.getString(5);
                                String clas = rs.getString(6);
                                String age = rs.getString(7);
                                String grade = rs.getString(8);
                                System.out.printf("id: %d, name: %s, surname: %s, patronymic: %s, school: %s, clas: %s, age: %s, grade: %s \n", id, name, surname, patronymic, school, clas, age, grade);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        break;
                    }

                    case 5: {
                        rs = DB.workDataBase(5);
                        break;
                    }

                }
                break;
        }


            case 3:
                langList = new ArrayList<Student>();
                SAX = new SaxParse();
                langList = SAX.workXML(1);

                System.out.println("Вывод списка \n");

                for (Student lang : langList) {
                    //String query = "select * from students WHERE id = '"+ lang.id +"' AND name = '"+ lang.name +"' AND surname = '"+ lang.surname +"' AND patronymic = '"+ lang.patronymic +"' AND school = '"+ lang.school +"' AND clas = '"+ lang.clas +"' AND age = '"+ lang.age +"' AND grade = '"+ lang.grade +"' ";


                    String query = "select * from students WHERE id = '"+ lang.id +"'";

                    try {
                        rs = DB.search(query);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    try {
                        if(rs.next())
                        {
                            try {
                                DB.update(lang);
                                rs=null;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        else
                        {
                            query = "select * from students WHERE id = '"+ lang.id +"' AND name = '"+ lang.name +"' AND surname = '"+ lang.surname +"' AND patronymic = '"+ lang.patronymic +"' AND school = '"+ lang.school +"' AND clas = '"+ lang.clas +"' AND age = '"+ lang.age +"' AND grade = '"+ lang.grade +"' ";
                            rs = DB.search(query);
                            if(!rs.next())
                            {
                                DB.insert(lang);
                                rs=null;
                            }
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;

            case 4:
                langList = new ArrayList<Student>();
                SAX = new SaxParse();
                List<Student> langListOnInsert = new ArrayList<Student>();

                rs = DB.workDataBase(1);

                try {
                    while (rs.next()) {
                        langList.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                DomParse DOM = new DomParse();
                DOM.DomParseWrite(langList);
                System.out.println("Дошло");
                break;

            case 5:
                return;
        }

        start();

    }


    public static void main(String[] args)
    {
        start();
    }
}
