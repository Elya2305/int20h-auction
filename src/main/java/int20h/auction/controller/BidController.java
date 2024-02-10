package int20h.auction.controller;

import int20h.auction.domain.BidRequest;
import int20h.auction.domain.BidResponse;
import int20h.auction.mapper.BidMapper;
import int20h.auction.service.BidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;
    private final BidMapper bidMapper;

    @PostMapping("/lot/{lotId}/bid")
    public BidResponse createBid(@PathVariable String lotId, @RequestBody BidRequest bidRequest) {
        log.info("Request on create bid for lot: " + lotId);
        return bidMapper.mapToResponse(bidService.create(lotId, bidRequest));
    }
}
