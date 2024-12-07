package com.chloe.wiki.service;

import com.chloe.wiki.domain.Ebook;
import com.chloe.wiki.domain.EbookExample;
import com.chloe.wiki.mapper.EbookMapper;
import com.chloe.wiki.req.EbookReq;
import com.chloe.wiki.resp.EbookResp;
import com.chloe.wiki.util.CopyUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Resource;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        // copy list
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        return list;
    }

}

