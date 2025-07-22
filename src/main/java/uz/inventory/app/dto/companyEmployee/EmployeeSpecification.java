package uz.inventory.app.dto.companyEmployee;

import org.springframework.data.jpa.domain.Specification;
import uz.inventory.app.entity.employee.EmployeeEntity;

public class EmployeeSpecification {
    // Filter by company name
    public static Specification<EmployeeEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    // Filter by company INN (tax ID)
    public static Specification<EmployeeEntity> hasInn(String inn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("inn"), "%" + inn + "%");
    }

    // Filter by company state
    public static Specification<EmployeeEntity> hasState(String state) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), state);
    }
}
