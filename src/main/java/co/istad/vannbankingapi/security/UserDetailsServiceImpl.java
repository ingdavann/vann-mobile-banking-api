package co.istad.vannbankingapi.security;

import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        // load user from database
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new UsernameNotFoundException("User has not found"));


        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        log.info("User: {}", user);
        return customUserDetails;
    }
}
