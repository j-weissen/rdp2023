package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.CategoryDto;
import htl.steyr.rdp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}