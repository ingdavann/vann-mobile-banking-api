//package co.istad.vannbankingapi.util;
//
//import co.istad.vannbankingapi.domain.Role;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataInit {
//
//    private final RoleRepository roleRepository;
//
//    @PostConstruct
//    void init() {
//
//        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
//        if (roleRepository.count() < 1) {
//            Role user = new Role();
//            user.setName("USER");
//
//            Role customer = new Role();
//            customer.setName("CUSTOMER");
//
//            Role staff = new Role();
//            staff.setName("STAFF");
//
//            Role admin = new Role();
//            admin.setName("ADMIN");
//
//            roleRepository.saveAll(
//                    List.of(user, customer, staff, admin)
//            );
//        }
//
//    }
//}
//
