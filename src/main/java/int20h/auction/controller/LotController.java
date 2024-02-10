package int20h.auction.controller;

import int20h.auction.domain.LotRequest;
import int20h.auction.domain.LotResponse;
import int20h.auction.entitiy.User;
import int20h.auction.mapper.LotMapper;
import int20h.auction.repository.UserRepository;
import int20h.auction.service.LotService;
import int20h.auction.util.filtering.FilterData;
import int20h.auction.util.filtering.FilterRule;
import int20h.auction.util.filtering.FilterType;
import int20h.auction.util.filtering.SortData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static int20h.auction.util.filtering.FilterUtils.prepareFilters;
import static int20h.auction.util.filtering.FilterUtils.prepareSort;

@Log4j2
@RestController
@RequestMapping("/v1/lot")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;
    private final LotMapper mapper;
    private final UserRepository userRepository;

    @PostMapping
    public LotResponse create(@RequestBody LotRequest lot) {
        log.info("Request on create lot {}", lot);
        return mapper.mapToResponse(lotService.create(lot));
    }

    @GetMapping("/{id}")
    public LotResponse getById(@PathVariable String id) {
        log.info("Request on get lot by id: " + id);
        return mapper.mapToResponse(lotService.getById(id));
    }

    @GetMapping
    public List<LotResponse> getAll(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, value = "filter[title]") String title,
            @RequestParam(required = false, value = "filter[owner_id]") String ownerId
    ) {
        log.info("Request list");
        List<FilterRule> filterRules = prepareFilters(
                new FilterData("title", title, FilterType.LIKE),
                new FilterData("owner", getOptionalUserIfIdIsNotNull(ownerId).orElse(null), FilterType.EQ)
        );

        Sort sortRules = prepareSort(new SortData(sort));

        return mapper.mapToResponse(lotService.getAll(filterRules, sortRules));
    }

    @PatchMapping("/{id}/cancel")
    public LotResponse cancel(@PathVariable String id) {
        log.info("Request on cancel lot with id: " + id);
        return mapper.mapToResponse(lotService.cancel(id));
    }

    @PutMapping("/{id}")
    public LotResponse update(@PathVariable String id, @RequestBody LotRequest lot) {
        log.info("Request on update lot with id: " + id);
        return mapper.mapToResponse(lotService.update(lot, id));
    }

    private Optional<User> getOptionalUserIfIdIsNotNull(String userId) {
        return Objects.isNull(userId) ? Optional.empty() : userRepository.findById(userId);
    }
}
