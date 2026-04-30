package com.example.data.repository

import androidx.datastore.core.DataStore
import com.example.common.qualifier.DispatcherIO
import com.example.data.datastore.UserPreferences
import com.example.data.mapper.UserDataMapper
import com.example.data.repository.ext.toResultOfOperation
import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.example.network.models.request.PostUserLoginData
import com.example.network.models.request.PostUserRegistrationData
import com.example.network.models.response.SuccessAuthResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val pomodoroApi: PomodoroApi,
    private val userDataMapper: UserDataMapper,
    private val dataStore: DataStore<UserPreferences>,
    @param:DispatcherIO private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun logInAccount(
        email: String,
        password: String,
    ): ResultOfOperation<LoginInfo> = withContext(dispatcher) {
        try {
            val response = pomodoroApi.loginAccount(
                user = PostUserLoginData(
                    email = email,
                    password = password,
                )
            )
            authHandler(response)

        } catch (throwable: Throwable) {
            throwable.toResultOfOperation()
        }
    }

    override suspend fun signUpAccount(
        email: String,
        username: String,
        fullName: String,
        password: String,
    ): ResultOfOperation<LoginInfo> = withContext(dispatcher) {
        try {
            val response = pomodoroApi.registerAccount(
                user = PostUserRegistrationData(
                    email = email,
                    username = username,
                    fullName = fullName,
                    password = password,
                )
            )
            authHandler(response)

        } catch (throwable: Throwable) {
            throwable.toResultOfOperation()
        }
    }


    override suspend fun showProfile(id: Int): ResultOfOperation<UserInfo> =
        withContext(dispatcher) {
            try {
                val response = pomodoroApi.getUserProfile(id)
                when {
                    response.isSuccessful -> {
                        val body = response.body()

                        if (body == null) {
                            ResultOfOperation.Error(OperationError.Unknown("Empty response body"))
                        } else {
                            ResultOfOperation.Success(
                                userDataMapper.mapToDomain(
                                    userDataMapper.mapToData(
                                        body
                                    )
                                )
                            )
                        }
                    }

                    else -> throw HttpException(response)
                }


            } catch (throwable: Throwable) {
                throwable.toResultOfOperation()
            }
        }


    suspend fun authHandler(response: Response<SuccessAuthResponse>): ResultOfOperation<LoginInfo> {
        return try {
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body == null) {
                        ResultOfOperation.Error(OperationError.Unknown("Empty response body"))
                    } else {
                        ResultOfOperation.Success(
                            data = userDataMapper.mapToDomain(
                                userDataMapper.mapToData(body)
                            )
                        ).also { result ->
                            updateToken(token = result.data.accessToken)
                        }
                    }
                }

                else -> throw HttpException(response)
            }
        } catch (throwable: Throwable) {
            throwable.toResultOfOperation()
        }
    }


    suspend fun updateToken(token: String) {
        dataStore.updateData {
            UserPreferences(token = token)
        }
    }
}


