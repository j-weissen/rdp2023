package htl.steyr.rdp.model;


import htl.steyr.rdp.utils.BillableBooking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "apartment_booking")
public class ApartmentBooking implements BillableBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start", nullable = false)
    @NonNull
    private LocalDate start;

    @Column(name = "end", nullable = false)
    @NonNull
    private LocalDate end;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(optional = false)
    @JoinColumn(name = "apartment_id", nullable = false)
    @NonNull
    private Apartment apartment;

    @Override
    public int calculatePrice() {
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        return (int) days * apartment.getPricePerDay();
    }
}
