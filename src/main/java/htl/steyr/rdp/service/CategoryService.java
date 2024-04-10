package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.CategoryDto;
import htl.steyr.rdp.model.Category;
import htl.steyr.rdp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public CategoryDto map(Category in) {
        return new CategoryDto(in.getId(), in.getName());
    }

    public List<CategoryDto> findAll() {
        return repo.findAll().stream().
                map(this::map).
                collect(Collectors.toList());
    }
}
