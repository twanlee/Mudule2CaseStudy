package controller;

import model.Product;
import service.Comparator;
import service.impl.ProductServiceImpl;
import utils.TypesOfProduct;

import java.util.List;

public class ProductController {
    ProductServiceImpl productServiceImpl = new ProductServiceImpl();

    public void sort(List<Product> list, Comparator comparator){
        productServiceImpl.sort(list,comparator);
    }
    public Product find(List<Product> list, String idOrName){
        return productServiceImpl.find(list,idOrName);
    }
    public boolean remove(List<Product> list, String idOrName){
        return productServiceImpl.remove(list,idOrName);
    }
    public void update(List<Product> list, Product product, int id){
        productServiceImpl.update(list,product,id);
    }
    public void display(List<Product> list){
        productServiceImpl.display(list);
    }
    public void add(List<Product> list, Product product){
        productServiceImpl.add(list,product);
    }

    public Product factoryProduct(TypesOfProduct typesOfProduct){
        return productServiceImpl.factoryProduct(typesOfProduct);
    }
    public TypesOfProduct checkType(Product product){
        return productServiceImpl.checkType(product);
    }
    public int totalCostOfInventory(List<Product> list){
        return productServiceImpl.totalCostOfInventory(list);
    }
    public void importProduct(List<Product> products, String idOrName, int quantity){
        productServiceImpl.importProduct(products,idOrName,quantity);
    }
    public void exportProduct(List<Product> products, String idOrName, int quantity){
        productServiceImpl.exportProduct(products,idOrName,quantity);
    }
    public void displaySingleProduct(List<Product> products, String idOrName){
        productServiceImpl.displaySingleProduct(products,idOrName);
    }

}
