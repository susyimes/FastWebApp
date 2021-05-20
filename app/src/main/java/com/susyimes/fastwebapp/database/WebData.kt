package com.susyimes.fastwebapp.database

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class WebData {
    @Id
    var id: Long = 0
    var url: String = ""
}