package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Customer;
import htl.steyr.rdp.model.SupplementaryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementaryPackageRepository extends JpaRepository<SupplementaryPackage, Integer> {
}
