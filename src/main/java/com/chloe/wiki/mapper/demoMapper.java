package com.chloe.wiki.mapper;

import com.chloe.wiki.domain.demo;
import com.chloe.wiki.domain.demoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface demoMapper {
    long countByExample(demoExample example);

    int deleteByExample(demoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(demo row);

    int insertSelective(demo row);

    List<demo> selectByExample(demoExample example);

    demo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") demo row, @Param("example") demoExample example);

    int updateByExample(@Param("row") demo row, @Param("example") demoExample example);

    int updateByPrimaryKeySelective(demo row);

    int updateByPrimaryKey(demo row);
}