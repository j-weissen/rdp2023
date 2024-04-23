package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    @Query("SELECT ab.apartment FROM ApartmentBooking ab WHERE NOT ((ab.start <= :end) AND (ab.end >= :start))")
    public Set<Apartment> findAvailableBetween(LocalDate start, LocalDate end);
}
