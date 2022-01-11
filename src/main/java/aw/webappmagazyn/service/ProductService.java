package aw.webappmagazyn.service;

import aw.webappmagazyn.model.Product;
import aw.webappmagazyn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        List<Product> list = new ArrayList<>();
        for (Product type:productRepository.findAll()) {
            if (!type.isHidden()){
                list.add(type);
            }
        }
        return list;
    }
    public List<Product> findAllHidden(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.getById(id);
    }

    public void save(Product product){
        productRepository.save(product);
    }
}
