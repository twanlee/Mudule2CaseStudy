package model.concrete;

import model.Product;
import utils.TypesOfProduct;

public class Cigar extends Product {
    private TypesOfProduct type = TypesOfProduct.CIGAR;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    public Cigar(String name, int id, int price, int quantity, String description) {
        super(name, id, price,quantity, description);
        this.quantity = quantity;
    }
    @Override
    public String toString(){
        return type+","+super.toString();
    }
    @Override
    public String displayProductInfo(){
        return super.displayProductInfo() + " | Quantity: " + quantity;
    }
}
