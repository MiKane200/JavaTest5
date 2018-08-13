package com.hand.infra.mapper;

import com.hand.domain.entity.Film;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface FilmMapper extends Mapper<Film> {
}