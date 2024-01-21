package com.ahmed.mazaadyapp.domain.models

data class AllCategories(
    val categories: List<Category>,
) {
    data class Category(
        val id: Int,
        val name: String,
        val children: List<Children>,
    ) {
        data class Children(
            val id: Int,
            val name: String
        )
    }

}
