package ca.sait.web7.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundDetail {

    private String title;
    private String detail;
    private String developerMessage;
    private int status;
    private long timeStamp;
}
