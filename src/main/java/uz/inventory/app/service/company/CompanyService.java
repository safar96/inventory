package uz.inventory.app.service.company;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.inventory.app.component.SearchSpecification;
import uz.inventory.app.dto.company.CompanyDto;
import uz.inventory.app.dto.company.CompanyTariffDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.entity.tariff.TariffEntity;
import uz.inventory.app.payload.ApiResponse;
import uz.inventory.app.repository.company.CompanyRepository;
import uz.inventory.app.repository.tariff.TariffRepository;
import uz.inventory.app.service.util.BaseService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService extends BaseService {

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

    public ResponseEntity<?> setCompanyTariff(CompanyTariffDto companyTariffDto) {
        try {
            Optional<CompanyEntity> company = companyRepository.findById(companyTariffDto.getCompany_id());
            if (company.isPresent()) {
                Optional<TariffEntity> result = tariffRepository.findById(companyTariffDto.getTariff_id());
                if (result.isPresent()) {
                    company.get().setTariff(result.get());
                    companyRepository.save(company.get());
                    return new ResponseEntity<>(new ApiResponse("Muvafiqiyatli saqlandi", true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse("Bunday tariff topilmadi", false), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(new ApiResponse("Bunday companiya topilmadi", false), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.OK);
        }
    }
}
