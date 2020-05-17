package repository;

import model.Product;

import java.util.List;

public interface IGeneralRepository {
    void save(List<Product> list);
    void load(List<Product> list);
}
