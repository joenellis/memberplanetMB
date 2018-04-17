package com.ellis.memberplanet.object;

/**
 * Created by joenellis on 23/08/2017.
 */

public class Trans {
    private String userid;
    private String transid;
    private String TransactionId;
    private String Description;
    private String Amount;
    private String Charges;
    private String ClientReference;
    private String date_created;


    public Trans(String userid, String transid, String transactionid, String message, String amount, String charge, String clientreference, String date) {
        this.userid = userid;
        this.transid = transid;
        this.TransactionId = transactionid;
        this.Description = message;
        this.Amount = amount;
        this.Charges = charge;
        this.ClientReference = clientreference;
        this.date_created = date;
    }

    public String getUserid() {
        return userid;
    }

    public String getTransid() {
        return transid;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public String getDescription() {
        return Description;
    }

    public String getAmount() {
        return Amount;
    }

    public String getCharge() {
        return Charges;
    }

    public String getClientReference() {
        return ClientReference;
    }

    public String getDate_created() {
        return date_created;
    }

}
