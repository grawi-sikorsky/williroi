package pl.sikor.williroi.model.heliumAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "data")
@Entity
public class AccountModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acc_reward24;
    private String acc_reward7d;
    private String acc_reward30d;
    private String acc_reward_lifetime;

    private int validator_count;
    private int staked_balance;
    private int speculative_sec_nonce;
    private int speculative_nonce;
    private int sec_nonce;
    private int sec_balance;
    private int nonce;
    private int hotspot_count;
    private int dc_nonce;
    private int dc_balance;
    private int block;
    private int balance;
    private String address;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getValidator_count() {
        return validator_count;
    }
    public void setValidator_count(int validator_count) {
        this.validator_count = validator_count;
    }
    public int getStaked_balance() {
        return staked_balance;
    }
    public void setStaked_balance(int staked_balance) {
        this.staked_balance = staked_balance;
    }
    public int getSpeculative_sec_nonce() {
        return speculative_sec_nonce;
    }
    public void setSpeculative_sec_nonce(int speculative_sec_nonce) {
        this.speculative_sec_nonce = speculative_sec_nonce;
    }
    public int getSpeculative_nonce() {
        return speculative_nonce;
    }
    public void setSpeculative_nonce(int speculative_nonce) {
        this.speculative_nonce = speculative_nonce;
    }
    public int getSec_nonce() {
        return sec_nonce;
    }
    public void setSec_nonce(int sec_nonce) {
        this.sec_nonce = sec_nonce;
    }
    public int getSec_balance() {
        return sec_balance;
    }
    public void setSec_balance(int sec_balance) {
        this.sec_balance = sec_balance;
    }
    public int getNonce() {
        return nonce;
    }
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
    public int getHotspot_count() {
        return hotspot_count;
    }
    public void setHotspot_count(int hotspot_count) {
        this.hotspot_count = hotspot_count;
    }
    public int getDc_nonce() {
        return dc_nonce;
    }
    public void setDc_nonce(int dc_nonce) {
        this.dc_nonce = dc_nonce;
    }
    public int getDc_balance() {
        return dc_balance;
    }
    public void setDc_balance(int dc_balance) {
        this.dc_balance = dc_balance;
    }
    public int getBlock() {
        return block;
    }
    public void setBlock(int block) {
        this.block = block;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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