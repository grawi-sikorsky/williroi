package pl.sikor.williroi.model;

import pl.sikor.williroi.model.heliumAPI.Hotspot;

public class HotspotDTO{

    private String address;
    private String price;
    private String roi;
    private String roi_days_left;
    private String roi_days_past;
    private String rewards_24;
    private String rewards_7d;
    private String rewards_30d;
    private String rewards_lifetime;

    private Hotspot apiHotspot;

    public String getAddress() {
        return address;
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
    public Hotspot getApiHotspot() {
        return apiHotspot;
    }
    public void setApiHotspot(Hotspot apiHotspot) {
        this.apiHotspot = apiHotspot;
    }
    
    
}