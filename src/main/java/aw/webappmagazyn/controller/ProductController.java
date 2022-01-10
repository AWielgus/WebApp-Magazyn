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
import org.springframework.web.bind.annotation.PathVariable;
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

        model.addAttribute("listOfProducts",productRepository.findAll());
        return "redirect:/productList";
    }

    @GetMapping("/productList")
    public String productList(Model model){
        model.addAttribute("listOfProducts",productRepository.findAll());
        return "product/productList";
    }

    @GetMapping("/productList/remove/{id}")
    public String productRemove(@PathVariable Long id) {
        productRepository.deleteById(id);

        return "redirect:/productList";
    }
    @GetMapping("/productList/edit/{id}")
    public String productEdit(@PathVariable Long id, Model model) {
        model.addAttribute("product",productRepository.getById(id));
        return "product/productEdit";
    }

    @PostMapping("/productList/update")
    public String productUpdate(Product product){
//        product.setModificationDate(LocalDateTime.now());
//        productRepository.save(product);
        System.out.println(product.toString());
        return "redirect:/productList";
    }

}
