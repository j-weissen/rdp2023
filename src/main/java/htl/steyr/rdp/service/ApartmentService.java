package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.response.ApartmentResponseDto;
import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.repository.ApartmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    @NonNull
    private final ApartmentRepository apartmentRepository;

    public Set<ApartmentResponseDto> getAvailableBetween(LocalDate start, LocalDate end) {
        return toDtos(apartmentRepository.findAvailableBetween(start, end));
    }

    public ApartmentResponseDto toDto(Apartment apartment) {
        return new ApartmentResponseDto(apartment.getId(), apartment.getName(), apartment.getMaxGuests(), apartment.getPricePerDay().floatValue() / 100, apartment.getCategory());
    }

    public Set<ApartmentResponseDto> toDtos(Set<Apartment> apartments) {
        return apartments.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
