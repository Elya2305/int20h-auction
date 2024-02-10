package int20h.auction.util.filtering;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterSpecification<T> implements Specification<T> {

    private final List<FilterRule> filters;

    public FilterSpecification(List<FilterRule> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        filters.forEach(filter -> {
            String fieldName = filter.fieldName();
            Object value = filter.value();
            FilterType type = filter.type();

            switch (type) {
                case EQ -> predicates.add(builder.equal(root.get(fieldName), value));
                case IN -> predicates.add(root.get(fieldName).in((Collection<?>) value));
                case LIKE -> predicates.add(builder.like(root.get(fieldName), "%" + value + "%"));
            }
        });

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}