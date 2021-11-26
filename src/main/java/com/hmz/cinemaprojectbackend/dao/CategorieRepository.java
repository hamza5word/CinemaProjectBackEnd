package com.hmz.cinemaprojectbackend.dao;

import com.hmz.cinemaprojectbackend.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
