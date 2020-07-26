package ca.sait.web7.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class ValidationErrorsDetailList {

    private String title;
    private String detail;
    private String developerMessage;
    private int status;
    private long timeStamp;
    private List<fieldErrorValidate> listErrors = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public class fieldErrorValidate {

        private String fieldName;
        private String descriptionError;
    }

    public void addError(String fieldNameError, String descriptionError)
    {
        listErrors.add(new fieldErrorValidate(fieldNameError, descriptionError));
    }

}
