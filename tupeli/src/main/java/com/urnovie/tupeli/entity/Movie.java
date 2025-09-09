package com.urnovie.tupeli.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovie", nullable = false, unique = true)
    private Long idMovie;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "estreno", nullable = false, length = 4)
    private Long estreno;

    @Column(name = "director", nullable = false, length = 100)
    private String director;

    @Column(name = "genre", nullable = false, length = 50)
    private String genre;

    @Column(name = "posterUrl", nullable = false, length = 500)
    private String posterUrl;
}
