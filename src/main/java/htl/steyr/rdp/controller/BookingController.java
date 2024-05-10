package htl.steyr.rdp.controller;

import htl.steyr.rdp.dto.request.BookingRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/booking")
public class BookingController {
    public ResponseEntity create(@RequestBody BookingRequestDto dto) {
        if (dto.apartments() == null || dto.packages() == null || dto.apartments().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete request body");
        }

        return ResponseEntity.ok().build();
    }
}
