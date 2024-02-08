package int20h.auction.domain;

import int20h.auction.entitiy.Lot;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class LotResponse {
    private String id;
    private String title;
    private String description;
    private Long price;
    private Instant closeTime;
    private Lot.LotStatus status;
    private String ownerId;
    private Instant createdAt;
}
