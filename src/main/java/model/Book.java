package model;


public class Book {
    private int num;
    private String booktitle;
    private int price;
    private String publisher;
    private String author;
    private int saleprice;
    private String isbn;

    @Override
    public String toString() {
        return booktitle + " | " + price +
                " | " + publisher + " | " + author +
                " | " + saleprice + " | " + isbn;
    }

    public Book() {}

    public Book(int num, String booktitle, int price, String publisher, String author, int saleprice, String isbn) {
        this.num = num;
        this.booktitle = booktitle;
        this.price = price;
        this.publisher = publisher;
        this.author = author;
        this.saleprice = saleprice;
        this.isbn = isbn;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(int saleprice) {
        this.saleprice = saleprice;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}