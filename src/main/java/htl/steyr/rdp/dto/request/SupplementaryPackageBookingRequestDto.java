package htl.steyr.rdp.dto.request;

import java.time.LocalDate;

public record SupplementaryPackageBookingRequestDto(int package_id, int amount, LocalDate date_from, LocalDate date_to) {
}
