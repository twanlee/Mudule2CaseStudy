package service.impl;

import model.concrete.Book;
import model.Product;
import org.junit.jupiter.api.Test;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @Test
    void findTest1() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));

        ProductService productService = new ProductServiceImpl();
        String  productExpected = "Hoàng tử bé";
        String check = productService.find(books,"1111").getName();
        assertEquals(productExpected,check);
    }
    @Test
    void findTest2() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        int  productExpected = Integer.parseInt("1112");
        Integer check = productService.find(books,"1112").getId();
        assertEquals(productExpected,check);
    }
    @Test
    void findTest3() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        String  productExpected = "Tuan Lee";
        String check = ((Book)productService.find(books,"Hoàng tử")).getAuthor();
        assertEquals(productExpected,check);
    }

    @Test
    void addTest() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        productService.add(books,new Book("Anh bé",1114,"Tuan Lee",1000,2,"Fiction"));
        String  book = books.get(3).getName();
        String bookExpected = "Anh bé";
        assertEquals(book,bookExpected);
    }

    @Test
    void removeTest() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        productService.remove(books,"1111");
        productService.remove(books,"1112");
        productService.remove(books,"1113");
        int expected = 0;
        int actual = books.size();
        assertEquals(expected,actual);
    }


    @Test
    void totalCostOfInventoryTest() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        int expected = 14000;
        int actual = productService.totalCostOfInventory(books);
        assertEquals(expected,actual);
    }

    @Test
    void importProductTest() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,2,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        productService.importProduct(books,"1111",10);
        int actual = productService.find(books,"1111").getQuantity();
        int expected = 12;
        assertEquals(expected,actual);
    }

    @Test
    void exportProductTest() {
        List<Product> books = new ArrayList<>();
        books.add(new Book("Hoàng tử bé",1111,"Tuan Lee",2000,4,"Fiction"));
        books.add(new Book("Hoàng tử",1112,"Tuan Lee",2000,3,"Fiction"));
        books.add(new Book("Anh tử bé",1113,"Tuan Lee",2000,2,"Fiction"));
        ProductService productService = new ProductServiceImpl();
        productService.exportProduct(books,"1111",2);
        int actual = productService.find(books,"1111").getQuantity();
        int expected = 2;
        assertEquals(expected,actual);
    }
}