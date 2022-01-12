package aw.webappmagazyn.controller;


import aw.webappmagazyn.model.ProductType;
import aw.webappmagazyn.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping("/typeAdd")
    public String addTypeSite( Model model){
        model.addAttribute("productType",new ProductType());
        return "type/typeAdd";
    }

    @PostMapping("/typeAdd")
    public String saveNewType(@ModelAttribute ProductType productType){
        productType.setCreationDate(LocalDateTime.now());
        productType.setModificationDate(LocalDateTime.now());
        productType.setHidden(false);
        productTypeService.save(productType);
        return "redirect:/typeList";
    }

    @GetMapping("/typeList")
    public String typeList(Model model){

        List<ProductType> listOfTypes = productTypeService.findAll();
        model.addAttribute("list",listOfTypes);
        return "type/typeList";
    }

    @GetMapping("/typeList/remove/{id}")
    public String typeRemove(@PathVariable Long id) {
        ProductType productType = productTypeService.getById(id);
        productType.setHidden(true);
        productType.setModificationDate(LocalDateTime.now());
        productTypeService.save(productType);
        return "redirect:/typeList";
    }
    @GetMapping("/typeList/edit/{id}")
    public String typeEdit(@PathVariable Long id, Model model) {
        model.addAttribute("productType",productTypeService.getById(id));
        return "type/typeEdit";
    }

    @PostMapping("/typeList/update")
    public String editUpdate(ProductType productType){
        productType.setModificationDate(LocalDateTime.now());
        productTypeService.save(productType);
        return "redirect:/typeList";
    }

}
