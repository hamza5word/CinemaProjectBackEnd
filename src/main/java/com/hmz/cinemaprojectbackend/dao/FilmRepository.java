package com.hmz.cinemaprojectbackend.dao;

import com.hmz.cinemaprojectbackend.entities.Film;
import com.hmz.cinemaprojectbackend.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long> {
}
