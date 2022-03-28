package pl.sikor.williroi.model.heliumAPI.account;

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

    public int validator_count;
    public int staked_balance;
    public int speculative_sec_nonce;
    public int speculative_nonce;
    public int sec_nonce;
    public int sec_balance;
    public int nonce;
    public int hotspot_count;
    public int dc_nonce;
    public int dc_balance;
    public int block;
    public int balance;
    public String address;
}