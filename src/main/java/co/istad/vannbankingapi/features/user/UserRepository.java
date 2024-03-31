package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalId);
    boolean existsByStudentCardId(String studentIdCard);
}