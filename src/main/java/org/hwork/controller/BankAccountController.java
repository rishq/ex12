package org.hwork.controller;

import org.hwork.entity.BankAccount;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {

    @PostMapping("/create")
    public BankAccount createAccount(@RequestBody BankAccount account) {
        return account;
    }

    @GetMapping("/test")
    public String testBankAccount() {
        BankAccount myAccount = new BankAccount();
        myAccount.setAccountNumber("123456789");
        myAccount.setOwnerName("Иван Иванов");
        myAccount.setBalance(1000.0);

        StringBuilder sb = new StringBuilder();
        sb.append("--- Тестирование банковского счета ---\n");
        
        myAccount.deposit(500.0);
        sb.append("Пополнение на 500. Баланс: ").append(myAccount.getBalance()).append("\n");
        
        myAccount.withdraw(200.0);
        sb.append("Снятие 200. Баланс: ").append(myAccount.getBalance()).append("\n");
        
        if (2000.0 > myAccount.getBalance()) {
            sb.append("Ошибка: недостаточно средств для снятия 2000\n");
        }
        
        return sb.toString();
    }
}
