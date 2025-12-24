package uz.inventory.app.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.company.dto.CompanyUserDto;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.company.entity.CompanyUserEntity;
import uz.inventory.app.company.repository.CompanyRepository;
import uz.inventory.app.company.repository.CompanyUserRepository;
import uz.inventory.app.user.entity.UserEntity;
import uz.inventory.app.user.repository.UserRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyUserService {

    private final CompanyUserRepository companyUserRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public Page<CompanyUserDto> getAll(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.desc("id"))
        );

        Page<CompanyUserEntity> page = companyUserRepository.findAll(pageable);
        return page.map(this::toDto);
    }

    public Optional<CompanyUserEntity> getById(Long id) {
        return companyUserRepository.findById(id);
    }

    public CompanyUserEntity assignUser(Long companyId, Long userId) {
        CompanyEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id " + companyId));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        CompanyUserEntity entity = new CompanyUserEntity();
        entity.setCompany(company);
        entity.setUser(user);
        entity.setState(1);

        return companyUserRepository.save(entity);
    }

    public void delete(Long id) {
        if (companyUserRepository.existsById(id)) {
            companyUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("Company User not found with id " + id);
        }
    }

    private CompanyUserDto toDto(CompanyUserEntity entity) {
        CompanyUserDto dto = new CompanyUserDto();
        dto.setId(entity.getId());
        dto.setState(entity.getState());

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setUserName(entity.getUser().getUsername());
        }

        return dto;
    }
}
