package com.example.hibernatejava.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.hibernatejava.dao.ProductDao;
import com.example.hibernatejava.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import com.example.hibernatejava.exceptions.WrongIndexException;
import com.example.hibernatejava.utils.UpdateFields;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductDao productDao;

    public Product findProductById(int id) throws Exception {
        return productDao.findOne(id);
    }

    public List<Product> findAllProducts(String search) throws Exception {
        return productDao.findAll(search);
    }

    public Product newProduct(Product product) {
        productDao.saveOne(product);
        return product;
    }

    public void deleteProduct(int id) throws Exception {
        Product foundProduct = productDao.findOne(id);

        if (foundProduct == null) {
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        productDao.deleteOne(foundProduct);

    }

    public List<String> findProductCategoriesNames() throws Exception {
        return productDao.findCategoriesNames();
    }

    public List<Product> findProductsByCategory(String cat) throws Exception {
        return productDao.findProductsByCategory(cat);
    }

    public List<Product> findProductFromCategory(String cat, int index) throws Exception {

        if (index < 0) {
            throw new WrongIndexException();
        }

        return productDao.findProductsByCategory(cat);
    }

    public void deleteAllProducts() {
        productDao.deleteAll();
    }

    public Product updateProduct(int id, Product fieldsToUpdate) throws Exception {
        Product productToUpdate = productDao.findOne(id);

        if (productToUpdate == null) {
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        UpdateFields.updateProductFields(fieldsToUpdate, productToUpdate);

        return productDao.updateOne(productToUpdate);
    }
}
