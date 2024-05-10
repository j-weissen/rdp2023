package htl.steyr.rdp.model;


import htl.steyr.rdp.utils.BillableBooking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "supplementary_package_booking")
public class SupplementaryPackageBooking implements BillableBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "end", nullable = false)
    @NonNull
    private LocalDate start;

    @Column(name = "start", nullable = false)
    @NonNull
    private LocalDate end;

    @Column(name = "amount", nullable = false)
    @NonNull
    private Integer amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplementary_package_id", nullable = false)
    @NonNull
    private SupplementaryPackage supplementaryPackage;

    @ManyToOne()
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Override
    public int calculatePrice() {
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        return (int) days * amount * supplementaryPackage.getPrice();
    }
}
