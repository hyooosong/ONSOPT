package com.example.onsopt_1st.data

data class ResponseSearchData(
    val documents: List<Document>,
    val meta: Meta
) {
    data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Int
    )
    data class Document(
        val contents: String,
        val datetime: String,
        val title: String,
        val url: String
    )
}