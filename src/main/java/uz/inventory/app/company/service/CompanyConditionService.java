package uz.inventory.app.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.inventory.app.company.entity.CompanyConditionEntity;
import uz.inventory.app.company.repository.CompanyConditionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyConditionService {

    private final CompanyConditionRepository companyConditionRepository;

    public List<CompanyConditionEntity> getAll() {
        return companyConditionRepository.findAll();
    }

    public Optional<CompanyConditionEntity> getById(Long id) {
        return companyConditionRepository.findById(id);
    }

    public CompanyConditionEntity save(CompanyConditionEntity entity) {
        return companyConditionRepository.save(entity);
    }

    public CompanyConditionEntity update(Long id, CompanyConditionEntity updatedEntity) {
        return companyConditionRepository.findById(id).map(entity -> {
            entity.setName(updatedEntity.getName());
            entity.setDescription(updatedEntity.getDescription());
            entity.setState(updatedEntity.getState());
            return companyConditionRepository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Company Condition not found with id " + id));
    }

    public void delete(Long id) {
        if (companyConditionRepository.existsById(id)) {
            companyConditionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Company Condition not found with id " + id);
        }
    }
}
