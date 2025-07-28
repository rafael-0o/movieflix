CREATE TABLE movie(
    id serial PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    create_at timestamp,
    update_at timestamp
)