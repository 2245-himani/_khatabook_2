package com.kevin.incomeexpence

class TransactionModel {

    var id = 0
    var amt = 0
    lateinit var category: String
    lateinit var notes: String
    var isexpence = 0

    constructor(id: Int, amt: Int, category: String, notes: String, isexpence: Int) {
        this.id = id
        this.amt = amt
        this.category = category
        this.notes = notes
        this.isexpence = isexpence
    }
}