package int20h.auction.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BidRequest {
    private String lotId;
    private Long price;

}
