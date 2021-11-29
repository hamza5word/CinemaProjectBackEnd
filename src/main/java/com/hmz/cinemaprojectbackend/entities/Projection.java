package com.hmz.cinemaprojectbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double prix;

    @ManyToOne
    private Film film;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Seance seance;
    @OneToMany(mappedBy = "projection")
    private Collection<Ticket> tickets;

}
