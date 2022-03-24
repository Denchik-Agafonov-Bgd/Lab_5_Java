package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.Console;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SaxParse
{
    private String filePath = "file.xml";
    private SAXParserFactory factory;
    private SAXParser saxParser;
    private List<Student> langList = new ArrayList<Student>();

    SaxParse() {
        try {
            factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();

            AdvancedXMLHandler handler = new AdvancedXMLHandler();
            saxParser.parse(filePath, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class AdvancedXMLHandler extends DefaultHandler {
        private String name, id,surname,patronymic,school,clas,age, grade, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("id"))
                    id = information;
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("surname"))
                    surname = information;
                if (lastElementName.equals("patronymic"))
                    patronymic = information;
                if (lastElementName.equals("school"))
                    school = information;
                if (lastElementName.equals("clas"))
                    clas = information;
                if (lastElementName.equals("age"))
                    age = information;
                if (lastElementName.equals("grade"))
                    grade = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( grade != null ) {
                langList.add(new Student(id, name, surname, patronymic, school, clas, age, grade));
                id = name = surname = patronymic = school = clas = age = grade = null;
            }
        }
    }


    private List<Student> search(Student student) {
        List<Student> searchlist = new ArrayList<Student>();
        String check = "NO";

        for (Student lang : langList) {
            if (!student.id.equals(check)) {
                if (!lang.id.equals(student.id))
                    continue;
            }
            if (!student.name.equals(check)) {
                if (!lang.name.equals(student.name))
                    continue;
            }
            if (!student.surname.equals(check)) {
                if (!lang.surname.equals(student.surname))
                    continue;
            }
            if (!student.patronymic.equals(check)) {
                if (!lang.patronymic.equals(student.patronymic))
                    continue;
            }
            if (!student.school.equals(check)) {
                if (!lang.school.equals(student.school))
                    continue;
            }
            if (!student.clas.equals(check)) {
                if (!lang.clas.equals(student.clas))
                    continue;
            }
            if (!student.age.equals(check)) {
                if (!lang.age.equals(student.age))
                    continue;
            }
            if (!student.grade.equals(check)) {
                if (!lang.grade.equals(student.grade))
                    continue;
            }
            searchlist.add(lang);
        }

        return searchlist;
    }


    public List<Student> workXML(int action) {
        Scanner scanner = new Scanner(System.in);

        try {
            switch (action) {
                case 1:
                {
                    return langList;
                }
                case 2:
                {
                    Student student =new Student();

                    System.out.println("Введите id: ");
                    student.id = scanner.next();
                    System.out.println("Введите name: ");
                    student.name= scanner.next();
                    System.out.println("Введите surname: ");
                    student.surname = scanner.next();
                    System.out.println("Введите patronymic: ");
                    student.patronymic = scanner.next();
                    System.out.println("Введите school: ");
                    student.school = scanner.next();
                    System.out.println("Введите class: ");
                    student.clas = scanner.next();
                    System.out.println("Введите age: ");
                    student.age = scanner.next();
                    System.out.println("Введите grade: ");
                    student.grade = scanner.next();

                    return search(student);
                }
                case 3:
                {
                    int max_id=0;
                    for (Student lang : langList) {
                        if (max_id < Integer.parseInt(lang.id))
                        {
                            max_id=Integer.parseInt(lang.id);
                        }

                    }

                    Student student =new Student();

                    System.out.println("Введите id: ");
                    student.id = Integer.toString(max_id+1);
                    System.out.println("Введите name: ");
                    student.name= scanner.next();
                    System.out.println("Введите surname: ");
                    student.surname = scanner.next();
                    System.out.println("Введите patronymic: ");
                    student.patronymic = scanner.next();
                    System.out.println("Введите school: ");
                    student.school = scanner.next();
                    System.out.println("Введите class: ");
                    student.clas = scanner.next();
                    System.out.println("Введите age: ");
                    student.age = scanner.next();
                    System.out.println("Введите grade: ");
                    student.grade = scanner.next();

                    langList.add(new Student(student.id, student.name, student.surname, student.patronymic, student.school, student.clas, student.age, student.grade));
                    return langList;
                }
                case 4:
                {
                    System.out.println("Введите id записи которую хотите изменить: ");
                    String type = scanner.next();
                    String check = "NO";

                    String[] str = new String[8];

                    System.out.println("Введите name: ");
                    str[1] = scanner.next();
                    System.out.println("Введите surname: ");
                    str[2] = scanner.next();
                    System.out.println("Введите patronymic: ");
                    str[3] = scanner.next();
                    System.out.println("Введите school: ");
                    str[4] = scanner.next();
                    System.out.println("Введите class: ");
                    str[5] = scanner.next();
                    System.out.println("Введите age: ");
                    str[6] = scanner.next();
                    System.out.println("Введите grade: ");
                    str[7] = scanner.next();

                    for (Student lang : langList)
                    {
                        if(lang.id.equals(type)) {
                            if (!str[1].equals(check))
                                lang.name(str[1]);
                            if (!str[2].equals(check))
                                lang.surname(str[2]);
                            if (!str[3].equals(check))
                                lang.patronymic(str[3]);
                            if (!str[4].equals(check))
                                lang.school(str[4]);
                            if (!str[5].equals(check))
                                lang.clas(str[5]);
                            if (!str[6].equals(check))
                                lang.age(str[6]);
                            if (!str[7].equals(check))
                                lang.grade(str[7]);
                        }
                    }

                    return langList;

                }
                case 5:
                {
                    System.out.println("Введите id записи которую хотите удалить: ");
                    String type = scanner.next();

                    Iterator<Student> stuIterator = langList.iterator();//создаем итератор
                    while(stuIterator.hasNext()) {//до тех пор, пока в списке есть элементы

                        Student nextStu = stuIterator.next();//получаем следующий элемент
                        if (nextStu.id.equals(type)) {
                            stuIterator.remove();//удаляем кота с нужным именем
                        }
                    }

                    return langList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

