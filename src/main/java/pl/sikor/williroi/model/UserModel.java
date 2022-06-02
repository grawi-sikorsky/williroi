package pl.sikor.williroi.model;

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

import pl.sikor.williroi.model.heliumAPI.AccountModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;

@Entity
public class UserModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String hntAccount;

    private String acc_reward24;
    private String acc_reward7d;
    private String acc_reward30d;
    private String acc_reward_lifetime;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    private AccountModel accountModel;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Hotspot> hotspots;


    public UserModel() {
    }

    public UserModel(UserModel user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.hntAccount = user.getHntAccount();
    }

    public UserModel toEntity() {
        UserModel user = new UserModel();

        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setHntAccount(this.hntAccount);

        return user;
    }


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
    public AccountModel getAccountModel() {
        return accountModel;
    }
    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public List<Hotspot> getHotspots() {
        return hotspots;
    }

    public void setHotspots(List<Hotspot> hotspots) {
        this.hotspots = hotspots;
    }

    public String getAcc_reward24() {
        return acc_reward24;
    }

    public void setAcc_reward24(String acc_reward24) {
        this.acc_reward24 = acc_reward24;
    }

    public String getAcc_reward7d() {
        return acc_reward7d;
    }

    public void setAcc_reward7d(String acc_reward7d) {
        this.acc_reward7d = acc_reward7d;
    }

    public String getAcc_reward30d() {
        return acc_reward30d;
    }

    public void setAcc_reward30d(String acc_reward30d) {
        this.acc_reward30d = acc_reward30d;
    }

    public String getAcc_reward_lifetime() {
        return acc_reward_lifetime;
    }

    public void setAcc_reward_lifetime(String acc_reward_lifetime) {
        this.acc_reward_lifetime = acc_reward_lifetime;
    }

    
}
