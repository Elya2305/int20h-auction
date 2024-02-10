package int20h.auction.mapper;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.BidRequest;
import int20h.auction.entitiy.Bid;
import int20h.auction.entitiy.Lot;
import int20h.auction.repository.BidRepository;
import int20h.auction.repository.LotRepository;
import int20h.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class BidMapper {
    private final LotRepository lotRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;

    public Bid mapToEntity(BidRequest bidRequest) {
        Lot lot = lotRepository.getReferenceById(bidRequest.getLotId());

        return Bid.builder()
                .id(UUID.randomUUID().toString())
                .lot(lot)
                .price(bidRequest.getPrice())
                .userName(userRepository.getReferenceById(UserContext.getUserId()).getUsername())
                .isBest(bidRepository.getBestBidByLotId(lot.getId()) < bidRequest.getPrice())

    }
}
