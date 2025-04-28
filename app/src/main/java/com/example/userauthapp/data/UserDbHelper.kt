package com.example.userauthapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private const val DB_NAME    = "UserAuth.db"
private const val DB_VERSION = 1

class UserDbHelper(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val sqlCreate = """
            CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (
              ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
              ${UserContract.UserEntry.COL_NAME} TEXT NOT NULL,
              ${UserContract.UserEntry.COL_EMAIL} TEXT NOT NULL UNIQUE,
              ${UserContract.UserEntry.COL_PASS} TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Se for necessário alterar esquema no futuro:
        db.execSQL("DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}")
        onCreate(db)
    }
}