package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.MovieRequest;
import br.com.movieflix.Controller.Response.MovieResponse;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Mapper.MovieMapper;
import br.com.movieflix.Service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/movieflix/movie")
@RestController
@Tag(name = "Movie", description = "resource responsible for managing films")
public class MovieController {
    private final MovieService movieService;
    @Operation(summary = "get all movies", description = "method responsible for get all movies",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "founded all",
    content = @Content(schema=@Schema(implementation = MovieResponse.class)))
    @GetMapping("/get")
    public ResponseEntity<List<MovieResponse>> getAll(){
        List<MovieResponse> moviesResponse = movieService.getAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(moviesResponse);
    }
    @Operation(summary = "save film", description = "method responsible for save a new film",
    security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "movie saved",
            content = @Content(schema=@Schema(implementation = MovieResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest movieRequest){
        Movie movie = movieService.saveMovie(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(movie));
    }
    @Operation(summary = "get a movie", description = "get one movie by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description="founded one",
    content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return movieService.getById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "update a movie", description = "update a movie by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description="updated",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
     return movieService.update(id,MovieMapper.toMovie(request))
             .map(movie->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
             .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "search by category", description = "search for a movie by category id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description="founded by category",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getByCategory(@RequestParam Long categoryId){
        return ResponseEntity.ok(movieService.findByCategory(categoryId)
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie)).toList());

    }
    @Operation(summary = "delete movie", description = "delete a movie by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description="deleted")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
      Optional<Movie> optMovie= movieService.getById(id);
      if(optMovie.isPresent()){
          movieService.delete(id);
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
    }
}
