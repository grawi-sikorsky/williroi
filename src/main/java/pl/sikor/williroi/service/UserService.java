package pl.sikor.williroi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.sikor.williroi.exceptions.User.UserDoesntExistException;
import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.respository.UserRepository;
import pl.sikor.williroi.service.heliumAPI.ApiService;

@Service
public class UserService {
    private final ApiService apiService;
    private final AccountService accountService;
    private final HotspotService hotspotService;
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    /* CONSTRUCTOR */
    public UserService(ApiService apiService, AccountService accountService, HotspotService hotspotService, UserRepository userRepository) {
        this.apiService = apiService;
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }


    /* ADD USER METHOD */
    public UserModel addNewUser(UserModel userModel) throws RuntimeException{
        if(userRepository.findByUsername(userModel.getUsername()) == null){

            UserModel user = new UserModel(userModel);

            userRepository.save(user);
            return user;

        } else {
            logger.error("USER EXISTS..");
            throw new UserDoesntExistException("USER ALREADY EXISTS..");
        }
    }

    /* GET USER METHOD */
    public UserModel getUser(String username) throws UserDoesntExistException{
        if(userRepository.findByUsername(username) != null){ System.out.println("GET USER");
            return userRepository.findByUsername(username);
        } else {
            logger.error("USER DOESNT EXISTS..");
            throw new UserDoesntExistException("USER DOESNT EXISTS..");
        }
    }

    /* ADD USER HELIUM ACCOUNT */
    public UserModel addHeliumAccount(UserModel userModel){

        if(userRepository.findByUsername(userModel.getUsername()) != null){
            UserModel user = userRepository.findByUsername(userModel.getUsername());
            user.setHntAccount(userModel.getHntAccount());
            user.setApiAccount(apiService.getAccountFromAPI(userModel.getHntAccount()));
            userRepository.save(user);
            return user;
        } else {
            logger.error("USER DOESNT EXISTS..");
            throw new UserDoesntExistException("USER DOESNT EXISTS..");
        }
    }

    /* ADD USER HOTSPOTS FROM ACCOUNT */
    public UserModel addHeliumHotspots(UserModel userModel){

        if(userRepository.findByUsername(userModel.getUsername()) != null){
            UserModel user = userRepository.findByUsername(userModel.getUsername());
            user.setHotspots(apiService.getAccountHotspotsFromApi(user.getHntAccount()));
            userRepository.save(user);
            return user;
        } else {
            logger.error("USER DOESNT EXISTS..");
            throw new UserDoesntExistException("USER DOESNT EXISTS..");
        }
    }




    public void getUserDataFromAPI() throws RuntimeException {
        apiService.getAccountFromAPI("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
        apiService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }
}
