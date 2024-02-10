package int20h.auction.controller;

import int20h.auction.domain.BidRequest;
import int20h.auction.domain.BidResponse;
import int20h.auction.service.BidService;
import int20h.auction.util.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(Path.BID_MAPPING)
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

//    @PostMapping
//    public BidResponse createBid(@RequestBody BidRequest bidRequest) {
//        log.info("Request on create bid for lot with id: " + bidRequest.getLotId());
//        bidService
//    }
}
