package htl.steyr.rdp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "supplementary_package_booking")
public class SupplementaryPackageBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false)
    @NonNull
    private Integer amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplementary_package_id", nullable = false)
    @NonNull
    private SupplementaryPackage supplementaryPackage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @NonNull
    private Booking booking;

}
