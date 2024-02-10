package int20h.auction.service;

import int20h.auction.domain.BidRequest;
import int20h.auction.entitiy.Bid;
import int20h.auction.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;

//    public Bid createBid(BidRequest bidRequest) {
//
//    }
}
