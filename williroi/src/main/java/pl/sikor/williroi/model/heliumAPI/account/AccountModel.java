package pl.sikor.williroi.model.heliumAPI.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@JsonRootName(value = "data")
public class AccountModel {
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
    @JsonProperty("balance")
    public int balance;
    public String address;

    ObjectMapper aa = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
}