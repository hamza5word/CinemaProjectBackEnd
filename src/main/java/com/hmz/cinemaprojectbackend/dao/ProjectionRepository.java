package com.hmz.cinemaprojectbackend.dao;

import com.hmz.cinemaprojectbackend.entities.Cinema;
import com.hmz.cinemaprojectbackend.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
