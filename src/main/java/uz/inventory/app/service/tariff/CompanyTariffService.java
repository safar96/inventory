package uz.inventory.app.service.tariff;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.tariff.CompanyTariffDto;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.entity.tariff.CompanyTariffEntity;
import uz.inventory.app.entity.tariff.TariffEntity;
import uz.inventory.app.enums.TariffDurationTypeEnum;
import uz.inventory.app.payload.CustomApiResponse;
import uz.inventory.app.company.repository.CompanyRepository;
import uz.inventory.app.repository.tariff.CompanyTariffRepository;
import uz.inventory.app.repository.tariff.TariffRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyTariffService {
   final private CompanyTariffRepository companyTariffRepository;
   final private TariffRepository tariffRepository;
   final private CompanyRepository companyRepository;


   public ResponseEntity<?> saveCompanyTariff(CompanyTariffDto companyTariffDto) {
       try{
           Optional<CompanyEntity> optionalCompany = companyRepository.findById(companyTariffDto.getCompany_id());
           if(optionalCompany.isPresent()){
               Optional<TariffEntity> optionalTariff = tariffRepository.findById(companyTariffDto.getTariff_id());
               if(optionalTariff.isPresent()){

//                   Oldin aktive bo'lib turgan tarifni passive qilib qo'yish
                   Optional<CompanyTariffEntity> cmOptional =
                           companyTariffRepository.findByCompanyIdAndTariffIdAndState(companyTariffDto.getCompany_id(), companyTariffDto.getTariff_id(),"A");
                   if(cmOptional.isPresent()){
                       CompanyTariffEntity companyTariffEntity = cmOptional.get();
                       companyTariffEntity.setState("P");
                       companyTariffEntity.setExpireDate(new Timestamp(System.currentTimeMillis()));
                       companyTariffRepository.save(companyTariffEntity);
                   }

//                   Yangi tariffga biriktirish
                   TariffEntity tariffEntity = optionalTariff.get();
                   CompanyTariffEntity companyTariffEntity = new CompanyTariffEntity();
                   companyTariffEntity.setCompanyId(companyTariffDto.getCompany_id());
                   companyTariffEntity.setTariffId(companyTariffDto.getTariff_id());
                   companyTariffEntity.setState("A");
                   companyTariffEntity.setExpireDate(getExpireDate(tariffEntity.getDurationType(),tariffEntity.getDuration()));
                   companyTariffRepository.save(companyTariffEntity);
                   return new ResponseEntity<>(new CustomApiResponse("Muvaffiqiyatli saqlandi",true), HttpStatus.OK);
               }else {
                   return new ResponseEntity<>(new CustomApiResponse("Bunday tariff topilmadi",false), HttpStatus.NOT_FOUND);
               }

           }else {
               return new ResponseEntity<>(new CustomApiResponse("Bunday kompaniya topilmadi",false), HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           return new ResponseEntity<>(new CustomApiResponse(e.getMessage(),false), HttpStatus.CONFLICT);
       }
   }

   private Date getExpireDate(TariffDurationTypeEnum durationTypeEnum,int duration){
       LocalDateTime currentTimestamp = LocalDateTime.now();
       if (durationTypeEnum == TariffDurationTypeEnum.Day){
           LocalDateTime updatedTimestampDay  = currentTimestamp.plusDays(duration);
           Instant instant = updatedTimestampDay.atZone(ZoneId.systemDefault()).toInstant();
           return Date.from(instant);
       } else if (durationTypeEnum == TariffDurationTypeEnum.Month) {
           LocalDateTime updatedTimestampDay  = currentTimestamp.plusMonths(duration);
           Instant instant = updatedTimestampDay.atZone(ZoneId.systemDefault()).toInstant();
           return Date.from(instant);
       }else if (durationTypeEnum == TariffDurationTypeEnum.Year) {
           LocalDateTime updatedTimestampDay  = currentTimestamp.plusYears(duration);
           Instant instant = updatedTimestampDay.atZone(ZoneId.systemDefault()).toInstant();
           return Date.from(instant);
       }
       return null;
   }
}
