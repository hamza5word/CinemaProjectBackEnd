package com.hmz.cinemaprojectbackend;

import com.hmz.cinemaprojectbackend.business.InitCinemaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CinemaProjectBackEndApplicationTests {


    @Autowired
    private InitCinemaService initCinemaService;

    @Test
    void testInitData() {
        // order is important
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initVilles());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initCinemas());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initSalles());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initPlaces());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initTickets());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initCategories());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initFilms());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initSeances());
        Assertions.assertThrows(Exception.class, () -> initCinemaService.initProjections());
    }

}
