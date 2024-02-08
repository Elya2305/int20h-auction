package int20h.auction.controller;

import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.service.LotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/lot")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;

    @PostMapping
    public LotResponse create(@RequestBody LotRequest lot) {
        log.info("Request on create lot {}", lot);
        return lotService.create(lot);
    }
}
