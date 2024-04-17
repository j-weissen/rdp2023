package htl.steyr.rdp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void calculateTotalPrice() {
        var customer = new Customer("a");
        var category = new Category("category");
        var apartment1 = new Apartment("a1", 1, 1000, category);
        var apartment2 = new Apartment("a2", 1, 2000, category);
        var supplementaryPackage1 = new SupplementaryPackage("sp1", 100);
        var supplementaryPackage2 = new SupplementaryPackage("sp2", 200);
        var apartmentBooking1 = new ApartmentBooking(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), apartment1);
        var apartmentBooking2 = new ApartmentBooking(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-05"), apartment2);
        var supplementaryPackageBooking1 = new SupplementaryPackageBooking(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), 3, supplementaryPackage1);
        var supplementaryPackageBooking2 = new SupplementaryPackageBooking(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-05"), 3, supplementaryPackage2);
        var booking = new Booking(Set.of(apartmentBooking1, apartmentBooking2), Set.of(supplementaryPackageBooking1, supplementaryPackageBooking2), customer);
        assertEquals(260.0, booking.calculateTotalPrice());
    }
}