package ru.geekbrains.homework6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework6.repositories.CustomerRepository;
import ru.geekbrains.homework6.repositories.ProductRepository;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void createProduct(String name, int cost){
        productRepository.createProduct(name,cost);
    }
    public void updateProduct(int id, String name, int cost){
        productRepository.updateProduct(id,name,cost);
    }
    public void getProduct(int id){
        productRepository.getProduct(id);
    }
    public void deleteProduct(int id){
        productRepository.deleteProduct(id);
    }

    public void createCustomer(String name){
        customerRepository.createCustomer(name);
    }
    public void updateCustomer(int id, String name){
        customerRepository.updateCustomer(id,name);
    }
    public void getCustomer(int id){
        customerRepository.getCustomer(id);
    }
    public void deleteCustomer(int id){
        customerRepository.deleteCustomer(id);
    }

    public void getProdByCustomerId(int id){
        customerRepository.prodById(id);
    }
    public void getCustByProdId(int id){
        productRepository.custById(id);
    }

}
