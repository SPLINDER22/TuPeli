package com.urnovie.tupeli.repository;

import com.urnovie.tupeli.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByUserIdUser(Long idUser);

    Optional<Movie> findByTitleAndUserIdUser(String title, Long userId);

}
