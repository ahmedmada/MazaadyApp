package com.ahmed.mazaadyapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ahmed.mazaadyapp.domain.models.Properties

data class PropertiesDTO(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("msg")
    val msg: String = ""
) {
    data class Data(
        @SerializedName("description")
        val description: String = "",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("list")
        val list: Boolean = false,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("options")
        val options: List<Option> = listOf(),
        @SerializedName("other_value")
        val otherValue: Any? = null,
        @SerializedName("parent")
        val parent: Any? = null,
        @SerializedName("slug")
        val slug: String = "",
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("value")
        val value: String = ""
    ) {
        data class Option(
            @SerializedName("child")
            val child: Boolean = false,
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("name")
            val name: String = "",
            @SerializedName("parent")
            val parent: Int = 0,
            @SerializedName("slug")
            val slug: String = ""
        )
    }

    fun toProperties(): Properties {
        return Properties(
            data.map {
                Properties.Data(
                    id = it.id,
                    name = it.name,
                    options = it.options.map { opt ->
                        Properties.Data.Option(
                            id = opt.id,
                            name = opt.name
                        )
                    }.plus(
                        Properties.Data.Option(
                        id = -1,
                        name = "خيار اخر"
                    ))
                )
            }
        )
    }
}