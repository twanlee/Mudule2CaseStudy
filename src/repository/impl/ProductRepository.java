package repository.impl;

import model.concrete.Book;
import model.Product;
import model.concrete.Cigar;
import repository.IGeneralRepository;
import utils.TypesOfProduct;

import java.io.*;
import java.util.List;

public class ProductRepository implements IGeneralRepository {
    @Override
    public synchronized void save(List<Product> list) {
        try {
            PrintStream ps = new PrintStream("Products");
            for (Product product: list){
                ps.println(product.toString());
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load(List<Product> list) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Products"));
            String line ;
            while ((line=bufferedReader.readLine()) != null){
                String[] attributes = line.split(",");
                String type = attributes[0];
                if(type.equalsIgnoreCase(String.valueOf(TypesOfProduct.BOOK))){
                    String name = attributes[1];
                    int id = Integer.parseInt(attributes[2]);
                    int price = Integer.parseInt(attributes[3]);
                    int quantity = Integer.parseInt(attributes[4]);
                    String description = attributes[5];
                    String author = attributes[6];
                    list.add(new Book(name,id,author,price,quantity,description));
                }else if(type.equalsIgnoreCase(String.valueOf(TypesOfProduct.CIGAR))){
                    String name = attributes[1];
                    int id = Integer.parseInt(attributes[2]);
                    int price = Integer.parseInt(attributes[3]);
                    int quantity = Integer.parseInt(attributes[4]);
                    String description = attributes[5];
                    list.add(new Cigar(name,id,price,quantity,description));
                }

            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
