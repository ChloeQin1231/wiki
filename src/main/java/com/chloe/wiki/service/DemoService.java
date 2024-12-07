package com.chloe.wiki.service;

import com.chloe.wiki.domain.Demo;
import com.chloe.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import jakarta.annotation.Resource;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}

