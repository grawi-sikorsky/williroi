package pl.sikor.williroi.model;

import java.util.ArrayList;

import javax.persistence.Entity;

import pl.sikor.williroi.model.heliumAPI.account.AccountModel;
import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;

@Entity
public class User {
    String username;
    String password;
    String apiAccountHash;
    AccountModel apiAccount;
    ArrayList<Hotspot> hotspots;
}
