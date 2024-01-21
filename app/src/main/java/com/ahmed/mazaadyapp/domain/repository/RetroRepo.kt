package com.ahmed.mazaadyapp.domain.repository

import com.ahmed.mazaadyapp.data.remote.dto.CategoriesDTO
import com.ahmed.mazaadyapp.data.remote.dto.OptionsDTO
import com.ahmed.mazaadyapp.data.remote.dto.PropertiesDTO

interface RetroRepo {

    suspend fun getCategories(): CategoriesDTO

    suspend fun getProperties(id: Int): PropertiesDTO

    suspend fun getOptions(id: Int): OptionsDTO

}