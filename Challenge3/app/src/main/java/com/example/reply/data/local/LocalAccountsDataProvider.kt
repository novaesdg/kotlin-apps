package com.example.reply.data.local

import com.example.reply.R
import com.example.reply.data.Account

object LocalAccountsDataProvider {
    val allUserAccounts = mutableListOf(
        Account(
            1L,
            0L,
            "Jeff",
            "Hansen",
            "hikingfan@gmail.com",
            "hkngfan@outside.com",
            R.drawable.avatar_10,
            true
        )
    )

    private val allUserContactAccounts = listOf(
        Account(
            4L,
            1L,
            "Michael",
            "Jackson",
            "michael.jackson@gmail.com",
            "kingofpop@neverland.com",
            R.drawable.avatar_michael
        ),
        Account(
            5L,
            2L,
            "Lebron",
            "James",
            "lebron.james@gmail.com",
            "kingjames@lakers.com",
            R.drawable.avatar_james
        ),
        Account(
            6L,
            3L,
            "Davi",
            "Brito",
            "davi.brito@gmail.com",
            "calmacalabreso@campeao.com",
            R.drawable.avatar_davi
        ),
        Account(
            7L,
            4L,
            "Xuxa",
            "Meneghel",
            "xuxa.meneghel@gmail.com",
            "rainha@baixinhos.com",
            R.drawable.avatar_xuxa //
        )
    )

    fun getDefaultUserAccount() = allUserAccounts.first()

    fun isUserAccount(uid: Long): Boolean = allUserAccounts.any { it.uid == uid }

    fun getContactAccountByUid(accountId: Long): Account {
        return allUserContactAccounts.firstOrNull { it.id == accountId }
            ?: allUserContactAccounts.first()
    }
}