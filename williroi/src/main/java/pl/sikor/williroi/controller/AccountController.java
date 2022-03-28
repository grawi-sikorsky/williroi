package pl.sikor.williroi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.service.AccountService;
import pl.sikor.williroi.service.HotspotService;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final HotspotService hotspotService;

    public AccountController(AccountService accountService, HotspotService hotspotService) {
        this.accountService = accountService;
        this.hotspotService = hotspotService;
    }

    @GetMapping("/")
    public void staradupa() {
        accountService.getAccountFromAPI();
        hotspotService.getHotspotFromApi("1126G1HMj1zM9WbDPfXxeheFUU6HG4ffFh7c97sHWvaS5ahRTSTM");
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }

    @GetMapping("/a")
    public void hotspots() {
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }

}
