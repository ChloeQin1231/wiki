package com.chloe.wiki.controller;

import com.chloe.wiki.domain.Ebook;
import com.chloe.wiki.req.EbookReq;
import com.chloe.wiki.resp.CommonResp;
import com.chloe.wiki.resp.EbookResp;
import com.chloe.wiki.resp.PageResp;
import com.chloe.wiki.service.EbookService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import jakarta.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
