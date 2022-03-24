package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DataBase
{
    private Statement stmt;
    private Connection conn;

    DataBase()
    {
            try
            {
                conn = getConnection();
            }
            catch(Exception ex)
            {
                    System.out.println("Connection failed...");

                    System.out.println(ex);
            }

        try {
            this.stmt = (Statement) conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get("settings.properties"))){
            props.load(in);
        }

        return DriverManager.getConnection(props.getProperty("connectionUrl"), props.getProperty("userName"), props.getProperty("password"));
    }

    public ResultSet getAll() throws SQLException
    {
        String query = "select * from students";
        return this.stmt.executeQuery(query);
    }

    public ResultSet search(String query) throws SQLException
    {
        System.out.println(query);
        return this.stmt.executeQuery(query);
    }

    public ResultSet insert(Student student) throws SQLException
    {
        String query = "INSERT INTO students (name, surname, patronymic, school, clas , age, grade)" +
                " VALUES ('" + student.name + "','" + student.surname + "', '" + student.patronymic + "', '" + student.school + "', " +
                "'" + student.clas + "','" + student.age + "','" + student.grade + "')";
        stmt.executeUpdate(query);

        return this.stmt.executeQuery("select * from students ORDER BY id DESC LIMIT 1");
    }

    public ResultSet update(Student student) throws SQLException
    {
        this.stmt.executeUpdate("update students set name = '" + student.name + "', surname = '" + student.surname + "', patronymic = '" + student.patronymic + "', school = '" + student.school + "', clas = '" + student.clas + "', age = '" + student.age + "', grade = '" + student.grade + "' where id = " + student.id + ";");

        return this.stmt.executeQuery("select * from students WHERE id = "+ student.id +"");
    }

    public void delete(int id) throws SQLException
    {
        this.stmt.executeUpdate("delete from students where id = "+ id +" ");
    }

    public ResultSet workDataBase(int action)
    {
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                switch (action) {
                    case 1:
                        return getAll();
                    case 2: {
                        boolean flag=false;
                        String str, str_check;
                        str_check="NO";
                        String query = "select * from students WHERE";

                        System.out.println("Введите id: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            flag = true;
                            query+=" id = '"+ str +"'";
                        }

                        System.out.println("Введите name: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " name = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите surname: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " surname = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите patronymic: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " patronymic = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите school: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " school = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите clas: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " clas = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите age: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " age = '"+ str +"'";
                            flag = true;
                        }

                        System.out.println("Введите grade: ");
                        str = scanner.next();
                        if(!str.equals(str_check))
                        {
                            if (flag)
                                query+=" AND ";

                            query+= " grade = '"+ str +"'";
                            flag = true;
                        }

                        return search(query);

                    }
                    case 3:
                    {
                        Student student = new Student();

                        System.out.println("Введите name: ");
                        student.name=scanner.next();
                        System.out.println("Введите surname: ");
                        student.surname=scanner.next();
                        System.out.println("Введите patronymic: ");
                        student.patronymic=scanner.next();
                        System.out.println("Введите school: ");
                        student.school=scanner.next();
                        System.out.println("Введите class: ");
                        student.clas=scanner.next();
                        System.out.println("Введите age: ");
                        student.age=scanner.next();
                        System.out.println("Введите grade: ");
                        student.grade=scanner.next();

                        return insert(student);
                    }

                    case 4:
                    {
                        int id;
                        String[] str = new String[8];
                        String str_check="NO";
                        String strok;

                        System.out.println("Введите id: ");
                        id = scanner.nextInt();

                        ResultSet temp = this.stmt.executeQuery("select * from students WHERE id = "+ id +"");

                        Student student = null;

                        if(temp.next())
                            student = new Student(temp.getString(1),temp.getString(2),temp.getString(3),temp.getString(4),temp.getString(5),temp.getString(6),temp.getString(7),temp.getString(8));
                        else
                            System.out.println("не нашел");

                        System.out.println("Введите name: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.name = String.valueOf(strok);
                        }

                        System.out.println("Введите surname: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.surname= String.valueOf(strok);
                        }

                        System.out.println("Введите patronymic: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.patronymic= String.valueOf(strok);
                        }

                        System.out.println("Введите school: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.school= String.valueOf(strok);
                        }

                        System.out.println("Введите class: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.clas= String.valueOf(strok);
                        }

                        System.out.println("Введите age: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.age= String.valueOf(strok);
                        }

                        System.out.println("Введите grade: ");
                        strok = scanner.next();
                        if(!strok.equals(str_check))
                        {
                            student.grade= String.valueOf(strok);
                        }

                        return update(student);
                    }

                    case 5:
                    {
                        int id;

                        System.out.println("Введите id: ");
                        id = scanner.nextInt();

                        delete(id);
                        return null;
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        return null;
    }



}
