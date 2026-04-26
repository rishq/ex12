package org.hwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "balance")
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Пополнение на " + amount + ". Текущий баланс: " + this.balance);
        }
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("Ошибка: недостаточно средств на счете. Текущий баланс: " + this.balance);
        } else if (amount > 0) {
            this.balance -= amount;
            System.out.println("Снятие " + amount + ". Текущий баланс: " + this.balance);
        }
    }

    public void showBalance() {
        System.out.println("Текущий баланс счета " + this.accountNumber + ": " + this.balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
