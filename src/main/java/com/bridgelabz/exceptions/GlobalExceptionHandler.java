package com.bridgelabz.exceptions;

import com.bridgelabz.dto.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Purpose: Method to handle the exception for validation
     *
     * @param exception: Throws MethodArgumentNotValidException if validation fails
     * @param headers:   to pass additional information between the clients and the server through the request and
     *                   response header
     * @param status:    in response to a client's request made to the server
     * @param request:   message that is transmitted between the client, or web browsers, to the servers
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("Status", status.value());

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * Purpose: exception method to handle when data not found in database
     *
     * @param notFoundException : thrown when data not found
     * @param request:          message that is transmitted between the client, or web browsers, to the servers
     * @return response entity contains the message
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundException(DataNotFoundException notFoundException, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(notFoundException.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
