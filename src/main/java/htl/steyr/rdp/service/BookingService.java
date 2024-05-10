package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.request.BookingRequestDto;
import htl.steyr.rdp.model.Booking;
import htl.steyr.rdp.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void create(BookingRequestDto dto) {
        Booking booking = fromDto(dto);
        apartmentBookingRepository.saveAll(booking.getApartmentBookings());
        supplementaryPackageBookingRepository.saveAll(booking.getSupplementaryPackageBookings());
        bookingRepository.save(booking);
    }

    public Booking fromDto(BookingRequestDto dto) {
        var customer = customerRepository.findById(dto.customer_id());
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %s not found", dto.customer_id()));
        }
        return new Booking(apartmentService.fromDtos(dto.apartments()), supplementaryPackageService.fromDtos(dto.packages()), customer.get());
    }
}
