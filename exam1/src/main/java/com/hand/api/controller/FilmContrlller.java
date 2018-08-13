package com.hand.api.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hand.api.service.FilmService;
import com.hand.domain.entity.Film;
import com.hand.domain.entity.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film")
public class FilmContrlller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/languageId",produces = {"application/json;charset=UTF-8"})
    public List<Film> getFilmByLanguageId(@RequestBody Language language, @RequestHeader(value = "User-Agent") String userAgent) {
        Byte languageId = language.getLanguageId();

        Film film = new Film();
        film.setLanguageId(Byte.valueOf(languageId));
        film.setDescription("testData");
        film.setTitle("test");
        film.setRentalDuration((byte) 1);
        film.setRentalRate(BigDecimal.valueOf(0.99));
        film.setRentalDuration((byte) 1);
        film.setReplacementCost(BigDecimal.valueOf(1));
        film.setLastUpdate(new Date());

        Film film1 = new Film();
        film1.setLanguageId(languageId);
        film1.setFilmId((short) 5);

        return filmService.getFilmById(film1);
    }

    @RequestMapping(value = "/redirect/{languageId}",method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectTogetFilmByLanguageId(@PathVariable String languageId,
                                                @RequestHeader("Host") String hostAddr) {
//        httpServlet.getInitParameter("");
        logger.info("languageId:"+languageId);
        logger.info("hostAddr is:" + hostAddr + "and ready to redirect to film/languageId");
        return "redirect:new.jsp";
    }

    @GetMapping(value = "/save/{languageId}"
    )
    public List<Film> getFilmByLanguageIdRestful(@PathVariable String languageId,
                                                 @RequestHeader(value = "User-Agent") String userAgent,
                                                 @ModelAttribute("msg") String msg,
                                                 HttpServletRequest request) throws Exception {

        request.setAttribute("User-Agent",userAgent);
        Film film = new Film();
        film.setLanguageId(Byte.valueOf(languageId));
        film.setDescription("testData");
        film.setTitle("test");
        film.setRentalDuration((byte) 1);
        film.setRentalRate(BigDecimal.valueOf(0.99));
        film.setRentalDuration((byte) 1);
        film.setReplacementCost(BigDecimal.valueOf(1));
        film.setLastUpdate(new Date());

//        try {
//            filmService.saveFail(film);
//        } catch (Exception e) {
//            throw new Exception("sorry! this error come from "+"@ModelAttribute:"+msg);
//        }

        Film film1 = new Film();
        film1.setLanguageId(Byte.valueOf(languageId));
        film1.setFilmId((short) 5);

        return filmService.getFilmById(film1);
    }
}
