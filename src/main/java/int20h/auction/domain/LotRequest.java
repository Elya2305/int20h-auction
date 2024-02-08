package int20h.auction.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class LotRequest {
    private String title;
    private String description;
    private Long price;
    private Instant closeTime;
}
