package com.kevin.incomeexpence

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (
    context: Context?
) : SQLiteOpenHelper(context, "incomeexpence.db", null, 1 ) {

    var TABLE_NAME = "transaction"

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql = "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT, amt INTEGER, category TEXT, notes TEXT, isexpence INTEGER)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addAmount(transactionModel: TransactionModel){

        var db = writableDatabase
        var values = ContentValues().apply {
            transactionModel.apply {
                put("amt",amt)
                put("category",category)
                put("notes",notes)
                put("isexpence",isexpence)
            }
        }
        db.insert(TABLE_NAME,null, values)
    }

    fun getTransaction(): ArrayList<TransactionModel> {
        var translist = ArrayList<TransactionModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count-1) {

            var id = cursor.getInt(0)
            var amt = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isexpence = cursor.getInt(4)

            var model = TransactionModel(id, amt, category, note, isexpence)
            translist.add(model)
            cursor.moveToNext()
        }
        return translist
    }
}
