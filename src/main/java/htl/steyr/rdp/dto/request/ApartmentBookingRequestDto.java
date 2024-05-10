package htl.steyr.rdp.dto.request;

import java.time.LocalDate;

public record ApartmentBookingRequestDto(int apartment_id, LocalDate date_from, LocalDate date_to) {
}
