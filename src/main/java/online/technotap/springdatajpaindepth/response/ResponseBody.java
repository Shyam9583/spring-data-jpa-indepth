package online.technotap.springdatajpaindepth.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ResponseBody<T> {
    private ZonedDateTime timestamp;
    private T data;
}
