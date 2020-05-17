package service.impl;

import model.Product;
import service.Comparator;

public class ProductPriceComparator implements Comparator {
    @Override
    public int compare(Product product1, Product product2) {
        return product1.getPricePerUnit() - product2.getPricePerUnit();
    }
}
