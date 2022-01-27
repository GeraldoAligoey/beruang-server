package com.gma.challenge.beruang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
  
  @GetMapping()
  public String index(ModelMap map) {

    return "category";
  }
}
