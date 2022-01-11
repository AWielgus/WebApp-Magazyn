package aw.webappmagazyn.controller;

import aw.webappmagazyn.model.History;
import aw.webappmagazyn.model.Product;
import aw.webappmagazyn.repository.HistoryRepository;
import aw.webappmagazyn.service.ProductService;
import aw.webappmagazyn.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class ProductController {

    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    ProductService productService;
    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/productAdd")
    public String addProductSite( Model model){
        model.addAttribute("type",productTypeService.findAll());
        model.addAttribute("product",new Product());
        return "product/productAdd";
    }

    @PostMapping("/productAdd")
    public String addProductSave(@ModelAttribute Product product, Model model){
        product.setHidden(false);
        product.setAmount(0);
        product.setCreationDate(LocalDateTime.now());
        product.setModificationDate(LocalDateTime.now());
        productService.save(product);

        model.addAttribute("listOfProducts", productService.findAll());
        return "redirect:/productList";
    }

    @GetMapping("/productList")
    public String productList(Model model){
        model.addAttribute("listOfProducts", productService.findAll());
        return "product/productList";
    }

    @GetMapping("/productList/remove/{id}")
    public String productRemove(@PathVariable Long id) {
        Product product = productService.getById(id);
        product.setHidden(true);
        History history = new History();
        history.setProductId(product.getName());
        history.setUpdateTime(LocalDateTime.now());
        history.setDescription(historyBuilding(productService.getById(id),product));
        historyRepository.save(history);
        product.setModificationDate(LocalDateTime.now());
        productService.save(product);
        return "redirect:/productList";
    }
    @GetMapping("/productList/edit/{id}")
    public String productEdit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("types",productTypeService.findAll());
        return "product/productEdit";
    }

    @PostMapping("/productList/update")
    public String productUpdate(Product product){

        History history = new History();
        history.setProductId(product.getName());
        history.setUpdateTime(LocalDateTime.now());
        history.setDescription(historyBuilding(productService.getById(product.getId()),product));
        historyRepository.save(history);
        product.setModificationDate(LocalDateTime.now());
        productService.save(product);
        return "redirect:/productList";
    }

    private String historyBuilding(Product productOld, Product productNew){
        String description = " ";
        if (!Objects.equals(productOld.getName(), productNew.getName())){
            description+= "Zmieniono nazwę z" + productOld.getName() + " na "+productNew.getName()+ " ";
        }
        if (!Objects.equals(productOld.getDescription(),productNew.getDescription())){
            description+="zmieniono opis ";
        }
        if (productOld.isHidden()){
            description += "usunięto produkt ";
        }
        if (!Objects.equals(productOld.getPrice(),productNew.getPrice())) {
            int price = productNew.getPrice() - productOld.getPrice();
            description+="Cena uległa zmianie o "+price+" ";
        }
        if (!Objects.equals(productOld.getAmount(),productNew.getAmount())){
            int amount = productNew.getAmount() - productOld.getAmount();
            if (amount > 0){
                description += "przyjęto "+ amount +" sztuk ";
            }else{
                description += "wydano "+ -amount +" sztuk ";
            }
        }
        return description;
    }
}
