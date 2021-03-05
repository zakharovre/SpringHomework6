package ru.geekbrains.homework6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.geekbrains.homework6.services.ShopService;

@SpringBootApplication
public class Homework6Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Homework6Application.class, args);

        PrepareData prepareData = (PrepareData) context.getBean("prepareData");
        prepareData.init();

        ShopService service = (ShopService) context.getBean("shopService");

        //service.createProduct("Phone",50000);

        //service.updateProduct(2,"Book", 300);

        //service.getProduct(8);

        //service.deleteProduct(5);

        //service.createCustomer("Vladimir");

        //service.updateCustomer(2,"Anton");

        //service.getCustomer(4);

        //service.deleteCustomer(5);

        service.getProdByCustomerId(4);

        service.getCustByProdId(3);

        prepareData.shutdown();
    }
}
