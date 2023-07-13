package ru.wevioz.trademarkapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.wevioz.trademarkapi.dto.ErrorDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> messageKeys = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    return Arrays.stream(Objects.requireNonNull(fieldError.getCodes()))
                            .findFirst()
                            .orElse(null);
                }).toList();
        ErrorDto errorDto = new ErrorDto(messageKeys, exception);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleItemNotFoundException(
            NotFoundException notFoundException,
            WebRequest webRequest
    ) {
        List<String> messageKeys = new ArrayList<>();
        messageKeys.add(notFoundException.getMessage());
        ErrorDto errorDto = new ErrorDto(messageKeys, notFoundException);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
