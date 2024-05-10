package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.request.BookingRequestDto;
import htl.steyr.rdp.service.BookingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    @NonNull
    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody BookingRequestDto dto) {
        if (dto.apartments() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete request body: required apartment list does not exist");
        } else if (dto.packages() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete request body: required package list does not exist");
        } else if (dto.apartments().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete request body: no apartments specified");
        }
        bookingService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
