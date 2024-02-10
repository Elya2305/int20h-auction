package int20h.auction.service;

import int20h.auction.domain.BidRequest;
import int20h.auction.entitiy.Bid;
import int20h.auction.entitiy.Lot;
import int20h.auction.exception.custom.ValidationException;
import int20h.auction.mapper.BidMapper;
import int20h.auction.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final LotService lotService;
    private final BidMapper bidMapper;

    public Bid create(String lotId, BidRequest request) {
        Lot lot = lotService.getById(lotId);
        if (request.getPrice() < lot.getPrice()) {
            throw new ValidationException("Bid price can't be less than " + lot.getPrice());
        }

        Optional<Bid> optionalBestBid = bidRepository.findByIsBestAndLot(true, lot);
        if (optionalBestBid.isEmpty()) {
            return createBestBid(lot, request.getPrice());
        }

        Bid bestBid = optionalBestBid.get();
        if (request.getPrice() <= bestBid.getPrice()) {
            throw new ValidationException("Bid price can't be less than or equal to " + bestBid.getPrice());
        }
        bestBid.setBest(false);
        bidRepository.save(bestBid);
        return createBestBid(lot, request.getPrice());
    }

    private Bid createBestBid(Lot lot, long price) {
        Bid bid = bidMapper.mapToNewEntity(lot, true, price);
        return bidRepository.save(bid);
    }
}
