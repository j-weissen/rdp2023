package htl.steyr.rdp.dto.request;

import java.util.List;

public record BookingRequestDto(int customer_id, List<ApartmentBookingRequestDto> apartments, List<SupplementaryPackageBookingRequestDto> packages) {
}
