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
        hotspotService.getHotspotFromApi("11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa");
        accountService.getAccountHotspotsFromApi("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }

}
