package online.technotap.springdatajpaindepth.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private ZonedDateTime timestamp;
    private HttpStatus status;
    private List<String> errors;
}
