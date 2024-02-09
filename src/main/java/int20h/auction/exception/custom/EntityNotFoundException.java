package int20h.auction.exception.custom;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class EntityNotFoundException extends AuctionException {
    public EntityNotFoundException(String message) {
        super(BAD_REQUEST, message);
    }
}