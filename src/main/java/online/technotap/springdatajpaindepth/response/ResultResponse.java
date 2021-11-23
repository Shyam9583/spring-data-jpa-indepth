package online.technotap.springdatajpaindepth.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
@Data
public class ResultResponse<T> {
    private ZonedDateTime timestamp;
    private HttpStatus status;
    private T data;
}
