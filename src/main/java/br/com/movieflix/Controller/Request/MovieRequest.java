package br.com.movieflix.Controller.Request;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Streaming;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        String title,
        String description,
        LocalDate releaseDate,
        double rating,
        List<Long> categories,
        List<Long> streamings) {
}
