package ru.geekbrains.homework6.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework6.entities.Customer;
import ru.geekbrains.homework6.entities.Product;


import javax.persistence.EntityManagerFactory;

@Component
public class CustomerRepository {

    private SessionFactory factory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createCustomer(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer newCust = new Customer(name);
            session.save(newCust);
            session.getTransaction().commit();
        }
    }

    public void getCustomer(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            System.out.println(session.get(Customer.class, id));
            session.getTransaction().commit();
        }
    }

    public void updateCustomer(int id, String newName) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer newCust = new Customer(id, newName);
            session.update(newCust);
            session.getTransaction().commit();
        }
    }

    public void deleteCustomer(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customerToRemove = session.get(Customer.class, id);
            session.createNativeQuery(String.format("DELETE from customers_prod WHERE customers_id = %d",id)).executeUpdate();
            session.delete(customerToRemove);
            session.getTransaction().commit();
        }
    }
    public void prodById(int id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer cus = session.get(Customer.class, id);
            System.out.println(session.get(Customer.class, id));
            for (Product product : cus.getProducts()) System.out.println(product);
            session.getTransaction().commit();
        }
    }
}
