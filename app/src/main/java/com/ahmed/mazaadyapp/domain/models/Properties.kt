package com.ahmed.mazaadyapp.domain.models


data class Properties(
    val `data`: List<Data>,
) {
    data class Data(
        val name: String = "",
        val id:Int,
        val options: List<Option> = listOf()
    ){
        data class Option(
            val id: Int = 0,
            val name: String = "",
        )
    }
}