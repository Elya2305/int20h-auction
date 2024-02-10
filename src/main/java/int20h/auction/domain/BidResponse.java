package int20h.auction.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class BidResponse {
    private String id;
    private String lotId;
    private Long price;
    private String username;
    private boolean isBest;
    private Instant createdAt;
}
