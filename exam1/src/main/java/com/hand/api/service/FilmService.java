package com.hand.api.service;

import java.util.List;

import com.hand.domain.entity.Film;
import org.springframework.stereotype.Service;

public interface FilmService{
    public void saveSuccess(Film film) throws Exception;
    public void saveFail(Film film) throws Exception;
    public List<Film> getFilmById(Film film);
}
