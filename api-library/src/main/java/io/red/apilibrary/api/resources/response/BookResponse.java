package io.red.apilibrary.api.resources.response;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn
) {
}
