package uz.inventory.app.util;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.inventory.app.util.dto.PaginationDto;
import uz.inventory.app.util.entity.DistrictsEntity;
import uz.inventory.app.util.entity.GendersEntity;
import uz.inventory.app.util.entity.RegionsEntity;
import uz.inventory.app.util.repository.DistrictRepository;
import uz.inventory.app.util.repository.GenderRepository;
import uz.inventory.app.util.repository.RegionRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilService {

    final private RegionRepository regionRepository;
    final private DistrictRepository districtRepository;
    final private GenderRepository genderRepository;

    public ResponseEntity<?> getRegions(){
        List<RegionsEntity> regions = regionRepository.findAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    public ResponseEntity<?> getDistricts(long regionId){
        List<DistrictsEntity> districts = districtRepository.findAllByRegionId(regionId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    public ResponseEntity<?> getGenders(){
        List<GendersEntity> genders = genderRepository.findAll();
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }


    public Pageable getPageable(PaginationDto paginationDto) {
        return PageRequest.of(paginationDto.getPage(), paginationDto.getSize(),
                Sort.by(Sort.Order.asc("id")));
    }

}