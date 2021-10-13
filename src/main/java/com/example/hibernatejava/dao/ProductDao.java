package com.example.hibernatejava.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.hibernatejava.entity.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    @PersistenceContext
    private EntityManager em;

    public Product findOne(int id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll(String search) {
        String queryStr = String.format("SELECT p FROM Product p WHERE p.name LIKE '%s'", "%" + search + "%");
        TypedQuery<Product> q = em.createQuery(queryStr, Product.class);
        return q.getResultList();
    }

    public void deleteOne(Product product) {
        em.remove(product);
    }

    public void deleteAll() {
        em.createQuery("DELETE FROM Product").executeUpdate();
    }

    public void saveOne(Product product) {
        em.persist(product);
    }

    public List<String> findCategoriesNames() {
        String queryStr = "SELECT DISTINCT p.category FROM Product p";
        TypedQuery<String> q = em.createQuery(queryStr, String.class);
        return q.getResultList();
    }

    public List<Product> findProductsByCategory(String cat) {
        String queryStr = String.format("SELECT p FROM Product p WHERE p.category = '%s'", cat);
        TypedQuery<Product> q = em.createQuery(queryStr, Product.class);
        return q.getResultList();
    }

    public Product updateOne(Product productToMerge) {
        return em.merge(productToMerge);
    }
}
