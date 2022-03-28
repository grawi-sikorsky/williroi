package pl.sikor.williroi.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final AccountService accountService;
    private final HotspotService hotspotService;

    public UserService(AccountService accountService, HotspotService hotspotService) {
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }

    public void getUserDataFromAPI(){
        accountService.getAccountFromAPI();
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }
}
