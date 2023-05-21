package com.kevin.incomeexpence.Model

import java.util.Date

class TransactionModel {

    var id = 0
    var amount = 0
    lateinit var category: String
    lateinit var note: String
    var isExpence = 0

    constructor(id: Int, amount: Int, category: String, note: String, isExpence: Int) {
        this.id = id
        this.amount = amount
        this.category = category
        this.note = note
        this.isExpence = isExpence
    }
}