package service.impl;

import model.Product;
import model.concrete.Book;
import model.concrete.Cigar;
import service.Comparator;
import service.ProductService;
import utils.TypesOfProduct;

import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    @Override
    public void sort(List<Product> list, Comparator comparator) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Product temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public Product find(List<Product> products, String idOrName) {
        for (Product product : products) {
            if (product.getName().equals(idOrName) || Integer.toString(product.getId()).equals(idOrName)){
                return product;
            }
        }return null;
    }


    @Override
    public boolean remove(List<Product> list, String idOrName) {
        Product productCheck = this.find(list, idOrName);
        if (productCheck != null) {
            list.remove(productCheck);
            System.out.println("Done");
            return true;
        } else
            return false;
    }

    @Override
    public void update(List<Product> list, Product product, int id) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                index = i;
            }
        }
        if (index != -1) {
            if(find(list,String.valueOf(product.getId()))==null){
                list.get(index).setName(product.getName());
                list.get(index).setDescription(product.getDescription());
                list.get(index).setPricePerUnit(product.getPricePerUnit());
                list.get(index).setId(product.getId());
            }else System.out.println("Duplicated product ID! Please check again!");
        }

    }

    public void display(List<Product> list) {
        for (Product product : list) {
            if(checkType(product) == TypesOfProduct.BOOK ){
                product = (Book) product;
                System.out.println(product.displayProductInfo());
            }else if (checkType(product) == TypesOfProduct.CIGAR){
                product = (Cigar) product;
                System.out.println(product.displayProductInfo());
            }
        }
    }

    @Override
    public void add(List<Product> list, Product product) {
        Product product1 = find(list, product.getName());
        Product product2 = find(list, Integer.toString(product.getId()));
        if (product1 == null && product2 == null) {
            list.add(product);
            System.out.println("Done");
        } else
            System.out.println("Duplicate product! ");

    }


    @Override
    public Product factoryProduct(TypesOfProduct typesOfProduct) {
        Scanner scanner = new Scanner(System.in);
        if(typesOfProduct == TypesOfProduct.BOOK){
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter Price: ");
            scanner.nextLine();
            int price = scanner.nextInt();
            System.out.println("Enter Quantity: ");
            scanner.nextLine();
            int quantity = scanner.nextInt();
            System.out.println("Enter description: ");
            scanner.nextLine();
            String description = scanner.nextLine();
            System.out.println("Enter the Author: ");
            String author = scanner.nextLine();
            return new Book(name,id,author,price,quantity,description);
        }else if(typesOfProduct == TypesOfProduct.CIGAR){
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter Price: ");
            scanner.nextLine();
            int price = scanner.nextInt();
            System.out.println("Enter description: ");
            scanner.nextLine();
            String description = scanner.nextLine();
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();
            return new Cigar(name,id,price,quantity,description);
        }
        return null;

    }

    @Override
    public TypesOfProduct checkType(Product product) {
        if(product instanceof Book) return TypesOfProduct.BOOK;
        else if(product instanceof Cigar) return TypesOfProduct.CIGAR;
        else return null;
    }

    @Override
    public int totalCostOfInventory(List<Product> list) {
        int sum=0;
        for (Product product : list){
            if(checkType(product)==TypesOfProduct.BOOK){
                product=(Book) product;
                sum+=product.getTotalPrice();
            }else if(checkType(product)==TypesOfProduct.CIGAR){
                product=(Cigar) product;
                sum+= product.getTotalPrice();
            }
        }
        return sum;
    }

    @Override
    public void importProduct(List<Product> list, String idOrName, int quantity) {
        Product product = find(list,idOrName);
        if(product!=null){
            int setQuantity = product.getQuantity()+quantity;
            if(setQuantity<0){
                System.out.println("Out of stock, please check again!");
            }else {
                product.setQuantity(setQuantity);
                System.out.println("Done");
            }

        }else System.out.println("Not found!");
    }

    @Override
    public void exportProduct(List<Product> list, String idOrName, int quantity) {
        Product product = find(list,idOrName);
        if(product!=null){
            int setQuantity = product.getQuantity()-quantity;
            if(setQuantity<0){
                System.out.println("Out of stock, please check again!");
            }else {
                product.setQuantity(setQuantity);
                System.out.println("Done");
            }

        }else System.out.println("Not found!");
    }

    @Override
    public void displaySingleProduct(List<Product> list, String idOrName) {
        Product product = find(list,idOrName);
        if(checkType(product)== TypesOfProduct.BOOK){
            product = (Book) product;
            System.out.println(product.displayProductInfo());
        }
        else if(checkType(product) == TypesOfProduct.CIGAR){
            product = (Cigar) product;
            System.out.println(product.displayProductInfo());
        }else System.out.println("Not found!");
    }


}
