package aw.webappmagazyn.controller;

import aw.webappmagazyn.model.Product;
import aw.webappmagazyn.repository.HistoryRepository;
import aw.webappmagazyn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;

@Controller
public class MainController {

    @Autowired
    ProductService productService;
    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/")
    public String index(Model model){
        int amountOfProduct = 0;
        int countOfProduct = 0;
        int valueOfWarehouse = 0;
        int countOfDeleted = 0;

        for(Product product : productService.findAllHidden()){
            if (product.isHidden()){
                countOfDeleted++;
            }else {
                countOfProduct++;
                amountOfProduct+=product.getAmount();
                valueOfWarehouse+=product.getAmount()*product.getPrice();
            }
        }
        model.addAttribute("a1",amountOfProduct);
        model.addAttribute("a2",countOfProduct);
        model.addAttribute("a3",valueOfWarehouse);
        model.addAttribute("a4",countOfDeleted);
        return "home";
    }

}
