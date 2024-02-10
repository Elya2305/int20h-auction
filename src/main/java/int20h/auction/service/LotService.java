package int20h.auction.service;

import int20h.auction.auth.context.UserContext;
import int20h.auction.domain.LotRequest;
import int20h.auction.entitiy.Lot;
import int20h.auction.exception.custom.AuthenticationException;
import int20h.auction.exception.custom.EntityNotFoundException;
import int20h.auction.mapper.LotMapper;
import int20h.auction.repository.LotRepository;
import int20h.auction.util.filtering.FilterRule;
import int20h.auction.util.filtering.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;
    private final LotMapper mapper;

    public Lot create(LotRequest request) {
        Lot lot = mapper.mapToEntity(request);
        return lotRepository.save(lot);
    }

    public Lot getById(String id) {
        Lot lot = lotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lot with id %s does not exist".formatted(id)));
        validateAuthorized(lot, "lot::get");
        return lot;
    }

    public List<Lot> getAll(List<FilterRule> filterRules, Sort sort) {
        FilterSpecification<Lot> specification = new FilterSpecification<>(filterRules);
        return lotRepository.findAll(specification, sort);
    }

    public Lot cancel(String id) {
        Lot lot = getById(id);
        validateAuthorized(lot, "lot::cancel");
        lot.setStatus(Lot.LotStatus.CANCELED);
        return lotRepository.save(lot);
    }

    public Lot update(LotRequest request, String id) {
        Lot lot = getById(id);
        validateAuthorized(lot, "lot::update");

        lot.setTitle(getOrDefault(request.getTitle(), lot.getTitle()));
        lot.setDescription(getOrDefault(request.getDescription(), lot.getDescription()));
        lot.setCloseTime(getOrDefault(request.getCloseTime(), lot.getCloseTime()));

        return lotRepository.save(lot);
    }

    private void validateAuthorized(Lot lot, String action) {
        if (!lot.getOwner().getId().equals(UserContext.getUserId())) {
            throw new AuthenticationException("User is not authorized to perform " + action);
        }
    }

    private <T> T getOrDefault(T val, T def) {
        return (Objects.isNull(val)) ? def : val;
    }
}
