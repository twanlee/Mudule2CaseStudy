package model;

import utils.TypesOfProduct;

public abstract class Product {
    private TypesOfProduct type;
    private String name;
    private int id;
    private int pricePerUnit;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    private String description;

    public Product(String name, int id, int pricePerUnit, int quantity, String description) {
        this.name = name;
        this.id = id;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getTotalPrice(){
        return pricePerUnit *quantity;
    }

    @Override
    public String toString(){
        return name+","+id+","+ pricePerUnit +","+quantity+","+description;
    }
    public String  displayProductInfo(){
        return "Name: " + name + " | ID " + id + " | Price: " + pricePerUnit +" | Quantity: "+quantity+" | Total Price: "+ getTotalPrice()+" | Description: " + description;
    }
}
