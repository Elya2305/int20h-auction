package int20h.auction.controller;

import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.mapper.LotMapper;
import int20h.auction.service.LotService;
import int20h.auction.util.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(Path.LOT_MAPPING)
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;
    private final LotMapper mapper;

    @PostMapping
    public LotResponse create(@RequestBody LotRequest lot) {
        log.info("Request on create lot {}", lot);
        return mapper.mapToResponse(lotService.create(lot));
    }

    @GetMapping(Path.GET_LOT)
    public LotResponse getById(@PathVariable String id) {
        log.info("Request on get lot by id: " + id);
        return mapper.mapToResponse(lotService.getById(id));
    }

    @PatchMapping(Path.CANCEL_LOT)
    public LotResponse cancel(@PathVariable String id) {
        log.info("Request on cancel lot with id: " + id);
        return mapper.mapToResponse(lotService.cancel(id));
    }

    @PutMapping(Path.UPDATE_LOT)
    public LotResponse update(@PathVariable String id, @RequestBody LotRequest lot) {
        log.info("Request on update lot with id: " + id);
        return mapper.mapToResponse(lotService.update(lot, id));
    }
}
