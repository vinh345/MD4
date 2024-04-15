package com.example.bai1.controller;

import com.example.bai1.model.Product;
import com.example.bai1.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private IProductRepository productRepository;

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products"; // Chuyển hướng đến trang danh sách sản phẩm sau khi lưu sản phẩm
    }


    // hiển thị
    @GetMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }

    // cập nhật
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id); // lấy đối tượng từ data base
        if (product.isPresent()) { // tồn tại
            ModelAndView modelAndView = new ModelAndView("edit"); // tạo form edit
            modelAndView.addObject("product", product.get()); // thêm dữ liêu vào form
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute("customer") Product product) {
        productRepository.save(product);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Customer updated successfully");
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id); // Xóa sản phẩm từ cơ sở dữ liệu dựa trên ID
        return "redirect:/products"; // Chuyển hướng đến trang danh sách sản phẩm sau khi xóa thành công
    }

}
