package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.CustomerDto;
import htl.steyr.rdp.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
