package uz.inventory.app.service.company;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.company.CompanySpecification;
import uz.inventory.app.dto.company.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.repository.company.CompanyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

   final private CompanyRepository companyRepository;

    public Page<CompanyEntity> getCompanies(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PageRequest.of(paginationRequestDto.getPage(), paginationRequestDto.getSize(),
                paginationRequestDto.getSortDir().equals("desc") ? Sort.by(Sort.Order.desc(paginationRequestDto.getSortBy())) :
                        Sort.by(Sort.Order.asc(paginationRequestDto.getSortBy())));
        Specification<CompanyEntity> specification = Specification.where(null);

        if (paginationRequestDto.getName() != null && !paginationRequestDto.getName().isEmpty()) {
            specification = specification.and(CompanySpecification.hasName(paginationRequestDto.getName()));
        }
        if (paginationRequestDto.getInn() != null && !paginationRequestDto.getInn().isEmpty()) {
            specification = specification.and(CompanySpecification.hasInn(paginationRequestDto.getInn()));
        }


        return companyRepository.findAll(specification, pageable);
    }

    public Optional<CompanyEntity> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public CompanyEntity saveCompany(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public CompanyEntity updateCompany(Long id, CompanyEntity updatedCompany) {
        return companyRepository.findById(id).map(company -> {
            company.setName(updatedCompany.getName());
            company.setInn(updatedCompany.getInn());
            company.setAddress(updatedCompany.getAddress());
            company.setState(updatedCompany.getState());
            company.setConditionId(updatedCompany.getConditionId());
            return companyRepository.save(company);
        }).orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }

    public String deleteCompany(Long id) throws JSONException {
        JSONObject res = new JSONObject();
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            res.put("success", true);
            res.put("msg", "Company successfully deleted");
            return res.toString();
        } else {
            throw new RuntimeException("Company not found with id " + id);
        }
    }
}
