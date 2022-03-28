package pl.sikor.williroi.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pl.sikor.williroi.model.heliumAPI.account.AccountModel;
import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String apiAccountHash;
    AccountModel apiAccount;
    ArrayList<Hotspot> hotspots;
}
