package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.response.ApartmentResponseDto;
import htl.steyr.rdp.service.ApartmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    @NonNull
    private final ApartmentService apartmentService;

    @GetMapping("/available/{start}/{end}")
    public ResponseEntity<Set<ApartmentResponseDto>> available(@PathVariable String start, @PathVariable String end) {
        LocalDate startDate, endDate;
        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed date", e);
        }
        Set<ApartmentResponseDto> response = apartmentService.getAvailableBetween(startDate, endDate);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while retrieving apartments from the database");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}