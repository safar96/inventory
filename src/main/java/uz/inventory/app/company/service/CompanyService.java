package uz.inventory.app.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.component.SearchSpecification;
import uz.inventory.app.company.dto.CompanyDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.company.repository.CompanyRepository;
import uz.inventory.app.repository.tariff.TariffRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    final private CompanyRepository companyRepository;
    final private TariffRepository tariffRepository;


    public Page<CompanyDto> getCompanies(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch(); // Get search term from request
        String[] searchFields = {"name", "address", "inn"};
        Specification<CompanyEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),  // Current page number
                paginationRequestDto.getSize(),  // Number of items per page
                Sort.by(Sort.Order.asc("name"))  // Sort by 'name' in ascending order
        );
        Page<CompanyEntity> companyPage = companyRepository.findAll(specification, pageable);
        return companyPage.map(company -> {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(company.getId());
            companyDto.setInn(company.getInn());
            companyDto.setName(company.getName());
            companyDto.setState(company.getState());
            companyDto.setCondition_id(company.getConditionId());
            companyDto.setAddress(company.getAddress());
            return companyDto;
        });
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
