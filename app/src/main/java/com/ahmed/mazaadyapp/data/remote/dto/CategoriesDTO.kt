package com.ahmed.mazaadyapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ahmed.mazaadyapp.domain.models.AllCategories

data class CategoriesDTO(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("msg")
    val msg: String = ""
) {
    data class Data(
        @SerializedName("ads_banners")
        val adsBanners: List<AdsBanner> = listOf(),
        @SerializedName("categories")
        val categories: List<Category> = listOf(),
        @SerializedName("google_version")
        val googleVersion: String = "",
        @SerializedName("huawei_version")
        val huaweiVersion: String = "",
        @SerializedName("ios_latest_version")
        val iosLatestVersion: String = "",
        @SerializedName("ios_version")
        val iosVersion: String = "",
        @SerializedName("statistics")
        val statistics: Statistics = Statistics()
    ) {
        data class AdsBanner(
            @SerializedName("duration")
            val duration: Int = 0,
            @SerializedName("img")
            val img: String = "",
            @SerializedName("media_type")
            val mediaType: String = ""
        )

        data class Category(
            @SerializedName("children")
            val children: List<Children> = listOf(),
            @SerializedName("circle_icon")
            val circleIcon: String = "",
            @SerializedName("description")
            val description: Any? = null,
            @SerializedName("disable_shipping")
            val disableShipping: Int = 0,
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("image")
            val image: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("slug")
            val slug: String = ""
        ) {
            data class Children(
                @SerializedName("children")
                val children: Any? = null,
                @SerializedName("circle_icon")
                val circleIcon: String = "",
                @SerializedName("description")
                val description: Any? = null,
                @SerializedName("disable_shipping")
                val disableShipping: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("image")
                val image: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("slug")
                val slug: String = ""
            )
        }

        data class Statistics(
            @SerializedName("auctions")
            val auctions: Int = 0,
            @SerializedName("products")
            val products: Int = 0,
            @SerializedName("users")
            val users: Int = 0
        )
    }

    fun toAllCategoriesModel(): AllCategories {
        return AllCategories(
            data.categories.map { category ->
                AllCategories.Category(
                    id = category.id,
                    name = category.name,
                    children = category.children.map {
                        AllCategories.Category.Children(
                            id = it.id,
                            name = it.name
                        )
                    }
                )
            }
        )
    }
}