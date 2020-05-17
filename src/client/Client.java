package client;

import controller.ProductController;
import model.Product;
import model.concrete.Book;
import model.concrete.Cigar;
import repository.impl.ProductRepository;
import service.impl.ProductNameComparator;
import service.impl.ProductPriceComparator;
import utils.TypesOfProduct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    Scanner sc = new Scanner(System.in);
    List<Product> myProducts = new ArrayList<>();
    ProductController productController = new ProductController();
    ProductRepository productRepository = new ProductRepository();
    ProductPriceComparator productPriceComparator = new ProductPriceComparator();
    ProductNameComparator productNameComparator = new ProductNameComparator();
    boolean flagAdmin = false;
    boolean flagStaff = false;



    public final String adminPassword = "123";
    public final String staffPassword = "111";
    public String filePath = "Products";
    File file = new File(filePath);

    public boolean checkFirstTime(){
        if(file.length()==0){
            return true;
        }else
            return false;
    }
    public void menu(){
        getLoad();
        System.out.println("======Menu======");
        System.out.println("Identify yourself");
        System.out.println("1. Admin");
        System.out.println("2. Staff");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                adminLogin();
                checkMenu();
                break;
            case 2:
                staffLogin();
                checkMenu();
                break;
            default:
                System.out.println("Enter 1 or 2");
                menu();
        }
    }
    public void checkMenu(){
        if(flagAdmin){
            adminMenu();
        }else if(flagStaff){
            staffMenu();
        }
    }

    public boolean adminLogin(){
        System.out.println("Enter password: ");
        String password = sc.next();
        if(password.equals(adminPassword)){
            return flagAdmin=true;
        }else {
            System.out.println("Wrong password! Check again!");
            adminLogin();
        }
        return false;
    }
    public boolean staffLogin(){
        System.out.println("Enter password: ");
        String password = sc.next();
        if (password.equals(staffPassword)){
            return flagStaff=true;
        }
        else {
            System.out.println("Wrong password! Check again!");
            staffLogin();
        }
        return false;
    }
    public void adminMenu(){
        if (checkFirstTime()) {
            System.out.println("First time running! Please add a new Product");
            System.out.println("-------");
            addNewProduct();
        }else {
            while (true) {
                System.out.println("=====Menu====");
                System.out.println("0. Exit");
                System.out.println("1. Sort by Price");
                System.out.println("2. Sort by Name");
                System.out.println("3. Find a product by ID or Name");
                System.out.println("4. Display all of products");
                System.out.println("5. Update product");
                System.out.println("6. Add a new product");
                System.out.println("7. Import product");
                System.out.println("8. Export product");
                System.out.println("9. Delete product");
                System.out.println("10. Show all cost of Inventory");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        productController.sort(myProducts,productPriceComparator);
                        productController.display(myProducts);
                        productRepository.save(myProducts);
                        break;
                    case 2:
                        productController.sort(myProducts,productNameComparator);
                        productController.display(myProducts);
                        productRepository.save(myProducts);
                        break;
                    case 3:
                        System.out.println("Enter product ID or Name");
                        sc.nextLine();
                        String idOrName = sc.nextLine();
                        productController.displaySingleProduct(myProducts,idOrName);
                        break;
                    case 4:
                        productController.display(myProducts);
                        break;
                    case 5:
                        System.out.println("Enter id");
                        sc.nextLine();
                        String id = sc.nextLine();
                        Product product = productController.find(myProducts,id);
                        if(product==null){
                            System.out.println("Not found!");
                        }else {
                            TypesOfProduct checkType = productController.checkType(productController.find(myProducts,id));
                            Product newProduct = productController.factoryProduct(checkType);
                            productController.update(myProducts,newProduct,Integer.parseInt(id));
                            productController.displaySingleProduct(myProducts,String.valueOf(newProduct.getId()));
                            productRepository.save(myProducts);
                        }
                        break;
                    case 6:
                        addNewProduct();
                        break;
                    case 7:
                        System.out.println("Enter ID or Name of product");
                        sc.nextLine();
                        String idName = sc.nextLine();
                        System.out.println("Enter quantity");
                        int quantity = sc.nextInt();
                        productController.importProduct(myProducts,idName,quantity);
                        productController.displaySingleProduct(myProducts,idName);
                        productRepository.save(myProducts);
                        break;
                    case 8:
                        System.out.println("Enter ID or Name of product");
                        sc.nextLine();
                        String idorName = sc.nextLine();
                        System.out.println("Enter quantity");
                        int number = sc.nextInt();
                        productController.exportProduct(myProducts,idorName,number);
                        productController.displaySingleProduct(myProducts,idorName);
                        productRepository.save(myProducts);
                        break;
                    case 9:
                        System.out.println("Enter ID of Name");
                        sc.nextLine();
                        String checkID = sc.nextLine();
                        productController.remove(myProducts,checkID);
                        productController.display(myProducts);
                        System.out.println("Done");
                        productRepository.save(myProducts);
                        break;
                    case 10:
                        System.out.println(productController.totalCostOfInventory(myProducts)+" VND");
                        break;
                    case 0:
                        productRepository.save(myProducts);
                        System.exit(0);
                    default:
                        System.out.println("Enter your choice again!");
                }
            }
        }


    }
    public void staffMenu(){
        if (checkFirstTime()) {
            System.out.println("First time running! Please add a new Product");
            System.out.println("-------");
            addNewProduct();
        }else {
            while (true) {
                System.out.println("=====Menu====");
                System.out.println("0. Exit");
                System.out.println("1. Sort by Price");
                System.out.println("2. Sort by Name");
                System.out.println("3. Find a product by ID or Name");
                System.out.println("4. Display all of products");
                System.out.println("5. Update product");
                System.out.println("6. Add a new product");
                System.out.println("7. Import product");
                System.out.println("8. Export product");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        productController.sort(myProducts,productPriceComparator);
                        productController.display(myProducts);
                        productRepository.save(myProducts);
                        break;
                    case 2:
                        productController.sort(myProducts,productNameComparator);
                        productController.display(myProducts);
                        productRepository.save(myProducts);
                        break;
                    case 3:
                        System.out.println("Enter product ID or Name");
                        sc.nextLine();
                        String idOrName = sc.nextLine();
                        productController.displaySingleProduct(myProducts,idOrName);
                        break;
                    case 4:
                        productController.display(myProducts);
                        break;
                    case 5:
                        System.out.println("Enter id");
                        sc.nextLine();
                        String id = sc.nextLine();
                        TypesOfProduct checkType = productController.checkType(productController.find(myProducts,id));
                        Product newProduct = productController.factoryProduct(checkType);
                        productController.update(myProducts,newProduct,Integer.parseInt(id));
                        productController.display(myProducts);
                        productRepository.save(myProducts);
                        break;
                    case 6:
                        addNewProduct();
                        break;
                    case 7:
                        System.out.println("Enter ID or Name of product");
                        sc.nextLine();
                        String idName = sc.nextLine();
                        System.out.println("Enter quantity");
                        int quantity = sc.nextInt();
                        productController.importProduct(myProducts,idName,quantity);
                        productController.displaySingleProduct(myProducts,idName);
                        productRepository.save(myProducts);
                        break;
                    case 8:
                        System.out.println("Enter ID or Name of product");
                        sc.nextLine();
                        String idorName = sc.nextLine();
                        System.out.println("Enter quantity");
                        int number = sc.nextInt();
                        productController.exportProduct(myProducts,idorName,number);
                        productController.displaySingleProduct(myProducts,idorName);
                        productRepository.save(myProducts);
                        break;
                    case 0:
                        productRepository.save(myProducts);
                        System.exit(0);
                    default:
                        System.out.println("Enter your choice again!");
                }
            }
        }


    }

    public void getLoad() {
        productRepository.load(myProducts);
    }

    public void addNewProduct() {
        while (true) {
            System.out.println("Which type of Product do you want to add?");
            System.out.println("0. Back");
            System.out.println("1.Book");
            System.out.println("2.Cigar");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    productController.add(myProducts, productController.factoryProduct(TypesOfProduct.BOOK));
                    productRepository.save(myProducts);
                    break;
                case 2:
                    productController.add(myProducts, productController.factoryProduct(TypesOfProduct.CIGAR));
                    productRepository.save(myProducts);
                    break;
                case 3:
                    System.exit(0);
                case 0:
                    checkMenu();
                default:
                    System.out.println("Enter 0 or 1 or 2");
            }
        }
    }



}
