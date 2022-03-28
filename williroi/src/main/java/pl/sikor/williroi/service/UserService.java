package pl.sikor.williroi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.respository.UserRepository;

@Service
public class UserService {
    
    private final AccountService accountService;
    private final HotspotService hotspotService;
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public UserService(AccountService accountService, HotspotService hotspotService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }

    /* ADD USER METHOD */
    public void addNewUser(UserModel userModel){
        if(userRepository.findByUsername(userModel.getUsername()) == null){
            userRepository.save(userModel);
        } else {
            logger.error("USER EXISTS..");
        }
    }

    /* GET USER METHOD */


    public void getUserDataFromAPI(){
        accountService.getAccountFromAPI();
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }
}
