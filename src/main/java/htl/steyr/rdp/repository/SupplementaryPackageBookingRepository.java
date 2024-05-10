package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.SupplementaryPackage;
import htl.steyr.rdp.model.SupplementaryPackageBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementaryPackageBookingRepository extends JpaRepository<SupplementaryPackageBooking, Integer> {
}
