package model.concrete;

import model.Product;
import utils.TypesOfProduct;

public class Book extends Product {
    private TypesOfProduct type = TypesOfProduct.BOOK;
    private String author;
    public Book(String name, int id,String author, int price,int quantity, String description) {
        super(name, id, price,quantity, description);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString(){
        return type+","+super.toString()+","+author;
    }
    @Override
    public String displayProductInfo(){
        return super.displayProductInfo()+" | Author: "+author;
    }
}
