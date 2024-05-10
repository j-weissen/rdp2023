package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.ApartmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface ApartmentBookingRepository extends JpaRepository<ApartmentBooking, Integer> {
}
