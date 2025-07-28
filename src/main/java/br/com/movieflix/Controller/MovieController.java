package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.MovieRequest;
import br.com.movieflix.Controller.Response.MovieResponse;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Mapper.MovieMapper;
import br.com.movieflix.Service.MovieService;
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
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest movieRequest){
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
}
