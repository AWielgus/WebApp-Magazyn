package aw.webappmagazyn.controller;


import aw.webappmagazyn.model.ProductType;
import aw.webappmagazyn.repository.ProductTypeRepository;
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
    ProductTypeRepository productTypeRepository;

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
        productTypeRepository.save(productType);

        return "redirect:/typeList";
    }

    @GetMapping("/typeList")
    public String typeList(Model model){

        List<ProductType> listOfTypes = productTypeRepository.findAll();
        model.addAttribute("list",listOfTypes);
        return "type/typeList";
    }

    @GetMapping("/typeList/remove/{id}")
    public String typeRemove(@PathVariable Long id) {
        productTypeRepository.deleteById(id);
        return "redirect:/typeList";
    }
    @GetMapping("/typeList/edit/{id}")
    public String typeEdit(@PathVariable Long id, Model model) {
        model.addAttribute("productType",productTypeRepository.getById(id));
        return "type/typeEdit";
    }

    @PostMapping("/typeList/update")
    public String printerAdd(ProductType productType){
        productType.setModificationDate(LocalDateTime.now());
        productTypeRepository.save(productType);
        return "redirect:/typeList";
    }

//    <select class="col-form-label col-4 " id="printer-ip-input">
//         <option th:each="i : ${printers}" th:value="${i.ip +';'+ i.port}" th:text="${i.ip +' '+ i.name}">
//    </select>

}
