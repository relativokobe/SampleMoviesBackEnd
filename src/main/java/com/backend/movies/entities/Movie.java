package com.backend.movies.entities;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for movies table
 * @author Kobe Kyle Relativo
 */
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "movies")
public class Movie {
    @Expose
    @Id
    private String id;
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "description")
    private String description;
    @Expose
    @Column(name = "director")
    private String director;

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<User> users;
}
