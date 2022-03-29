package pl.sikor.williroi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.service.AccountService;
import pl.sikor.williroi.service.HotspotService;
import pl.sikor.williroi.service.heliumAPI.ApiService;

@RestController
@RequestMapping("/user/{username}/account")
public class AccountController {

    private final ApiService apiService;
    private final AccountService accountService;
    private final HotspotService hotspotService;

    public AccountController(ApiService apiService, AccountService accountService, HotspotService hotspotService) {
        this.apiService = apiService;
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }

    @GetMapping("/{hntaddress}")
    public void getHeliumAccount(@PathVariable String username, @PathVariable String hntaddress) {
        apiService.getAccountFromAPI(hntaddress);
        apiService.getAccountHotspotsFromApi(hntaddress);
    }

    @GetMapping("/a")
    public void hotspots() {
        apiService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }

    @GetMapping("/rewards")
    public void hotspotrewards() {
        //apiService.getHotspotRewardsFromAPI("1126G1HMj1zM9WbDPfXxeheFUU6HG4ffFh7c97sHWvaS5ahRTSTM");
    }

}
