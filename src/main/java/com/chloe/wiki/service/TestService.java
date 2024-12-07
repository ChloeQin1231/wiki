package com.chloe.wiki.service;

import com.chloe.wiki.domain.Test;
import com.chloe.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
