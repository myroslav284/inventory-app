package com.example.invetoryapp.controller;

import com.example.invetoryapp.model.Category;
import com.example.invetoryapp.model.Product;
import com.example.invetoryapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository repo;

    @GetMapping("/categories")
    private String listCategories(Model model){
        List<Category> listCategories = repo.findAll();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }

    @GetMapping("/categories/new")
    private String createCategory(Model model){
        model.addAttribute("category", new Category());
        return "category_form";
    }
    @PostMapping("/categories/save")
    private String saveCategory(Category category){
        repo.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model){
        Category category = repo.findById(id).get();
        model.addAttribute("category", category);
        repo.save(category);
        return "category_form";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        repo.deleteById(id);
        return "redirect:/categories";
    }
}
