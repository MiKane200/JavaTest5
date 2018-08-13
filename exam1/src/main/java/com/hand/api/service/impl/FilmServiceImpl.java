package com.hand.api.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.hand.api.service.FilmService;
import com.hand.domain.entity.Film;
import com.hand.domain.entity.FilmActorCompose;
import com.hand.infra.mapper.FilmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

@Service
@Transactional(rollbackFor = Exception.class)
public class FilmServiceImpl implements FilmService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FilmMapper filmMapper;

    @Override
    public void saveSuccess(Film film) throws Exception {
        filmMapper.insert(film);
    }

    @Override
    public void saveFail(Film film) throws Exception {
        filmMapper.insert(film);
        throw new Exception("Transactional rollback test!");
    }

    @Override
    public List<Film> getFilmById(Film film) {
        FilmActorCompose filmActorCompose = filmMapper.getFilmActorsByAnno((short) 1);

        logger.info("ooooops");

        film.setDescription("TEST MAPPER");
        film.setTitle("TEST");
        film.setRentalDuration((byte) 1);
        film.setRentalRate(BigDecimal.valueOf(0.99));
        film.setRentalDuration((byte) 1);
        film.setReplacementCost(BigDecimal.valueOf(1));
        film.setLastUpdate(new Date());
        Example example = Example.builder(Film.class)
                .where(Sqls.custom().andEqualTo("filmId","1"))
                .build();
        int flag = filmMapper.updateByExample(film,example);

//        Example example2 = Example.builder(Film.class)
//                .where(Sqls.custom().andLike("title","A%"))
//                .build();
//        int count = filmMapper.deleteByExample(example2);

        List<FilmActorCompose> filmActorComposes =filmMapper.getFilmActors((short) 1);


        Example example4 = Example.builder(Film.class)
                .select("filmId","title")
                .where(Sqls.custom().andGreaterThan("filmId",5))
                .andWhere(Sqls.custom().andEqualTo("languageId",film.getLanguageId()))
                .orderByDesc("languageId")
                .build();
        PageHelper.offsetPage(1, 2);
        List<Film> resultFilm = filmMapper.selectByExample(example4);
        return resultFilm;
    }
}
