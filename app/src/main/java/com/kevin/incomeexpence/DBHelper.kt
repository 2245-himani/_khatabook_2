package com.kevin.incomeexpence

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kevin.incomeexpence.Model.TransactionModel

class DBHelper (
    context: Context?
) : SQLiteOpenHelper(context, "incomeexpence.db", null, 1 ) {

    var TABLE_NAME = "trans"
    var Id = "id"
    var AMOUNT = "amount"
    var CATEGORY = "category"
    var NOTE = "note"
    var IS_EXPENCE = "isExpence"
    var DATE = "date"
    var MONTH = "month"
    var YEAR = "year"

    override fun onCreate(p0: SQLiteDatabase?) {
        var que = "CREATE TABLE $TABLE_NAME($Id INTEGER PRIMARY KEY AUTOINCREMENT ,$AMOUNT INTEGER , $CATEGORY TEXT, $NOTE TEXT, $IS_EXPENCE INTEGER, $DATE TEXT, $MONTH TEXT, $YEAR TEXT)"
        p0?.execSQL(que)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addAmount(transactionModel: TransactionModel){

        var db = writableDatabase
        var values = ContentValues().apply {
            transactionModel.apply {
               put(AMOUNT,amount)
               put(CATEGORY,category)
               put(NOTE,note)
               put(IS_EXPENCE,isExpence)
                put(DATE,date)
                put(MONTH,month)
                put(YEAR,year)
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
            var amount = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isexpence = cursor.getInt(4)
            var date = cursor.getString(5)
            var month = cursor.getString(6)
            var year = cursor.getString(7)

            var model = TransactionModel(id, amount, category, note, isexpence , date, month , year)
            translist.add(model)
            cursor.moveToNext()
        }
        return translist
    }
    fun updateTrans(transactionModel: TransactionModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            transactionModel.apply {
                put(AMOUNT,amount)
                put(CATEGORY,category)
                put(NOTE,note)
                put(IS_EXPENCE,isExpence)
                put(DATE,date)
                put(MONTH,month)
                put(YEAR,year)
            }
        }
        db.update(TABLE_NAME,values,"id=${transactionModel.id}",null)
    }
    fun deleteTrans(id: Int) {
        var db = writableDatabase
        db.delete(TABLE_NAME,"id=$id",null)
    }
}
