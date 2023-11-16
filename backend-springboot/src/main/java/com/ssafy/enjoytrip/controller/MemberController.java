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

import java.util.*;

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

    public Boolean idCheck(String userId) throws Exception{
        // userId와 일치하는 계정이 1개 이상이면, true, else false.
        return service.idCheck(userId) > 0 ? true : false;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDto memberDto) throws Exception {
        Map<String, String> map = new HashMap<>();

        if (idCheck(memberDto.getUserId())) {
            map.put("msg", "false");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        } else {
            service.signup(memberDto);
            map.put("msg", "true");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
    }

    @PostMapping("modify")
    public ResponseEntity<?> modify(@RequestBody MemberDto memberDto) throws Exception {

        Map<String, String> map = new HashMap<>();
        service.memberModify(memberDto);
        map.put("msg", "success");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String userId) throws Exception {

        Map<String, String> map = new HashMap<>();
        // id check
        if (service.idCheck(userId) > 0) {
            service.memberDelete(userId);
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}