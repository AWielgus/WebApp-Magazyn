package aw.webappmagazyn.service;

import aw.webappmagazyn.model.ProductType;
import aw.webappmagazyn.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    public List<ProductType> findAll(){
        List<ProductType> list = new ArrayList<>();
        for (ProductType type:productTypeRepository.findAll()) {
            if (!type.isHidden()){
                list.add(type);
            }
        }
        return list;
    }
    public List<ProductType> findAllHidden(){
        return productTypeRepository.findAll();
    }

    public ProductType getById(Long id){
        return productTypeRepository.getById(id);
    }

    public void save(ProductType productType){
        productTypeRepository.save(productType);
    }

}
