package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
