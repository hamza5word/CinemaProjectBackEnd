package com.hmz.cinemaprojectbackend;

import com.hmz.cinemaprojectbackend.business.InitCinemaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CinemaProjectBackEndApplicationTests {


    @Autowired
    private InitCinemaService initCinemaService;

    @Test
    void testInitData() {
        initCinemaService.initVilles();
        initCinemaService.initCinemas();
        initCinemaService.initSalles();
        initCinemaService.initPlaces();
        initCinemaService.initTickets();
    }

}
