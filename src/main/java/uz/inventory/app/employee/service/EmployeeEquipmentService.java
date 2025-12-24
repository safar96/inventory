package uz.inventory.app.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.employee.dto.EmployeeEquipmentDto;
import uz.inventory.app.employee.entity.EmployeeEntity;
import uz.inventory.app.employee.entity.EmployeeEquipmentEntity;
import uz.inventory.app.employee.repository.EmployeeEquipmentRepository;
import uz.inventory.app.employee.repository.EmployeeRepository;
import uz.inventory.app.equipment.entity.EquipmentEntity;
import uz.inventory.app.equipment.repository.EquipmentRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeEquipmentService {

    private final EmployeeEquipmentRepository employeeEquipmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EquipmentRepository equipmentRepository;

    public Page<EmployeeEquipmentDto> getAll(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.desc("assignedDate"))
        );

        Page<EmployeeEquipmentEntity> page = employeeEquipmentRepository.findAll(pageable);
        return page.map(this::toDto);
    }

    public Optional<EmployeeEquipmentEntity> getById(Long id) {
        return employeeEquipmentRepository.findById(id);
    }

    public EmployeeEquipmentEntity assignEquipment(Long employeeId, Long equipmentId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));

        EquipmentEntity equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found with id " + equipmentId));

        EmployeeEquipmentEntity entity = new EmployeeEquipmentEntity();
        entity.setEmployee(employee);
        entity.setEquipment(equipment);
        entity.setAssignedDate(LocalDateTime.now());
        entity.setState(1); // Active

        return employeeEquipmentRepository.save(entity);
    }

    public EmployeeEquipmentEntity returnEquipment(Long id) {
        return employeeEquipmentRepository.findById(id).map(entity -> {
            entity.setReturnedDate(LocalDateTime.now());
            entity.setState(0); // Returned
            return employeeEquipmentRepository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Employee Equipment not found with id " + id));
    }

    public void delete(Long id) {
        if (employeeEquipmentRepository.existsById(id)) {
            employeeEquipmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee Equipment not found with id " + id);
        }
    }

    private EmployeeEquipmentDto toDto(EmployeeEquipmentEntity entity) {
        EmployeeEquipmentDto dto = new EmployeeEquipmentDto();
        dto.setId(entity.getId());
        dto.setAssignedDate(entity.getAssignedDate());
        dto.setReturnedDate(entity.getReturnedDate());
        dto.setState(entity.getState());

        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
            dto.setEmployeeName(entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
        }

        if (entity.getEquipment() != null) {
            dto.setEquipmentId(entity.getEquipment().getId());
            dto.setEquipmentName(entity.getEquipment().getName());
        }

        return dto;
    }
}
