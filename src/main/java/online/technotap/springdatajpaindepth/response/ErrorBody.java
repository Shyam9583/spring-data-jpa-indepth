package online.technotap.springdatajpaindepth.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class ErrorBody {
    private ZonedDateTime timestamp;
    private List<String> errors;
}
