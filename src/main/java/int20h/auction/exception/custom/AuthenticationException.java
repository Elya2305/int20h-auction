package int20h.auction.exception.custom;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class AuthenticationException extends AuctionException {
    public AuthenticationException(String message) {
        super(FORBIDDEN, message);
    }
}