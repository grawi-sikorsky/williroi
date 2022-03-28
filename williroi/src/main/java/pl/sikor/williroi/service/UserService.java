package pl.sikor.williroi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.respository.UserRepository;

@Service
public class UserService {
    
    private final AccountService accountService;
    private final HotspotService hotspotService;
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    /* CONSTRUCTOR */
    public UserService(AccountService accountService, HotspotService hotspotService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }



    /* ADD USER METHOD */
    public UserModel addNewUser(String helium_account_address) throws RuntimeException{
        if(userRepository.findByApiAccountHash(helium_account_address) == null){

            UserModel user = new UserModel();
            user.setApiAccount(accountService.getAccountFromAPI(helium_account_address));
            user.setHntAccount(helium_account_address);
            user.setHotspots(accountService.getAccountHotspotsFromApi(helium_account_address));

            userRepository.save(user);
            return user;

        } else {
            logger.error("USER EXISTS..");
            throw new RuntimeException();
        }
    }

    /* GET USER METHOD */
    public UserModel getUser(String accountID) throws RuntimeException{
        if(userRepository.findByUsername(accountID) != null){
            return userRepository.findByUsername(accountID);
        } else {
            logger.error("USER DOESNT EXISTS..");
            throw new RuntimeException();
        }
    }


    public void getUserDataFromAPI() throws RuntimeException {
        accountService.getAccountFromAPI("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }
}
