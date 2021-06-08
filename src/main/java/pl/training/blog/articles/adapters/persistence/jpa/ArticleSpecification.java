package pl.training.blog.articles.adapters.persistence.jpa;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class ArticleSpecification implements Specification<ArticleEntity> {

    private final Set<SearchCriteria> searchCriteria = new HashSet<>();

    public void add(SearchCriteria ... searchCriteria) {
        this.searchCriteria.addAll(asList(searchCriteria));
    }

    @Override
    public Predicate toPredicate(Root<ArticleEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        var predicates = new HashSet<Predicate>();
        for (SearchCriteria criteria : searchCriteria) {
            switch (criteria.getOperator()) {
                case GREATER_THEN:
                    predicates.add(builder.greaterThan(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case LESS_THEN:
                    predicates.add(builder.lessThan(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case GREATER_OR_EQUAL_THEN:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case LESS_OR_EQUAL_THEN:
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(root.get(criteria.getPropertyName()), criteria.getValue().toString()));
                    break;
                case MATCH:
                    predicates.add(builder.like(root.get(criteria.getPropertyName()), "%" + criteria.getValue() + "%"));
                    break;
                case MATCH_START:
                    predicates.add(builder.like(root.get(criteria.getPropertyName()), criteria.getValue() + "%"));
                    break;
                case MATCH_END:
                    predicates.add(builder.like(root.get(criteria.getPropertyName()), "%" + criteria.getValue()));
                    break;
                case IN:
                    predicates.add(builder.in(root.get(criteria.getPropertyName())).value(criteria.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(builder.in(root.get(criteria.getPropertyName())).value(criteria.getValue())));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
