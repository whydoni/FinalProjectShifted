package org.example.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "mutasi")
public class Mutasi {

    @Id
    @Column(name = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp waktuMutasi;

    @Column(name = "accountnumber")
    String accountnumber;

    @Column(name = "details")
    String details;

    @Column(name = "type")
    String type;

    @Column(name = "nominal")
    Long nominal;

    @Column(name = "balance")
    Long balance;

    public Timestamp getWaktuMutasi() {
        return waktuMutasi;
    }

    public void setWaktuMutasi(Timestamp waktuMutasi) {
        this.waktuMutasi = waktuMutasi;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
