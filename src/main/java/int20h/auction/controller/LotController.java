package int20h.auction.controller;

import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.mapper.LotMapper;
import int20h.auction.service.LotService;
import int20h.auction.util.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(Path.PREFIX)
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;
    private final LotMapper mapper;

    @PostMapping
    public LotResponse create(@RequestBody LotRequest lot) {
        log.info("Request on create lot {}", lot);
        return mapper.mapLotToResponse(lotService.create(lot));
    }

    @GetMapping
    public LotResponse getById(@RequestParam String id) {
        log.info("Request on get lot by id: " + id);
        return mapper.mapLotToResponse(lotService.getById(id));
    }

    @PostMapping(Path.CANCEL_LOT)
    public LotResponse cancelLot(@RequestParam String id) {
        log.info("Request on cancel lot with id: " + id);
        return mapper.mapLotToResponse(lotService.cancelLot(id));
    }

    @PostMapping(Path.UPDATE_LOT)
    public LotResponse updateLot(@RequestBody LotRequest lot, @RequestParam String id) {
        log.info("Request on update lot with id: " + id);
        return mapper.mapLotToResponse(lotService.updateLot(lot, id));
    }
}
