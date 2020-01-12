package by.laguta.skryaga.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeRate implements Comparable<ExchangeRate> {

    private LocalDateTime date;
    private String currencyType;
    private String bankName;
    private String bankAddress;
    private Double buyRate;
    private Double sellRate;


    public ExchangeRate(LocalDateTime date, String currencyType, String bankName, String bankAddress, Double buyRate, Double sellRate) {
        this.date = date;
        this.currencyType = currencyType;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    @Override
    public int compareTo(ExchangeRate another) {
        return sellRate.compareTo(another.sellRate);
    }
}
