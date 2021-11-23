package online.technotap.springdatajpaindepth.controller;

import online.technotap.springdatajpaindepth.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class DefaultController {

    @GetMapping("/")
    public ResultResponse<Object> greet() {
        return ResultResponse.builder()
                .status(HttpStatus.OK)
                .timestamp(ZonedDateTime.now())
                .data("Welcome to the club.")
                .build();
    }
}
