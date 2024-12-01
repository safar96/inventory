package uz.inventory.app.component;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class SearchSpecification {
    public static <T> Specification<T> containsSearchTerm(String search, String... fields) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty() || fields == null || fields.length == 0) {
                return criteriaBuilder.conjunction(); // No filtering
            }

            String likePattern = "%" + search.toLowerCase() + "%";
            List<Predicate> predicates = new ArrayList<>();

            for (String field : fields) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), likePattern));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
