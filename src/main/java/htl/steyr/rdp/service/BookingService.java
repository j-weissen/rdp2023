package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.request.BookingRequestDto;
import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.ApartmentBooking;
import htl.steyr.rdp.model.Booking;
import htl.steyr.rdp.model.SupplementaryPackageBooking;
import htl.steyr.rdp.repository.*;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {
    @NonNull
    private final BookingRepository bookingRepository;
    @NonNull
    private final CustomerRepository customerRepository;
    @NonNull
    private final ApartmentService apartmentService;
    @NonNull
    private final SupplementaryPackageService supplementaryPackageService;
    @NonNull
    private final SupplementaryPackageBookingRepository supplementaryPackageBookingRepository;
    @NonNull
    private final ApartmentBookingRepository apartmentBookingRepository;
    @NonNull
    private final ApartmentRepository apartmentRepository;

    public void create(BookingRequestDto dto) {
        Booking booking = fromDto(dto);
        var apartmentBookings = apartmentService.fromDtos(dto.apartments());
        var supplementaryPackageBookings = supplementaryPackageService.fromDtos(dto.packages());
        for (ApartmentBooking ab : apartmentBookings) {
            System.out.println(apartmentRepository.apartmentIsFreeBetween(ab.getApartment(), ab.getStart(), ab.getEnd()));
            if (!apartmentRepository.apartmentIsFreeBetween(ab.getApartment(), ab.getStart(), ab.getEnd()).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Apartment with id %s is not free in the specified time period", ab.getApartment().getId()));
            }
            List<ApartmentBooking> apartmentBookingsWithSameApartment = apartmentBookings.stream().filter(ab2 -> Objects.equals(ab.getApartment().getId(), ab2.getApartment().getId())).toList();
            System.out.println(apartmentBookingsWithSameApartment);
            if (apartmentBookingsWithSameApartment.size() > 1) { // multiple bookings for the same apartment, check for date conflict necessary
                for (ApartmentBooking i : apartmentBookingsWithSameApartment) {
                    for (ApartmentBooking j : apartmentBookingsWithSameApartment) {
                        if (i.equals(j)) {
                            continue;
                        }
                        if (i.getStart().isAfter(j.getEnd()) || i.getEnd().isBefore(j.getStart())) {
                            continue;
                        }
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Multiple specified apartment bookings for apartment with id %s conflict in date range", i.getApartment().getId()));
                    }
                }
            }
        }
        Booking persisted = bookingRepository.save(booking);
        persisted.setApartmentBookings(null);
        persisted.setSupplementaryPackageBookings(null);
        apartmentBookingRepository.saveAll(apartmentBookings.stream().peek(ab -> ab.setBooking(persisted)).toList());
        supplementaryPackageBookingRepository.saveAll(supplementaryPackageBookings.stream().peek(ab -> ab.setBooking(persisted)).toList());
    }

    public Booking fromDto(BookingRequestDto dto) {
        var customer = customerRepository.findById(dto.customer_id());
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %s not found", dto.customer_id()));
        }
        return Booking.builder()
            .apartmentBookings(apartmentService.fromDtos(dto.apartments()))
            .supplementaryPackageBookings(supplementaryPackageService.fromDtos(dto.packages()))
            .customer(customer.get()).build();
    }
}
