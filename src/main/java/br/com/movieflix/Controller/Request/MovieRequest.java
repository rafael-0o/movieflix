package br.com.movieflix.Controller.Request;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Streaming;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @NotEmpty(message = "title is required") String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        double rating,
        List<Long> categories,
        List<Long> streamings) {
}
