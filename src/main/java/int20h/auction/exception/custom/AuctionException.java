package int20h.auction.exception.custom;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AuctionException extends ResponseStatusException {
    private final String message;

    public AuctionException(HttpStatusCode status, String message) {
        super(status);
        this.message = message;
    }
}