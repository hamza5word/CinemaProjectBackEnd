package com.hmz.cinemaprojectbackend.business.impl;

import com.hmz.cinemaprojectbackend.business.InitCinemaService;
import com.hmz.cinemaprojectbackend.dao.*;
import com.hmz.cinemaprojectbackend.entities.*;
import com.hmz.operations.Random;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InitCinemaServiceImpl implements InitCinemaService {

    Logger logger = LoggerFactory.getLogger(InitCinemaServiceImpl.class);

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
        logger.info(">> Villes initialization completed");
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
        logger.info(">> Cinemas initialization completed");
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
        logger.info(">> Salles initialization completed");
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
        logger.info(">> Places initialization completed");
    }

    @Override
    public void initCategories() {
        Stream.of("Action", "Aventure", "Drama", "Comedie").forEach(categorie -> {
            Categorie c = new Categorie();
            c.setName(categorie);
            categorieRepository.save(c);
        });
        logger.info(">> Categories initialization completed");
    }

    @Override
    public void initFilms() {
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("Avengers", "Power Up", "Speed", "Shang chi", "Dragon", "Lord of the Rings").forEach(film -> {
            Film f = new Film();
            f.setTitre(film);
            f.setDuree(new Random(1, 3).getIntRandom());
            f.setPhoto(film + ".jpg");
            f.setCategorie(categories.get(new Random(0, categories.size()).getIntRandom()));
            filmRepository.save(f);
        });
        logger.info(">> Films initialization completed");
    }


    @Override
    public void initSeances() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        Stream.of("18:00", "15:30", "11:40", "10:15").forEach(seance -> {
            Seance s = new Seance();
            try {
                s.setHeureDebut(format.parse(seance));
                seanceRepository.save(s);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        });
        logger.info(">> Seances initialization completed");
    }

    @Override
    @Transactional
    public void initProjections() {
        villeRepository.findAll().forEach(v -> {
            v.getCinemas().forEach(c -> {
                c.getSalles().forEach(s -> {
                    filmRepository.findAll().forEach(f -> {
                        seanceRepository.findAll().forEach(se -> {
                            Projection p = new Projection();
                            p.setFilm(f);
                            p.setDate(new Date());
                            p.setSeance(se);
                            p.setSalle(s);
                            p.setPrix(new Random(100, 500).getRandom());
                            projectionRepository.save(p);
                        });
                    });
                });
            });
        });
        logger.info(">> Projections initialization completed");
    }

    @Override
    public void initTickets() {
        placeRepository.findAll().forEach(p -> {
            for(int i = 0; i<new Random(1, 3).getIntRandom(); i++) {
                Ticket t = new Ticket();
                t.setNomClient("Client " + (i + 1));
                t.setPrix(new Random(100, 300).getRandom());
                t.setReservee(false);
                t.setPlace(p);
                ticketRepository.save(t);
            }
        });
        logger.info(">> Tickets initialization completed");
    }

}
