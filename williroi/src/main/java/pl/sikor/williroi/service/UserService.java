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
    
    /* CONSTRUCTOR */
    public UserService(AccountService accountService, HotspotService hotspotService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }


    /* ADD USER METHOD */
    public UserModel addNewUser(UserModel userModel) throws RuntimeException{
        if(userRepository.findByUsername(userModel.getUsername()) == null){

            UserModel user = new UserModel(userModel);

            // user.setHntAccount(userModel.getHntAccount());
            // user.setApiAccount(accountService.getAccountFromAPI(hntAccount));
            // user.setHotspots(accountService.getAccountHotspotsFromApi(hntAccount));

            userRepository.save(user);
            return user;

        } else {
            logger.error("USER EXISTS..");
            throw new RuntimeException();
        }
    }

    /* GET USER METHOD */
    public UserModel getUser(String username) throws RuntimeException{
        if(userRepository.findByUsername(username) != null){
            return userRepository.findByUsername(username);
        } else {
            logger.error("USER DOESNT EXISTS..");
            throw new RuntimeException();
        }
    }


    /* ADD USER HELIUM ACCOUNT */
    public UserModel addHeliumAccount(UserModel userModel){

        if(userRepository.findByUsername(userModel.getUsername()) != null){
            UserModel user = userRepository.findByUsername(userModel.getUsername());
            user.setHntAccount(userModel.getHntAccount());
            user.setApiAccount(accountService.getAccountFromAPI(userModel.getHntAccount()));
            userRepository.save(user);
            return user;
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
