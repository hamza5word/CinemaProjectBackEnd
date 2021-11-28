package com.hmz.cinemaprojectbackend.business.impl;

import com.hmz.cinemaprojectbackend.business.InitCinemaService;
import com.hmz.cinemaprojectbackend.dao.*;
import com.hmz.cinemaprojectbackend.entities.*;
import com.hmz.operations.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InitCinemaServiceImpl implements InitCinemaService {

    private final VilleRepository villeRepository;
    private final CinemaRepository cinemaRepository;
    private final SalleRepository salleRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;

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
            for(int i = 0; i<new Random(3, 6).getIntRandom(); i++) {
                Ticket t = new Ticket();
                t.setPrix(new Random(100, 300).getRandom());
                t.setCodePaiment(new Random(10000, 99999).getIntRandom());
                t.setNomClient("Client " + (i+1));
                t.setReservee(false);
                t.setPlace(p);
                ticketRepository.save(t);
            }
        });
    }

    @Override
    public void initProjections() {

    }

    @Override
    public void initFilms() {

    }

    @Override
    public void initSeances() {

    }

}
