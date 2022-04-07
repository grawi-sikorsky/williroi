package pl.sikor.williroi.model.heliumAPI;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "data")
@Entity
public class Hotspot {

    // pola wlasne
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String price;
    private String roi;
    private String roi_days_left;
    private String roi_days_past;
    private String rewards_24;
    private String rewards_7d;
    private String rewards_30d;
    private String rewards_lifetime;

    // pola z API
    private double lng;
    private double lat;
    private Date timestamp_added;
    // private Status status;
    private double reward_scale;
    private String payer;
    private String owner;
    private int nonce;
    private String name;
    private String mode;
    private String location_hex;
    private String location;
    private int last_poc_challenge;
    private int last_change_block;
    // private Geocode geocode;
    private Integer gain;
    private Integer elevation;
    private int block_added;
    private int block;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getTimestamp_added() {
        return timestamp_added;
    }

    public void setTimestamp_added(Date timestamp_added) {
        this.timestamp_added = timestamp_added;
    }

    public double getReward_scale() {
        return reward_scale;
    }

    public void setReward_scale(double reward_scale) {
        this.reward_scale = reward_scale;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLocation_hex() {
        return location_hex;
    }

    public void setLocation_hex(String location_hex) {
        this.location_hex = location_hex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLast_poc_challenge() {
        return last_poc_challenge;
    }

    public void setLast_poc_challenge(int last_poc_challenge) {
        this.last_poc_challenge = last_poc_challenge;
    }

    public int getLast_change_block() {
        return last_change_block;
    }

    public void setLast_change_block(int last_change_block) {
        this.last_change_block = last_change_block;
    }

    public Integer getGain() {
        return gain;
    }

    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public int getBlock_added() {
        return block_added;
    }

    public void setBlock_added(int block_added) {
        this.block_added = block_added;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getRoi_days_left() {
        return roi_days_left;
    }

    public void setRoi_days_left(String roi_days_left) {
        this.roi_days_left = roi_days_left;
    }

    public String getRoi_days_past() {
        return roi_days_past;
    }

    public void setRoi_days_past(String roi_days_past) {
        this.roi_days_past = roi_days_past;
    }

    public String getRewards_24() {
        return rewards_24;
    }

    public void setRewards_24(String rewards_24) {
        this.rewards_24 = rewards_24;
    }

    public String getRewards_7d() {
        return rewards_7d;
    }

    public void setRewards_7d(String rewards_7d) {
        this.rewards_7d = rewards_7d;
    }

    public String getRewards_30d() {
        return rewards_30d;
    }

    public void setRewards_30d(String rewards_30d) {
        this.rewards_30d = rewards_30d;
    }

    public String getRewards_lifetime() {
        return rewards_lifetime;
    }

    public void setRewards_lifetime(String rewards_lifetime) {
        this.rewards_lifetime = rewards_lifetime;
    }

}