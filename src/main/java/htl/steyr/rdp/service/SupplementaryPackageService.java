package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.request.SupplementaryPackageBookingRequestDto;
import htl.steyr.rdp.model.SupplementaryPackageBooking;
import htl.steyr.rdp.repository.SupplementaryPackageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplementaryPackageService {
    @NonNull
    private final SupplementaryPackageRepository supplementaryPackageRepository;

    public SupplementaryPackageBooking fromDto(SupplementaryPackageBookingRequestDto dto) {
        var supplementaryPackage = supplementaryPackageRepository.findById(dto.package_id());
        if (supplementaryPackage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Supplementary package with id %s not found", dto.package_id()));
        }
        return new SupplementaryPackageBooking(dto.date_from(), dto.date_to(), dto.amount(), supplementaryPackage.get());
    }

    public Set<SupplementaryPackageBooking> fromDtos(List<SupplementaryPackageBookingRequestDto> dtos) {
        return dtos.stream().map(this::fromDto).collect(Collectors.toSet());
    }
}
