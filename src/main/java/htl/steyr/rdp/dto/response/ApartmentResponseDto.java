package htl.steyr.rdp.dto.response;

import htl.steyr.rdp.model.Category;

public record ApartmentResponseDto(Integer id, String name, Integer maxGuests, Float price, Category categoryId) {

}
