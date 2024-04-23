package htl.steyr.rdp.controller;

import htl.steyr.rdp.model.Apartment;
import htl.steyr.rdp.service.AparmentService;
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
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    @NonNull
    private final AparmentService apartmentService;

    @GetMapping("/available/{start}/{end}")
    public String /*ResponseEntity<Set<Apartment>>*/ available(@PathVariable String start, @PathVariable String end) {
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed date", e);
        }

        return "happy happy happy";
    }
}
