package service.impl;

import model.Product;
import service.Comparator;

public class ProductNameComparator implements Comparator {
    @Override
    public int compare(Product product1, Product product2) {
        return product1.getName().compareTo(product2.getName());
    }
}
