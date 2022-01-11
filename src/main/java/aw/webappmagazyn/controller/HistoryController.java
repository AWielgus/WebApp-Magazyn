package aw.webappmagazyn.controller;

import aw.webappmagazyn.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping ("/history")
    public String history(Model model){
        model.addAttribute("list", historyRepository.findAll());
        return "history";
    }
}
