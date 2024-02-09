package int20h.auction.service;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.LotRequest;
import int20h.auction.entitiy.Lot;
import int20h.auction.entitiy.User;
import int20h.auction.mapper.LotMapper;
import int20h.auction.repository.LotRepository;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotService {
    private final UserRepository userRepository;
    private final LotRepository lotRepository;
    private final LotMapper mapper;

    public Lot create(LotRequest request) {
        User user = userRepository.getReferenceById(UserContext.getUserUuid());
        Lot lot = mapper.mapLotRequestToLotWithUser(request, user);
        return lotRepository.save(lot);
    }

    public Lot getById(String id) {
        return lotRepository.getReferenceById(id);
    }

    public Lot cancelLot(String id) {
        Lot lot = lotRepository.getReferenceById(id);
        lot.setStatus(Lot.LotStatus.CANCELED);
        lotRepository.save(lot);

        return lot;
    }

    public Lot updateLot(LotRequest request, String id) {
        Lot lot = lotRepository.getReferenceById(id);

        lot.setTitle(request.getTitle());
        lot.setDescription(request.getDescription());
        lot.setPrice(request.getPrice());
        lot.setCloseTime(request.getCloseTime());

        lotRepository.save(lot);

        return lot;
    }
}
