package com.example.userauthapp.data

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val COL_NAME   = "name"
        const val COL_EMAIL  = "email"
        const val COL_PASS   = "password"
    }
}