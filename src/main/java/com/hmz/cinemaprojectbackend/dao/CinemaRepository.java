package com.hmz.cinemaprojectbackend.dao;

import com.hmz.cinemaprojectbackend.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
