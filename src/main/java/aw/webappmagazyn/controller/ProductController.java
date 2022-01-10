package aw.webappmagazyn.controller;

import aw.webappmagazyn.model.Product;
import aw.webappmagazyn.model.ProductType;
import aw.webappmagazyn.repository.ProductRepository;
import aw.webappmagazyn.repository.ProductTypeRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/productAdd")
    public String addProductSite( Model model){
        model.addAttribute("type",productTypeRepository.findAll());
        model.addAttribute("product",new Product());
        return "product/productAdd";
    }

    @PostMapping("/productAdd")
    public String addProductSave(@ModelAttribute Product product, Model model){
        product.setHidden(false);
        product.setAmount(0);
        product.setCreationDate(LocalDateTime.now());
        product.setModificationDate(LocalDateTime.now());
        productRepository.save(product);
        return "product/productList";
    }

}
