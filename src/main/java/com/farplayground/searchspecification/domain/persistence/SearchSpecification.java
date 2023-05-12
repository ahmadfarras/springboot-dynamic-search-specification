package com.farplayground.searchspecification.domain.persistence;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.*;

/**
 * @author farras
 * @since 0.0.1
 */
public class SearchSpecification<T> implements Specification<T> {

    private final List<String> columns;

    private final String search;

    private final Map<?, List<?>> filter;

    private final Map<?, List<?>> dateRange;

    public SearchSpecification(Map<?, List<?>> filter) {
        this(null, filter);
    }

    public SearchSpecification(List<String> columns, String search) {
        this(columns, search, null);
    }

    public SearchSpecification(List<String> columns, Map<?, List<?>> filter) {
        this(columns, null, filter);
    }

    public SearchSpecification(List<String> columns, String search, Map<?,List<?>> filter) {
        this(columns, search, filter, null);
    }

    public SearchSpecification(List<String> columns, String search, Map<?, List<?>> filter, Map<?, List<?>> dateRange) {
        this.columns = columns;
        this.search = search;
        this.filter = filter;
        this.dateRange = dateRange;
    }

    //TODO: Still error when using data type other than string. Need to find a way to search with different data type.
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate searchPredicate = createSearchPredicate(root, criteriaBuilder);

        Predicate addedFilterPredicate = addFilter(criteriaBuilder, root, searchPredicate);

        Predicate addedDateRangePredicate = addDateRange(criteriaBuilder, root, addedFilterPredicate);

        return addedDateRangePredicate;
    }

    @SuppressWarnings("unchecked")
    private <S> S buildPath(String column, Path<T> path) {

        if (!column.contains(".")){
            return (S) path.get(column);
        }

        String[] parts = column.split("\\.");
        for (String part: parts) {
            path = path.get(part);
        }

        return (S) path;
    }

    private Predicate createSearchPredicate(Root<T> root, CriteriaBuilder criteriaBuilder){

        List<Predicate> predicates = new ArrayList<>();

        if (search == null){
            return criteriaBuilder.conjunction();
        }

        for (String column: columns) {
            Expression<String> searchPath = buildPath(column, root);
            Predicate predicate = criteriaBuilder.like(
                    criteriaBuilder.lower(searchPath), "%" + search.toLowerCase() + "%");
            predicates.add(predicate);
        }

        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    private Predicate addFilter(CriteriaBuilder criteriaBuilder, Root<T> root, Predicate searchPredicate){
        if (filter == null){
            return searchPredicate;
        }

        Set<?> filterKeys = filter.keySet();

        for (Object filterKey : filterKeys) {
            List<?> filterValue = filter.get(filterKey);
            if (filterValue == null){
                continue;
            }

            boolean nullsOnly = filterValue.stream().noneMatch(Objects::nonNull);
            if (nullsOnly){
                continue;
            }

            Expression<String> filterPath = buildPath(filterKey.toString(), root);

            searchPredicate = criteriaBuilder.and(searchPredicate, filterPath.in(filterValue));
        }

        return searchPredicate;
    }

    private Predicate addDateRange(CriteriaBuilder criteriaBuilder, Root<T> root, Predicate searchPredicate){
        if (dateRange == null){
            return searchPredicate;
        }

        Set<?> dateRangeKeys = dateRange.keySet();

        for (Object dateRangeKey : dateRangeKeys) {
            List<?> rangeValue = dateRange.get(dateRangeKey);
            if (rangeValue == null){
                continue;
            }

            if (rangeValue.contains(null)){
                continue;
            }

            Expression<LocalDate> rangePath = buildPath(dateRangeKey.toString(), root);

            searchPredicate = criteriaBuilder.and(searchPredicate, criteriaBuilder.between(rangePath,
                    (LocalDate) rangeValue.get(0), (LocalDate) rangeValue.get(1)));
        }

        return searchPredicate;
    }
}
