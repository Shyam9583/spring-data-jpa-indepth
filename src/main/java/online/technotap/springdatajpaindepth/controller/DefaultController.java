package online.technotap.springdatajpaindepth.controller;

import online.technotap.springdatajpaindepth.response.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Random;

@RestController
public class DefaultController {

    @GetMapping("/")
    public ResponseEntity<ResponseBody<Object>> greet() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ResponseBody.builder()
                        .timestamp(ZonedDateTime.now())
                        .data("Today's lucky number is " + new Random().nextInt(100) + "")
                        .build()
                );
    }
}
