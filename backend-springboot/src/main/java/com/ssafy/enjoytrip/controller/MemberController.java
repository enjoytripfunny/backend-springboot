package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        super();
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto) {

        try {
            MemberDto returnDto = service.login(memberDto);
            return new ResponseEntity<>(returnDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(memberDto, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody String userId) throws Exception {
        service.memberDelete(userId);
        Map<String, String> map = new HashMap<>();
        // id check
        if (true) {
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}