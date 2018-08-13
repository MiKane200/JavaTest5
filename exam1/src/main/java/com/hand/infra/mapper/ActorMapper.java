package com.hand.infra.mapper;

import java.util.List;

import com.hand.domain.entity.Actor;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ActorMapper extends Mapper<Actor> {
    //未完成 注解实现多表查询
    @Select("select * from actor where rid in(select rid from u_r where uid=1)")
    public List<Actor> getActorsByuid(int uid);
}