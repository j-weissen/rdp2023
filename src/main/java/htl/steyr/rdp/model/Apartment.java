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
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "max_guests", nullable = false)
    @NonNull
    private Integer maxGuests;

    @Column(name = "price_per_day", nullable = false)
    @NonNull
    private Integer pricePerDay;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NonNull
    private Category category;

    @OneToMany(mappedBy = "apartment", orphanRemoval = true)
    private Set<ApartmentBooking> apartmentBookings = new LinkedHashSet<>();

}
