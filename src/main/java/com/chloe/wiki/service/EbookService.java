package com.chloe.wiki.service;

import com.chloe.wiki.domain.Ebook;
import com.chloe.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.annotation.Resource;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}

