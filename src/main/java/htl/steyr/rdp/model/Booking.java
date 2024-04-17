package htl.steyr.rdp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price_per_day", nullable = false)
    @NonNull
    private Integer pricePerDay;

    @OneToMany(mappedBy = "booking", orphanRemoval = true)
    @NonNull
    private Set<ApartmentBooking> apartmentBookings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "booking", orphanRemoval = true)
    @NonNull
    private Set<SupplementaryPackageBooking> supplementaryPackageBookings = new LinkedHashSet<>();

    public double calculateTotalPrice() {
        int apartmentsPrice = apartmentBookings.stream().map(ApartmentBooking::calculatePrice).reduce(0, Integer::sum);
        int supplementaryPackagesPrice = supplementaryPackageBookings.stream().map(SupplementaryPackageBooking::calculatePrice).reduce(0, Integer::sum);
        return ((double) apartmentsPrice + supplementaryPackagesPrice) / 100.00;
    }
}
