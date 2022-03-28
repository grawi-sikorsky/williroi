package pl.sikor.williroi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.sikor.williroi.model.heliumAPI.account.AccountModel;
import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;

@Entity
public class UserModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String hntAccount;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private AccountModel apiAccount;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Hotspot> hotspots;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHntAccount() {
        return hntAccount;
    }
    public void setHntAccount(String hntAccount) {
        this.hntAccount = hntAccount;
    }
    public AccountModel getApiAccount() {
        return apiAccount;
    }
    public void setApiAccount(AccountModel apiAccount) {
        this.apiAccount = apiAccount;
    }
    public List<Hotspot> getHotspots() {
        return hotspots;
    }
    public void setHotspots(List<Hotspot> hotspots) {
        this.hotspots = hotspots;
    }

    
}
