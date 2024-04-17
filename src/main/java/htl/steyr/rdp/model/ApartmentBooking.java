package htl.steyr.rdp.model;


import htl.steyr.rdp.utils.BillableBooking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
    private Date end;

    @Column(name = "end", nullable = false)
    @NonNull
    private Date start;

    @Column(name = "amount", nullable = false)
    @NonNull
    private Integer amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id")
    @NonNull
    private Booking booking;

    @ManyToOne(optional = false)
    @JoinColumn(name = "apartment_id", nullable = false)
    @NonNull
    private Apartment apartment;

    @Override
    public int calculatePrice() {
        return (int) ChronoUnit.DAYS.between(start.toInstant(), end.toInstant()) * apartment.getPricePerDay();
    }
}
