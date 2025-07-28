package br.com.movieflix.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private double rating;
    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns=@JoinColumn(name = "movie_id"),
            inverseJoinColumns=@JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "movie_streaming",
            joinColumns=@JoinColumn(name = "movie_id"),
            inverseJoinColumns=@JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streamings;
}