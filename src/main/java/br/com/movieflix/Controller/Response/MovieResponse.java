package br.com.movieflix.Controller.Response;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Streaming;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings) {
}
