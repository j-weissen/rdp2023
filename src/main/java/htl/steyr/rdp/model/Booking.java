package htl.steyr.rdp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "booking")
    private Set<ApartmentBooking> apartmentBookings;

    @OneToMany(mappedBy = "booking")
    private Set<SupplementaryPackageBooking> supplementaryPackageBookings;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public double calculateTotalPrice() {
        int apartmentsPrice = apartmentBookings.stream().map(ApartmentBooking::calculatePrice).reduce(0, Integer::sum);
        int supplementaryPackagesPrice = supplementaryPackageBookings.stream().map(SupplementaryPackageBooking::calculatePrice).reduce(0, Integer::sum);
        return ((double) apartmentsPrice + supplementaryPackagesPrice) / 100.00;
    }
}
