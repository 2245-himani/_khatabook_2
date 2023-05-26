package com.kevin.incomeexpence.Model

import java.time.Month
import java.time.Year
import java.util.Date

class TransactionModel {

    var id = 0
    var amount = 0
    lateinit var category: String
    lateinit var note: String
    var isExpence = 0
    lateinit var date: String
    lateinit var month: String
    lateinit var year: String

    constructor(id: Int, amount: Int, category: String, note: String, isExpence: Int, date: String, month: String, year: String) {
        this.id = id
        this.amount = amount
        this.category = category
        this.note = note
        this.isExpence = isExpence
        this.date = date
        this.month = month
        this.year = year
    }
}