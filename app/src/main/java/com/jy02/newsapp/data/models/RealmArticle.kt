package com.jy02.newsapp.data.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmArticle: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.invoke()
    var author: String? = ""
    var content: String? = ""
    var description: String? = ""
    var publishedAt: String? = ""
    var source: RealmSource? = null
    var title: String?  = ""
    var url: String?  = ""
    var urlToImage: String?  = ""
}

class RealmSource: RealmObject {
    var id: String? = ""
    var name: String? = ""
}