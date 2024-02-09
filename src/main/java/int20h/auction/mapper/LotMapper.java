package int20h.auction.mapper;

import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.entitiy.Lot;
import int20h.auction.entitiy.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static int20h.auction.entitiy.Lot.LotStatus.OPEN;
import static java.time.Instant.now;

@Component
public class LotMapper {

    public LotResponse mapLotToResponse(Lot lot) {
        return LotResponse.builder()
                .id(lot.getId())
                .title(lot.getTitle())
                .description(lot.getDescription())
                .price(lot.getPrice())
                .closeTime(lot.getCloseTime())
                .status(lot.getStatus())
                .ownerId(lot.getOwner().getId())
                .createdAt(lot.getCreatedAt())
                .build();
    }

    public Lot mapLotRequestToLotWithUser(LotRequest request, User user) {
        return Lot.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .closeTime(request.getCloseTime())
                .owner(user)
                .status(OPEN)
                .createdAt(now())
                .build();
    }
}