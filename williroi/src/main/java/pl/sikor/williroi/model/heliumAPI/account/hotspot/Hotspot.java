package pl.sikor.williroi.model.heliumAPI.account.hotspot;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "data")
public class Hotspot {
    // pola wlasne
    public String price;
    public String roi;
    public String roi_days_left;
    public String roi_days_past;

    public String rewards_24;
    public String rewards_7d;
    public String rewards_30d;

    // pola z API
    public double lng;
    public double lat;
    public Date timestamp_added;
    //public Status status;
    public double reward_scale;
    public String payer;
    public String owner;
    public int nonce;
    public String name;
    public String mode;
    public String location_hex;
    public String location;
    public int last_poc_challenge;
    public int last_change_block;
    //public Geocode geocode;
    public Integer gain;
    public Integer elevation;
    public int block_added;
    public int block;
    public String address;
}