package htl.steyr.rdp.service;

import htl.steyr.rdp.repository.ApartmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AparmentService {
    @NonNull
    private final ApartmentRepository apartmentRepository;
}
