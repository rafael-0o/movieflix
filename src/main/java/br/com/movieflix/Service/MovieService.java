package br.com.movieflix.Service;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Entity.Movie;
import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }
    @Transactional
    public Movie saveMovie(Movie movie){
        // sobrescreve a lista que só tem os ids por uma lista com id e nome
        movie.setCategories(this.findCategory(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }
    public Optional<Movie> getById(Long id){
        return movieRepository.findById(id);
    }
    /** metodo usado para que quando voce enviar as informações do movie aparecer o categories/streamings com id e name,
        pois se não, quando você enviar o post vai aparecer nos nomes "null"**/
    private List<Category> findCategory(List<Category> categoryList){
        List<Category> categorySaved = new ArrayList<>();
        categoryList.forEach(category -> categoryService
                .findById(category.getId())
                .ifPresent(c-> categorySaved.add(c)));
        return categorySaved;
    }
    private List<Streaming> findStreamings(List<Streaming> streamingList){
        List<Streaming> streamingSaved=new ArrayList<>();
        streamingList.forEach(streaming -> streamingService
                .findById(streaming.getId()).ifPresent(streamingSaved::add));
        return streamingSaved;
    }
    @Transactional
    public Optional<Movie> update(Long movieId, Movie movieUp){
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()){
            List<Category>categories=findCategory(movieUp.getCategories());
            List<Streaming>streamings=findStreamings(movieUp.getStreamings());
            Movie movie = optMovie.get();
            movie.setTitle(movieUp.getTitle());
            movie.setDescription(movieUp.getDescription());
            movie.setReleaseDate(movieUp.getReleaseDate());
            movie.setRating(movieUp.getRating());
            movie.getCategories().clear();
            movie.getCategories().addAll(categories);
            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);
            return Optional.of(movie);
        }
        return Optional.empty();
    }
    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }
    public void delete(Long id){
        movieRepository.deleteById(id);
    }
}
