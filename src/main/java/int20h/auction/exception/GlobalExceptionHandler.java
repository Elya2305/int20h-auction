package int20h.auction.exception;

import int20h.auction.exception.custom.AuctionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalException(Exception e) {
        log.error("An exception was caught!", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message("Internal exception occurred")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build());
    }

    @ExceptionHandler(AuctionException.class)
    public ResponseEntity<ErrorResponse> handleAuctionApiException(AuctionException e) {
        log.error("An exception was caught!", e);

        return ResponseEntity
                .status(e.getStatusCode())
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(e.getStatusCode().value())
                        .build());
    }
}