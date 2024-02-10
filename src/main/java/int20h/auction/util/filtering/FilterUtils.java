package int20h.auction.util.filtering;

import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

public class FilterUtils {

    public static List<FilterRule> prepareFilters(FilterData... filters) {
        return Arrays.stream(filters)
                .filter(o -> Objects.nonNull(o.value()))
                .map(o -> new FilterRule(o.fieldName(), o.value(), o.type()))
                .toList();
    }

    public static Sort prepareSort(SortData... parameters) {
        List<String> allowedProperties = List.of("createdAt", "-createdAt", "price", "-price");

        List<Sort.Order> orders = Arrays.stream(parameters)
                .map(SortData::value)
                .filter(Objects::nonNull)
                .filter(allowedProperties::contains)
                .map(value -> value.startsWith("-") ? desc(value.substring(1)) : asc(value)).toList();

        return Sort.by(orders);
    }
}