package ru.geekbrains.homework6.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.entities.Customer;
import ru.geekbrains.homework6.entities.Product;

import javax.persistence.EntityManagerFactory;

@Component
public class ProductRepository {

    private SessionFactory factory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createProduct(String name, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product newProd = new Product(name, cost);
            session.save(newProd);
            session.getTransaction().commit();
        }
    }

    public void getProduct(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            System.out.println(session.get(Product.class, id));
            session.getTransaction().commit();
        }
    }

    public void updateProduct(int id, String newName, int newCost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product newProd = new Product(id, newName, newCost);
            session.update(newProd);
            session.getTransaction().commit();
        }
    }

    public void deleteProduct(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product productToRemove = session.get(Product.class, id);
            session.createNativeQuery(String.format("DELETE from customers_prod WHERE products_id = %d",id)).executeUpdate();
            session.delete(productToRemove);
            session.getTransaction().commit();
        }
    }

    public void custById(int id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product prod = session.get(Product.class, id);
            System.out.println(session.get(Product.class, id));
            for (Customer customer : prod.getCustomers()) System.out.println(customer);
            session.getTransaction().commit();
        }
    }
}
