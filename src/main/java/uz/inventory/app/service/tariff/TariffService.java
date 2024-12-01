package uz.inventory.app.service.tariff;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.tariff.TariffDto;
import uz.inventory.app.dto.util.PaginationDto;
import uz.inventory.app.entity.tariff.TariffEntity;
import uz.inventory.app.enums.TariffDurationTypeEnum;
import uz.inventory.app.payload.ApiResponse;
import uz.inventory.app.repository.tariff.TariffRepository;
import uz.inventory.app.service.UtilService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TariffService {
    final private TariffRepository tariffRepository;
    final private UtilService utilService;

    public Page<TariffDto> getTariffs(PaginationDto paginationDto) {
        Pageable pageable = utilService.getPageable(paginationDto);
        Page<TariffEntity> tariffEntities = tariffRepository.findAll(pageable);
        return tariffEntities.map(this::getTariffDto);
    }

    public ResponseEntity<TariffDto> getTariff(Long id) {
        Optional<TariffEntity> tariffEntity = tariffRepository.findById(id);
        return tariffEntity.map(
                        entity -> ResponseEntity.ok(getTariffDto(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> saveTariff(TariffDto tariffDto) {
        try {
            TariffEntity tariffEntity = new TariffEntity();
            tariffEntity.setName(tariffDto.getName());
            tariffEntity.setDescription(tariffDto.getDescription());
            tariffEntity.setState(tariffDto.getState());
            tariffEntity.setPrise(tariffDto.getPrise());
            tariffEntity.setDurationType(TariffDurationTypeEnum.valueOf(tariffDto.getDuration_type()));
            tariffEntity.setDuration(tariffDto.getDuration());
            tariffRepository.save(tariffEntity);
            return ResponseEntity.ok(new ApiResponse("Muvaffiqiyatli saqlandi", true));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }

    public ResponseEntity<?> updateTariff(TariffDto tariffDto) {
        try {
            if (tariffRepository.findById(tariffDto.getId()).isPresent()) {
                tariffRepository.findById(tariffDto.getId()).map(tariffEntity -> {
                    tariffEntity.setName(tariffDto.getName());
                    tariffEntity.setDescription(tariffDto.getDescription());
                    tariffEntity.setState(tariffDto.getState());
                    tariffEntity.setPrise(tariffDto.getPrise());
                    tariffEntity.setDuration(tariffDto.getDuration());
                    tariffEntity.setDurationType(TariffDurationTypeEnum.valueOf(tariffDto.getDuration_type()));
                    tariffRepository.save(tariffEntity);
                    return tariffEntity;
                });
                return ResponseEntity.ok(new ApiResponse("Muvaffiqiyatli almashtirildi", true));
            } else {
                return ResponseEntity.ok(new ApiResponse("Bunday tariff topilmadi", false));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }

    public ResponseEntity<?> deleteTariff(Long id) {
        try {
            if (tariffRepository.findById(id).isPresent()) {
                tariffRepository.deleteById(id);
                return ResponseEntity.ok(new ApiResponse("Muvaffiqiyatli o'chrildi", true));
            }
            return ResponseEntity.ok(new ApiResponse("Bunday tariff topilmadi", false));

        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), false));
        }
    }

    private TariffDto getTariffDto(TariffEntity tariffEntity) {
        TariffDto tariffDto = new TariffDto();
        BeanUtils.copyProperties(tariffEntity, tariffDto);
        return tariffDto;
    }
}
