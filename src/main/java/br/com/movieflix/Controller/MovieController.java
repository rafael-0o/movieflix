package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.MovieRequest;
import br.com.movieflix.Controller.Response.MovieResponse;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Mapper.MovieMapper;
import br.com.movieflix.Service.MovieService;
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
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/get")
    public ResponseEntity<List<MovieResponse>> getAll(){
        List<MovieResponse> moviesResponse = movieService.getAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(moviesResponse);
    }
    @PostMapping("/create")
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest movieRequest){
        Movie movie = movieService.saveMovie(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(movie));
    }
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return movieService.getById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
     return movieService.update(id,MovieMapper.toMovie(request))
             .map(movie->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
             .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getByCategory(@RequestParam Long categoryId){
        return ResponseEntity.ok(movieService.findByCategory(categoryId)
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie)).toList());

    }
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
