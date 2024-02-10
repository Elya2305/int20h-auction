package int20h.auction.mapper;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.BidResponse;
import int20h.auction.entitiy.Bid;
import int20h.auction.entitiy.Lot;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BidMapper {
    private final UserRepository userRepository;

    public Bid mapToNewEntity(Lot lot, boolean isBest, long price) {

        return Bid.builder()
                .id(UUID.randomUUID().toString())
                .lot(lot)
                .price(price)
                .lot(lot)
                .owner(userRepository.getReferenceById(UserContext.getUserId()))
                .isBest(isBest)
                .createdAt(Instant.now())
                .build();

    }

    public BidResponse mapToResponse(Bid bid) {

        return BidResponse.builder()
                .id(bid.getId())
                .lotId(bid.getLot().getId())
                .price(bid.getPrice())
                .username(bid.getOwner().getUsername())
                .isBest(bid.isBest())
                .createdAt(bid.getCreatedAt())
                .build();
    }
}
