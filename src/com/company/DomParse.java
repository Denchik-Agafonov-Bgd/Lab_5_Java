package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParse {
    private String filePath = "file.xml";

    public void DomParseWrite(List<Student> langList)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement = doc.createElement( "Students");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);

            for (Student lang : langList) {
                rootElement.appendChild(getStudent(doc, lang));
            }

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(filePath);

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getStudent(Document doc, Student lang) {
        Element student = doc.createElement("Student");

        // создаем элемент name
        student.appendChild(getStudentElements(doc, student, "id", lang.id));
        student.appendChild(getStudentElements(doc, student, "name", lang.name));
        student.appendChild(getStudentElements(doc, student, "surname", lang.surname));
        student.appendChild(getStudentElements(doc, student, "patronymic", lang.patronymic));
        student.appendChild(getStudentElements(doc, student, "school", lang.school));
        student.appendChild(getStudentElements(doc, student, "clas", lang.clas));
        student.appendChild(getStudentElements(doc, student, "age", lang.age));
        student.appendChild(getStudentElements(doc, student, "grade", lang.grade));

        return student;
    }

    // утилитный метод для создание нового узла XML-файла
    private static Node getStudentElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}





