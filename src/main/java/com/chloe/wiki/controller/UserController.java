package com.chloe.wiki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.chloe.wiki.req.UserLoginReq;
import com.chloe.wiki.req.UserQueryReq;
import com.chloe.wiki.req.UserResetPasswordReq;
import com.chloe.wiki.req.UserSaveReq;
import com.chloe.wiki.resp.CommonResp;
import com.chloe.wiki.resp.PageResp;
import com.chloe.wiki.resp.UserLoginResp;
import com.chloe.wiki.resp.UserQueryResp;
import com.chloe.wiki.service.UserService;
import com.chloe.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/list")
    public CommonResp<PageResp<UserQueryResp>> list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<Void> save(@Valid @RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<Void> resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Void> delete(@PathVariable Long id) {
        CommonResp<Void> resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/reset-password")
    public CommonResp<Void> resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<Void> resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("Generated single sign-on token: {}, storing in redis", token);
        userLoginResp.setToken(token.toString());
        try {
            redisTemplate.opsForValue().set(
                    token.toString(),
                    objectMapper.writeValueAsString(userLoginResp),
                    3600 * 24,
                    TimeUnit.SECONDS
            );
        } catch (Exception e) {
            LOG.error("Error serializing user login response", e);
            throw new RuntimeException("Login process failed");
        }

        resp.setContent(userLoginResp);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp<Void> logout(@PathVariable String token) {
        CommonResp<Void> resp = new CommonResp<>();
        redisTemplate.delete(token);
        LOG.info("Deleted token from redis: {}", token);
        return resp;
    }
}
