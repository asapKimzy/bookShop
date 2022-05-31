package Model;

import Model.Genre;

import java.math.BigDecimal;

public class Book {// класс книг с конструкторами, геттерами и сеттерами
    private int id;
    private String name;
    private int num;
    private BigDecimal price;
    private String author;
    private Genre genre;

    public Book (String name, int num, BigDecimal price, String author, Genre genre ){
        this.name=name;
        this.num=num;
        this.price=price;
        this.author=author;
        this.genre=genre;
    }
    public Book(){}
public Book(int id,String name, int num, BigDecimal price, String author, Genre genre){
    this.id=id;
    this.name=name;
    this.num=num;
    this.price=price;
    this.author=author;
    this.genre=genre;
}

    public int getId() {
        return id;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public String toString(){
        return id+" "+name+" "+num+" "+price+" "+author+" "+genre;
    }
}
