package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.request.ApartmentBookingRequestDto;
import htl.steyr.rdp.dto.request.SupplementaryPackageBookingRequestDto;
import htl.steyr.rdp.dto.response.ApartmentResponseDto;
import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.model.ApartmentBooking;
import htl.steyr.rdp.model.SupplementaryPackageBooking;
import htl.steyr.rdp.repository.ApartmentBookingRepository;
import htl.steyr.rdp.repository.ApartmentRepository;
import htl.steyr.rdp.repository.SupplementaryPackageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    @NonNull
    private final ApartmentRepository apartmentRepository;

    public List<ApartmentResponseDto> getAvailableBetween(LocalDate start, LocalDate end) {
        return toDtos(apartmentRepository.findAvailableBetween(start, end));
    }

    public ApartmentResponseDto toDto(Apartment apartment) {
        return new ApartmentResponseDto(apartment.getId(), apartment.getName(), apartment.getMaxGuests(), apartment.getPricePerDay().floatValue() / 100, apartment.getCategory());
    }

    public List<ApartmentResponseDto> toDtos(List<Apartment> apartments) {
        return apartments.stream().map(this::toDto).toList();
    }

    public ApartmentBooking fromDto(ApartmentBookingRequestDto dto) {
        var apartment = apartmentRepository.findById(dto.apartment_id());
        if (apartment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Apartment with id %s not found", dto.apartment_id()));
        }
        return new ApartmentBooking(dto.date_from(), dto.date_to(), apartment.get());
    }

    public Set<ApartmentBooking> fromDtos(List<ApartmentBookingRequestDto> dtos) {
        return dtos.stream().map(this::fromDto).collect(Collectors.toSet());
    }
}
