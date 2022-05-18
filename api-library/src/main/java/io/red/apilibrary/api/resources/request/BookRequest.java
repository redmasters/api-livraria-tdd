package io.red.apilibrary.api.resources.request;

public record BookRequest(
        String title,
        String author,
        String isbn
) {
}
