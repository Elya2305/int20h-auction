package int20h.auction.mapper;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.entitiy.Lot;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static int20h.auction.entitiy.Lot.LotStatus.OPEN;
import static java.time.Instant.now;

@Component
@RequiredArgsConstructor
public class LotMapper {
    private final UserRepository userRepository;

    public LotResponse mapToResponse(Lot lot) {
        return LotResponse.builder()
                .id(lot.getId())
                .title(lot.getTitle())
                .description(lot.getDescription())
                .price(lot.getPrice())
                .closeTime(lot.getCloseTime())
                .status(lot.getStatus())
                .ownerId(lot.getOwner().getId())
                .image(lot.getImage())
                .createdAt(lot.getCreatedAt())
                .build();
    }

    public Lot mapToEntity(LotRequest request) {
        return Lot.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .closeTime(request.getCloseTime())
                .owner(userRepository.getReferenceById(UserContext.getUserId()))
                .status(OPEN)
                .image(request.getImage())
                .createdAt(now())
                .build();
    }
}
