package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import com.jpmorgan.labs.micronaut.rest.example.TransferDetails
import com.jpmorgan.labs.micronaut.rest.example.asAccountNumber
import com.jpmorgan.labs.micronaut.rest.example.asAmount
import com.jpmorgan.labs.micronaut.rest.example.asUserId
import org.json.JSONObject

internal fun JSONObject.toTransferRequest(): TransferDetails {
    val userId = getString(Api.Resources.TransferDetails.Fields.userId)?.let(String::asUserId)!!
    val amount = getInt(Api.Resources.TransferDetails.Fields.amount).let(Int::asAmount)
    val accountFrom = getString(Api.Resources.TransferDetails.Fields.accountFrom)?.let(String::asAccountNumber)!!
    val accountTo = getString(Api.Resources.TransferDetails.Fields.accountTo)?.let(String::asAccountNumber)!!
    return TransferDetails(userId,  amount, accountFrom, accountTo)
}

internal fun TransferDetails.toJson() = JSONObject().apply {
    put(Api.Resources.TransferDetails.Fields.userId, userId.value)
    put(Api.Resources.TransferDetails.Fields.amount, amount.value)
    put(Api.Resources.TransferDetails.Fields.accountFrom, accountFrom.value)
    put(Api.Resources.TransferDetails.Fields.accountTo, accountTo.value)
}
