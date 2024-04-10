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
@Table(name = "apartment_booking")
public class ApartmentBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

}
