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
        Assertions.assertDoesNotThrow(() -> initCinemaService.initVilles());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initCinemas());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initSalles());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initPlaces());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initCategories());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initFilms());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initSeances());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initProjections());
        Assertions.assertDoesNotThrow(() -> initCinemaService.initTickets());
    }

}
