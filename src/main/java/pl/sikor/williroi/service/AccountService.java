package pl.sikor.williroi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.sikor.williroi.respository.UserRepository;

// account 12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9

@Service
public class AccountService {

    private String apiAddress = "https://api.helium.io/v1/";
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final UserRepository userRepository;
    
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
