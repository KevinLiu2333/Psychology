package com.lucky.axis.service;

import java.util.HashMap;
import java.util.Map;

public class PersonInfoService {
    public PersonInfoService() {
    }

    public String getPersonInfo(Person person) {
        String personinfo = "";
        personinfo = "Hello, " + person.getName()
                + " ! This is you information\r\n";
        personinfo = personinfo + "Ages: " + person.getAge() + "\r\n";
        Map skill = person.getSkill();
        String strskill = "";
        strskill = strskill + skill.get("NAME") + " : you have "
                + skill.get("YEAR") + " years experience!\r\n";
        personinfo = personinfo + strskill;
        personinfo = personinfo + "Book Name : "
                + person.getMybook().getBookname() + "\r\n";
        personinfo = personinfo + "ISBN : "
                + person.getMybook().getIsbn() + "\r\n";
        personinfo = personinfo + "You pay $"
                + person.getMybook().getPrice() + " for this book.\r\n";
        personinfo = personinfo + "This book have "
                + person.getMybook().getPages() + " pages.\r\n";
        return personinfo;
    }

    public Book setBookInfo(String bookname, Long isbn) {
        Book book = new Book();
        book.setBookname(bookname);
        book.setIsbn(isbn);
        book.setPages(265);
        book.setPrice((float) 25.65);
        return book;
    }

    public Person getPersonInfo() {
        Person person = new Person();
        person.setName("Aaron");
        person.setAge(26);
        Map skill = new HashMap();
        skill.put("NAME", "JAVA");
        skill.put("YEAR", "3");
        person.setSkill(skill);
        Book book = new Book();
        book.setBookname("<Thinking in Java>");
        book.setIsbn(456256821L);
        book.setPages(982);
        book.setPrice((float) 99.8);
        person.setMybook(book);
        return person;
    }

}