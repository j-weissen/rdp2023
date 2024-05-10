package htl.steyr.rdp.service;

import htl.steyr.rdp.model.Customer;
import htl.steyr.rdp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    public CustomerDto map(Customer in) {
        return new CustomerDto(in.getId(), in.getName());
    }

    public List<CustomerDto> findAll() {
        return repo.findAll().stream().
                map(this::map).
                collect(Collectors.toList());
    }
}
