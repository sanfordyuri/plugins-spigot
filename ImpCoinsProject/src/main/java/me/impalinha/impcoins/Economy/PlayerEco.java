package me.impalinha.impcoins.Economy;

import java.util.UUID;

public class PlayerEco {

    private UUID uuid;
    private Double amount;

    public PlayerEco(UUID uuid, Double amount) {
        this.uuid = uuid;
        this.amount = amount;
    }


    public Double getAmount() {
        return amount;
    }


    public void setAmount(Double amount) {
        if(amount > 0) {
            this.amount = amount;
        }
    }

    public void addAmount(Double amount) {
        if(amount > 0) {
            this.amount += amount;
        }
    }

    public void removeAmount(Double amount) {
        if ((amount > 0) && this.amount >= amount) {
            this.amount -= amount;
        }
    }

    public boolean sendAmount(Double amount, PlayerEco eco) {
        if(amount > 0) {
            if(this.amount >= amount) {
                this.amount = this.amount - amount;
                eco.setAmount(eco.getAmount() + amount);
                return true;
            }
        }

        return false;
    }

}
