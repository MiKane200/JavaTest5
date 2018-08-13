package com.hand.infra.mapper;

import java.util.List;

import com.hand.domain.entity.Film;
import com.hand.domain.entity.FilmActorCompose;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

public interface FilmMapper extends Mapper<Film> {
    public List<FilmActorCompose> getFilmActors(Short filmId);

    //未完成 注解实现多表查询
    @Select("select * from film f where " +
            "f.film_id = #{filmId};")
    @Results({
            @Result(id = true , column = "film_id", property = "filmId"),
            @Result(column = "title",property = "title"),
//            @Result(column = "filmId",property = "actors",
//                    many=@Many(
//                            select = "com.hand.infra.mapper.ActorMapper",
//                    fetchType = FetchType.LAZY
//            ))
    })
    public FilmActorCompose getFilmActorsByAnno(Short filmId);
}