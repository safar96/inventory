package uz.inventory.app.core.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.inventory.app.auth.entity.RoleEntity;
import uz.inventory.app.company.entity.CompanyConditionEntity;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.company.repository.CompanyConditionRepository;
import uz.inventory.app.company.repository.CompanyRepository;
import uz.inventory.app.employee.entity.EmployeeEntity;
import uz.inventory.app.employee.repository.EmployeeRepository;
import uz.inventory.app.equipment.entity.EquipmentEntity;
import uz.inventory.app.equipment.entity.EquipmentTypeEntity;
import uz.inventory.app.equipment.repository.EquipmentRepository;
import uz.inventory.app.equipment.repository.EquipmentTypeRepository;
import uz.inventory.app.section.entity.SectionEntity;
import uz.inventory.app.section.repository.SectionRepository;
import uz.inventory.app.user.entity.UserEntity;
import uz.inventory.app.util.entity.GendersEntity;
import uz.inventory.app.auth.repository.RoleRepository;
import uz.inventory.app.user.repository.UserRepository;
import uz.inventory.app.util.repository.GenderRepository;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialMode;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenderRepository genderRepository;
    private final CompanyRepository companyRepository;
    private final CompanyConditionRepository companyConditionRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentRepository equipmentRepository;
    private final SectionRepository sectionRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            // Rollar
            RoleEntity adminRole = roleRepository.save(new RoleEntity(1, "ROLE_ADMIN"));
            roleRepository.save(new RoleEntity(2, "ROLE_USER"));
            roleRepository.save(new RoleEntity(3, "ROLE_MODERATOR"));
            roleRepository.save(new RoleEntity(4, "ROLE_SUPER_ADMIN"));

            // Admin user
            userRepository.save(new UserEntity(
                    "admin",
                    "admin",
                    "admin",
                    "123",
                    passwordEncoder.encode("admin"),
                    roleRepository.findAllById(1)
            ));

            // Jinslar
            genderRepository.save(new GendersEntity("Erkak"));
            genderRepository.save(new GendersEntity("Ayol"));

            // Kompaniya holatlari
            CompanyConditionEntity activeCondition = new CompanyConditionEntity();
            activeCondition.setName("Faol");
            activeCondition.setDescription("Kompaniya faol ishlayapti");
            activeCondition.setState(1);
            activeCondition = companyConditionRepository.save(activeCondition);

            CompanyConditionEntity pausedCondition = new CompanyConditionEntity();
            pausedCondition.setName("To'xtatilgan");
            pausedCondition.setDescription("Kompaniya vaqtincha to'xtatilgan");
            pausedCondition.setState(0);
            companyConditionRepository.save(pausedCondition);

            CompanyConditionEntity closedCondition = new CompanyConditionEntity();
            closedCondition.setName("Yopilgan");
            closedCondition.setDescription("Kompaniya yopilgan");
            closedCondition.setState(-1);
            companyConditionRepository.save(closedCondition);

            // Kompaniyalar
            CompanyEntity uzAutoSanoat = new CompanyEntity();
            uzAutoSanoat.setName("UzAuto Motors");
            uzAutoSanoat.setInn("200123456");
            uzAutoSanoat.setAddress("Toshkent sh., Chilonzor t., Bunyodkor ko'ch. 45");
            uzAutoSanoat.setState("1");
            uzAutoSanoat.setCondition(activeCondition);
            uzAutoSanoat = companyRepository.save(uzAutoSanoat);

            CompanyEntity aloqaBank = new CompanyEntity();
            aloqaBank.setName("Aloqabank");
            aloqaBank.setInn("201234567");
            aloqaBank.setAddress("Toshkent sh., Mirobod t., Amir Temur ko'ch. 107/3");
            aloqaBank.setState("1");
            aloqaBank.setCondition(activeCondition);
            aloqaBank = companyRepository.save(aloqaBank);

            CompanyEntity telekom = new CompanyEntity();
            telekom.setName("Uzbektelecom");
            telekom.setInn("202345678");
            telekom.setAddress("Toshkent sh., Yunusobod t., Amir Temur ko'ch. 63");
            telekom.setState("1");
            telekom.setCondition(activeCondition);
            telekom = companyRepository.save(telekom);

            CompanyEntity foodCompany = new CompanyEntity();
            foodCompany.setName("Toshkent Non Zavodi");
            foodCompany.setInn("203456789");
            foodCompany.setAddress("Toshkent sh., Sergeli t., Yangi Sergeli MFY");
            foodCompany.setState("1");
            foodCompany.setCondition(activeCondition);
            foodCompany = companyRepository.save(foodCompany);

            CompanyEntity textileCompany = new CompanyEntity();
            textileCompany.setName("Margilan Yog'och Zavodi");
            textileCompany.setInn("204567890");
            textileCompany.setAddress("Farg'ona viloyati, Margilan sh., Ipak Yo'li ko'ch. 12");
            textileCompany.setState("1");
            textileCompany.setCondition(activeCondition);
            textileCompany = companyRepository.save(textileCompany);

            // Jihozlar turlari
            EquipmentTypeEntity computerType = new EquipmentTypeEntity();
            computerType.setName("Kompyuter");
            computerType.setDescription("Ish stoli kompyuterlari va noutbuklar");
            computerType.setState(1);
            computerType = equipmentTypeRepository.save(computerType);

            EquipmentTypeEntity printerType = new EquipmentTypeEntity();
            printerType.setName("Printer");
            printerType.setDescription("Turli xil printerlar");
            printerType.setState(1);
            printerType = equipmentTypeRepository.save(printerType);

            EquipmentTypeEntity phoneType = new EquipmentTypeEntity();
            phoneType.setName("Telefon");
            phoneType.setDescription("Statsionar va mobil telefonlar");
            phoneType.setState(1);
            phoneType = equipmentTypeRepository.save(phoneType);

            EquipmentTypeEntity furnitureType = new EquipmentTypeEntity();
            furnitureType.setName("Mebel");
            furnitureType.setDescription("Ish joyi uchun mebel");
            furnitureType.setState(1);
            furnitureType = equipmentTypeRepository.save(furnitureType);

            EquipmentTypeEntity vehicleType = new EquipmentTypeEntity();
            vehicleType.setName("Transport");
            vehicleType.setDescription("Avtomobillar va boshqa transport vositalari");
            vehicleType.setState(1);
            vehicleType = equipmentTypeRepository.save(vehicleType);

            // Bo'limlar
            SectionEntity itSection = new SectionEntity();
            itSection.setName("IT bo'limi");
            itSection.setDescription("Axborot texnologiyalari bo'limi");
            itSection.setCompany(uzAutoSanoat);
            itSection.setState(1);
            itSection = sectionRepository.save(itSection);

            SectionEntity hrSection = new SectionEntity();
            hrSection.setName("Kadrlar bo'limi");
            hrSection.setDescription("Kadrlar bilan ishlash bo'limi");
            hrSection.setCompany(uzAutoSanoat);
            hrSection.setState(1);
            hrSection = sectionRepository.save(hrSection);

            SectionEntity financeSection = new SectionEntity();
            financeSection.setName("Moliya bo'limi");
            financeSection.setDescription("Moliyaviy hisob-kitob bo'limi");
            financeSection.setCompany(aloqaBank);
            financeSection.setState(1);
            financeSection = sectionRepository.save(financeSection);

            SectionEntity salesSection = new SectionEntity();
            salesSection.setName("Savdo bo'limi");
            salesSection.setDescription("Savdo va marketing bo'limi");
            salesSection.setCompany(foodCompany);
            salesSection.setState(1);
            salesSection = sectionRepository.save(salesSection);

            // Jihozlar
            EquipmentEntity dell = new EquipmentEntity();
            dell.setName("Dell Latitude 5520");
            dell.setDescription("Intel Core i5, 16GB RAM, 512GB SSD");
            dell.setSerialNumber("DL5520-001");
            dell.setInventoryNumber("INV-2024-001");
            dell.setEquipmentType(computerType);
            dell.setCompany(uzAutoSanoat);
            dell.setState(1);
            equipmentRepository.save(dell);

            EquipmentEntity hpPrinter = new EquipmentEntity();
            hpPrinter.setName("HP LaserJet Pro M404dn");
            hpPrinter.setDescription("Oq-qora lazer printer");
            hpPrinter.setSerialNumber("HPLJ-404-002");
            hpPrinter.setInventoryNumber("INV-2024-002");
            hpPrinter.setEquipmentType(printerType);
            hpPrinter.setCompany(uzAutoSanoat);
            hpPrinter.setState(1);
            equipmentRepository.save(hpPrinter);

            EquipmentEntity lenovo = new EquipmentEntity();
            lenovo.setName("Lenovo ThinkPad X1");
            lenovo.setDescription("Intel Core i7, 32GB RAM, 1TB SSD");
            lenovo.setSerialNumber("LTX1-003");
            lenovo.setInventoryNumber("INV-2024-003");
            lenovo.setEquipmentType(computerType);
            lenovo.setCompany(aloqaBank);
            lenovo.setState(1);
            equipmentRepository.save(lenovo);

            EquipmentEntity canon = new EquipmentEntity();
            canon.setName("Canon ImageRUNNER");
            canon.setDescription("Ko'p funktsiyali printer");
            canon.setSerialNumber("CIR-2525-004");
            canon.setInventoryNumber("INV-2024-004");
            canon.setEquipmentType(printerType);
            canon.setCompany(telekom);
            canon.setState(1);
            equipmentRepository.save(canon);

            EquipmentEntity macbook = new EquipmentEntity();
            macbook.setName("MacBook Pro 14");
            macbook.setDescription("M2 Pro chip, 16GB RAM, 512GB SSD");
            macbook.setSerialNumber("MBP14-005");
            macbook.setInventoryNumber("INV-2024-005");
            macbook.setEquipmentType(computerType);
            macbook.setCompany(aloqaBank);
            macbook.setState(1);
            equipmentRepository.save(macbook);

            // Xodimlar (O'zbek ismlari bilan)
            EmployeeEntity sardor = new EmployeeEntity();
            sardor.setCompany(uzAutoSanoat);
            sardor.setFirstName("Sardor");
            sardor.setMiddleName("Ismoilovich");
            sardor.setLastName("Karimov");
            sardor.setGenderCode("Erkak");
            sardor.setBirthDate(new Date(90, 4, 15));
            sardor.setSection(itSection);
            sardor.setState("1");
            employeeRepository.save(sardor);

            EmployeeEntity dilnoza = new EmployeeEntity();
            dilnoza.setCompany(uzAutoSanoat);
            dilnoza.setFirstName("Dilnoza");
            dilnoza.setMiddleName("Rustamovna");
            dilnoza.setLastName("Rahimova");
            dilnoza.setGenderCode("Ayol");
            dilnoza.setBirthDate(new Date(92, 7, 23));
            dilnoza.setSection(hrSection);
            dilnoza.setState("1");
            employeeRepository.save(dilnoza);

            EmployeeEntity jamshid = new EmployeeEntity();
            jamshid.setCompany(aloqaBank);
            jamshid.setFirstName("Jamshid");
            jamshid.setMiddleName("Akramovich");
            jamshid.setLastName("Toshmatov");
            jamshid.setGenderCode("Erkak");
            jamshid.setBirthDate(new Date(88, 2, 10));
            jamshid.setSection(financeSection);
            jamshid.setState("1");
            employeeRepository.save(jamshid);

            EmployeeEntity kamola = new EmployeeEntity();
            kamola.setCompany(aloqaBank);
            kamola.setFirstName("Kamola");
            kamola.setMiddleName("Alisher qizi");
            kamola.setLastName("Yusupova");
            kamola.setGenderCode("Ayol");
            kamola.setBirthDate(new Date(95, 9, 5));
            kamola.setSection(financeSection);
            kamola.setState("1");
            employeeRepository.save(kamola);

            EmployeeEntity bobur = new EmployeeEntity();
            bobur.setCompany(foodCompany);
            bobur.setFirstName("Bobur");
            bobur.setMiddleName("Shavkatovich");
            bobur.setLastName("Umarov");
            bobur.setGenderCode("Erkak");
            bobur.setBirthDate(new Date(91, 11, 18));
            bobur.setSection(salesSection);
            bobur.setState("1");
            employeeRepository.save(bobur);

            EmployeeEntity guzal = new EmployeeEntity();
            guzal.setCompany(foodCompany);
            guzal.setFirstName("Guzal");
            guzal.setMiddleName("Shoirovna");
            guzal.setLastName("Nasriddinova");
            guzal.setGenderCode("Ayol");
            guzal.setBirthDate(new Date(93, 3, 28));
            guzal.setSection(salesSection);
            guzal.setState("1");
            employeeRepository.save(guzal);

            EmployeeEntity aziz = new EmployeeEntity();
            aziz.setCompany(telekom);
            aziz.setFirstName("Aziz");
            aziz.setMiddleName("Dilshodovich");
            aziz.setLastName("Abdullayev");
            aziz.setGenderCode("Erkak");
            aziz.setBirthDate(new Date(89, 6, 7));
            aziz.setState("1");
            employeeRepository.save(aziz);

            EmployeeEntity madina = new EmployeeEntity();
            madina.setCompany(textileCompany);
            madina.setFirstName("Madina");
            madina.setMiddleName("Ulug'bekovna");
            madina.setLastName("Tursunova");
            madina.setGenderCode("Ayol");
            madina.setBirthDate(new Date(94, 1, 14));
            madina.setState("1");
            employeeRepository.save(madina);

        }
    }
}
