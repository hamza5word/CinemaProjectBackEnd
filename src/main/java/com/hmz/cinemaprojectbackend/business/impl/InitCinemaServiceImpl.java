package com.hmz.cinemaprojectbackend.business.impl;

import com.hmz.cinemaprojectbackend.business.InitCinemaService;
import com.hmz.cinemaprojectbackend.dao.*;
import com.hmz.cinemaprojectbackend.entities.*;
import com.hmz.operations.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InitCinemaServiceImpl implements InitCinemaService {

    private final VilleRepository villeRepository;
    private final CinemaRepository cinemaRepository;
    private final SalleRepository salleRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;
    private final ProjectionRepository projectionRepository;
    private final CategorieRepository categorieRepository;
    private final FilmRepository filmRepository;
    private  final SeanceRepository seanceRepository;

    @Override
    public void initVilles() {
        Stream.of("Fès", "Casablanca", "Marrakech").forEach(ville -> {
            Ville v = new Ville();
            v.setName(ville);
            villeRepository.save(v);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v -> Stream.of("Megarama", "Chahrazad", "Founoun").forEach(cinema -> {
            Cinema c = new Cinema();
            c.setName(cinema);
            c.setNombreSalles(new Random(3, 10).getIntRandom());
            c.setVille(v);
            cinemaRepository.save(c);
        }));
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(c -> {
            for(int i = 0; i<c.getNombreSalles(); i++) {
                Salle s = new Salle();
                s.setNom("Salle " + (i+1));
                s.setCinema(c);
                s.setNombrePlaces(new Random(20, 30).getIntRandom());
                salleRepository.save(s);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(s -> {
            for(int i = 0; i<s.getNombrePlaces(); i++) {
                Place p = new Place();
                p.setNumero(i+1);
                p.setSalle(s);
                placeRepository.save(p);
            }
        });
    }

    @Override
    public void initTickets() {
        placeRepository.findAll().forEach(p -> {
            Ticket t = new Ticket();
            t.setPrix(new Random(100, 300).getRandom());
            t.setCodePaiment(new Random(10000, 99999).getIntRandom());
            t.setReservee(false);
            t.setPlace(p);
            ticketRepository.save(t);
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action", "Aventure", "Drama", "Comedie").forEach(categorie -> {
            Categorie c = new Categorie();
            c.setName(categorie);
            categorieRepository.save(c);
        });
    }

    @Override
    public void initFilms() {
        categorieRepository.findAll().forEach(c -> {
            Film f = new Film();
            f.setTitre("Film " + c.getName());
            f.setCategorie(c);
            filmRepository.save(f);
        });
    }


    @Override
    public void initSeances() {
        for(int i = 0; i<6; i++) {
            Seance s = new Seance();
            int year = new Random(1998, 2020).getIntRandom();
            int month = new Random(1, 12).getIntRandom();
            int day = new Random(1, 28).getIntRandom();
            int hour = new Random(0, 24).getIntRandom();
            int minute = new Random(0, 60).getIntRandom();
            s.setHeureDebut(Date.from(LocalDateTime.of(year, month, day, hour, minute).atZone(ZoneId.systemDefault()).toInstant()));
            seanceRepository.save(s);
        }
    }

    @Override
    public void initProjections() {
        List<Film> films = filmRepository.findAll();
        List<Salle> salles = salleRepository.findAll();
        List<Seance> seances = seanceRepository.findAll();
        for(int i = 0; i<3; i++) {
            Projection p = new Projection();
            p.setPrix(new Random(100, 500).getRandom());
            p.setFilm(films.get(new Random(0, films.size()).getIntRandom()));
            p.setSalle(salles.get(new Random(0, salles.size()).getIntRandom()));
            p.setSeance(seances.get(new Random(0, seances.size()).getIntRandom()));
            projectionRepository.save(p);
        }
    }

}
