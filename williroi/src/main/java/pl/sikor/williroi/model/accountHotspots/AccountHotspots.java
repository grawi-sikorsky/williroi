package pl.sikor.williroi.model.accountHotspots;

public class AccountHotspots {
    
}

public class Status{
    public Date timestamp;
    public String online;
    public ArrayList<String> listen_addrs;
    public int height;
}

public class Geocode{
    public String short_street;
    public String short_state;
    public String short_country;
    public String short_city;
    public String long_street;
    public String long_state;
    public String long_country;
    public String long_city;
    public String city_id;
}

public class Datum{
    public double lng;
    public double lat;
    public Date timestamp_added;
    public Status status;
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
    public Geocode geocode;
    public int gain;
    public int elevation;
    public int block_added;
    public int block;
    public String address;
}

public class Root{
    public ArrayList<Datum> data;
}