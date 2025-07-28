package br.com.movieflix.Service;

import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }
    @Transactional
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Optional<Movie> getById(Long id){
        return movieRepository.findById(id);
    }

}
