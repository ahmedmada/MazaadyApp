package com.ahmed.mazaadyapp.domain.usecases

import com.ahmed.mazaadyapp.common.Resources
import com.ahmed.mazaadyapp.domain.models.AllCategories
import com.ahmed.mazaadyapp.domain.repository.RetroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val retroRepo: RetroRepo
){
    operator fun invoke(): Flow<Resources<AllCategories>> = flow {
        try {
            emit(Resources.Loading())
            val data = retroRepo.getCategories()
            emit(Resources.Success(data.toAllCategoriesModel()))
        } catch (e: HttpException) {
            emit(Resources.Error(message = e.message.toString()))
        } catch (e: IOException) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}