package com.parser.stripe2db.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class ListTransactionsPojo {
    @Getter
    @Setter
    private Root root;
}

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
class Datum{
    public String id;
    public String object;
    public int amount;
    public int available_on;
    public int created;
    public String currency;
    public String description;
    public Object exchange_rate;
    public int fee;
    public ArrayList<FeeDetail> fee_details;
    public int net;
    public String reporting_category;
    public String source;
    public String status;
    public String type;
}

class FeeDetail{
    public int amount;
    public Object application;
    public String currency;
    public String description;
    public String type;
}

class Root {
    public String object;
    public ArrayList<Datum> data;
    public boolean has_more;
    public String url;
}

