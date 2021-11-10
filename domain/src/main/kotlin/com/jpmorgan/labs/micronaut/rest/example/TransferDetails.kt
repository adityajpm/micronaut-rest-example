package com.jpmorgan.labs.micronaut.rest.example

data class TransferDetails(val userId: UserId, val amount: Amount, val accountFrom: AccountNumber, val accountTo: AccountNumber){
    init {
        require(accountFrom != accountTo) { "Cannot transfer to the same account"}
    }
}


fun String.asAccountNumber() = AccountNumber(this)
