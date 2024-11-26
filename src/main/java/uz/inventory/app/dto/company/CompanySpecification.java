package uz.inventory.app.dto.company;

import org.springframework.data.jpa.domain.Specification;

import uz.inventory.app.entity.company.CompanyEntity;

public class CompanySpecification {

    // Filter by company name
    public static Specification<CompanyEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    // Filter by company INN (tax ID)
    public static Specification<CompanyEntity> hasInn(String inn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("inn"), "%" + inn + "%");
    }

    // Filter by company state
    public static Specification<CompanyEntity> hasState(String state) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), state);
    }
}
