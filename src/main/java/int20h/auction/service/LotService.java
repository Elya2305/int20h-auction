package int20h.auction.service;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.entitiy.Lot;
import int20h.auction.repository.LotRepository;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static int20h.auction.entitiy.Lot.LotStatus.OPEN;
import static java.time.Instant.now;

@Service
@RequiredArgsConstructor
public class LotService {
    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    public LotResponse create(LotRequest request) {
        Lot lot = Lot.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .closeTime(request.getCloseTime())
                .owner(userRepository.getReferenceById(UserContext.getUserUuid()))
                .status(OPEN)
                .createdAt(now())
                .build();
        Lot entity = lotRepository.save(lot);
        return LotResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .closeTime(entity.getCloseTime())
                .status(entity.getStatus())
                .ownerId(UserContext.getUserUuid())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
