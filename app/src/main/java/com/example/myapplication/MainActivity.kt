package com.example.myapplication


open class Account(val accountNumber: String, val ownerName: String) {
    private var balance: Double = 0.0

    fun getBalance(): Double {
        return balance
    }

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance = balance + amount
            println("Deposited $$amount. New balance: $$balance")
        } else {
            println("Deposit amount must be more than 0.")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance = balance - amount
            println("Withdrew $$amount. New balance: $$balance")
        } else {
            println("Cannot withdraw. Not enough balance or invalid amount.")
        }
    }

    fun printInfo() {
        println("Account Number: $accountNumber")
        println("Owner Name: $ownerName")
        println("Balance: $$balance")
    }
}

class SavingsAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        if (amount > 500) {
            println("You can’t withdraw more than $500 in one transaction.")
        } else {
            super.withdraw(amount)
        }
    }
}

class VIPAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {

    private val transactionFee = 2.0

    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        if (getBalance() >= totalAmount) {
            println("Withdrawing $$amount (Transaction fee: $$transactionFee)")
            super.withdraw(totalAmount)
        } else {
            println("Not enough balance to withdraw $$amount plus fee of $$transactionFee.")
        }
    }
}

fun main() {
    // Create a SavingsAccount
    val acc1 = SavingsAccount("S101", "giorgi g.")
    acc1.deposit(1000.0)
    acc1.withdraw(300.0)
    acc1.withdraw(600.0)
    acc1.printInfo()

    println("---------------")

    // Create a VIPAccount
    val acc2 = VIPAccount("V202", "Mariami A.")
    acc2.deposit(1000.0)
    acc2.withdraw(50.0)
    acc2.printInfo()

    println("---------------")


    val accounts: List<Account> = listOf(acc1, acc2)
    for (account in accounts) {
        account.deposit(50.0)
        account.printInfo()
        println("---------------")
    }
}