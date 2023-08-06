package hu.progmasters.finalexam.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleClubNotFound(ClubNotFoundException exception) {
        ValidationError validationError = new ValidationError("clubId",
                "no club found with id: " + exception.getClubId());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handlePlayerTypeIsNotCorrect(PlayerNotFoundException exception) {
        ValidationError validationError = new ValidationError("playerType",
                "must not be null ");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoachNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handlePlayerTypeIsNotCorrect(CoachNotFoundException exception) {
        ValidationError validationError = new ValidationError("coachId",
                "no coach found with id: ");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
