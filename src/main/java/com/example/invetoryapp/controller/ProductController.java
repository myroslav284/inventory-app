package com.example.invetoryapp.controller;

import com.example.invetoryapp.model.Product;
import com.example.invetoryapp.repository.CategoryRepository;
import com.example.invetoryapp.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/products/new")
    public String showNewProductForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepo.findAll());
        return "product_form";
    }
    @PostMapping("/products/save")
    public String createProduct(Product product, HttpServletRequest request){
        String[] detailIDs = request.getParameterValues("detailID");
        String[] detailNames = request.getParameterValues("detailName");
        String[] detailValues = request.getParameterValues("detailValue");

        for (int i = 0; i < detailNames.length; i++) {
            if (detailIDs != null && detailIDs.length > 0) {
                product.setDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
            } else {
                product.addDetail(detailNames[i], detailValues[i]);
            }
        }
        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(Model model){
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model){
        Product product = productRepo.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productRepo.deleteById(id);
        return "redirect:/products";
    }
}
