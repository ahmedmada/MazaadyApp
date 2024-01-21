package com.ahmed.mazaadyapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ahmed.mazaadyapp.domain.models.Options

data class OptionsDTO(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("msg")
    val msg: String = ""
) {
    data class Data(
        @SerializedName("description")
        val description: Any? = null,
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
        val parent: Int = 0,
        @SerializedName("slug")
        val slug: String = "",
        @SerializedName("type")
        val type: Any? = null,
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
    fun toOptions(): Options {
        return Options(
            data.map {
                Options.Data(
                    id = it.id,
                    name = it.name,
                    options = it.options.map { opt ->
                        Options.Data.Option(
                            id = opt.id,
                            name = opt.name
                        )
                    }.plus(
                        Options.Data.Option(
                            id = -1,
                            name = "خيار اخر"
                        ))
                )
            }
        )
    }
}