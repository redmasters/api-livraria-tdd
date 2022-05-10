package io.red.apilibrary.api.request;

public record BookRequest(
        String title,
        String author,
        String isbn
) {
}
