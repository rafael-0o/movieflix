package br.com.movieflix.Mapper;

import br.com.movieflix.Controller.Request.MovieRequest;
import br.com.movieflix.Controller.Response.CategoryResponse;
import br.com.movieflix.Controller.Response.MovieResponse;
import br.com.movieflix.Controller.Response.StreamingResponse;
import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
/**o map tranforma um valor contido em uma operação (Optional,stream) em um outro valor e aplicando uma função a ele,
 Ele é muito útil para encadear operações de forma limpa e evitar verificações de null. **/
    public static Movie toMovie(MovieRequest movieRequest){
        List<Category> categories=movieRequest.categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();
        List<Streaming> streamings=movieRequest.streamings()
                .stream()
                .map(streamingId->Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
    public static MovieResponse toMovieResponse(Movie movie){
        List<CategoryResponse> categories=movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();
        List<StreamingResponse> streamings=movie.getStreamings()
                .stream()
                .map(streaming->StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
