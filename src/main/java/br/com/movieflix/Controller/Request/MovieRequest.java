package br.com.movieflix.Controller.Request;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Streaming;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        @Schema(type="String" , description = "film name")
        @NotEmpty(message = "title is required") String title,
        @Schema(type="String" , description = "film description")
        String description,
        @Schema(type="date" , description = "film release date. Ex: 12/08/2010")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema(type="double" , description = "film rating Ex: 6.8")
        double rating,
        @Schema(type="array" , description = "code list of categories")
        List<Long> categories,
        @Schema(type="array" , description = "code list of streamings")
        List<Long> streamings) {
}
