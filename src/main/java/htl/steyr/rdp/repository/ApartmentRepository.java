package htl.steyr.rdp.repository;

import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.ApartmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    @Query("""
            SELECT a
            FROM Apartment a
            WHERE a.id NOT IN (
                SELECT ab.apartment.id
                FROM ApartmentBooking ab
                WHERE (ab.start <= :end AND ab.end >= :start)
            )
            """)
    List<Apartment> findAvailableBetween(LocalDate start, LocalDate end);

    @Query("""
        SELECT ab
        FROM ApartmentBooking ab WHERE ab.apartment = :apartment
        AND NOT (ab.start <= :end AND ab.end >= :start)
   """)
    List<ApartmentBooking> apartmentIsFreeBetween(Apartment apartment, LocalDate start, LocalDate end);
}