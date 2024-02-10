package int20h.auction.exception.custom;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationException extends AuctionException {
    public ValidationException(String message) {
        super(BAD_REQUEST, message);
    }
}