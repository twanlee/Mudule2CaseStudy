package service;

import model.Product;
import utils.TypesOfProduct;
import java.util.List;

public interface ProductService {
    void sort(List<Product> list, Comparator comparator);

    Product find(List<Product> products, String idOrName);

    boolean remove(List<Product> list, String idOrName);
    void update(List<Product> list, Product product, int id);
    void display(List<Product> list);
    void add(List<Product>list, Product product);
    Product factoryProduct(TypesOfProduct typesOfProduct);
    TypesOfProduct checkType(Product product);
    int totalCostOfInventory(List<Product> list);
    void importProduct(List<Product> list,String idOrName,int quantity);
    void exportProduct(List<Product> list, String idOrName, int quantity);
    void displaySingleProduct(List<Product> list, String idOrName);

}
