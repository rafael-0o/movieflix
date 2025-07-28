CREATE TABLE movie_streaming(
    movie_id INTEGER,
    streaming_id INTEGER,
    CONSTRAINT fk__movie__streaming__movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk__movie__streaming__streaming FOREIGN KEY(streaming_id) REFERENCES streaming(id)
)