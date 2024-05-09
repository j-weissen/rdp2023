package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    @Query("""
            SELECT a
            FROM Apartment a
            WHERE a.id NOT IN (
                SELECT ab.apartment.id
                FROM ApartmentBooking ab
                WHERE (ab.start <= :start AND ab.end >= :end)
            )
            """)
    Set<Apartment> findAvailableBetween(LocalDate start, LocalDate end);
}
