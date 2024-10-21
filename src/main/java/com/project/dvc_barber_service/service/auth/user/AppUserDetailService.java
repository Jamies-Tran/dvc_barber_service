package com.project.dvc_barber_service.service.auth.user;

import com.project.dvc_barber_service.dto.account.Account;
import com.project.dvc_barber_service.dto.account.action.AccountFindByPhoneAction;
import com.project.dvc_barber_service.service.account.AccountQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserDetailService implements UserDetailsService {
    @NonNull AccountQueryService queryService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<Account> tryToGetAccount = queryService.findByPhone(AccountFindByPhoneAction.buildFrom(phone));
        if(tryToGetAccount.isPresent()) {
            Account account = tryToGetAccount.get();
            return User.builder()
                    .username(account.phone())
                    .password(account.password())
                    .disabled(false)
                    .authorities(account.getAuthorities())
                    .build();
        }


        throw new RuntimeException();
    }
}
