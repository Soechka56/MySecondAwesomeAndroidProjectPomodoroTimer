package com.example.data.repository

import com.example.data.mapper.UserDataMapper
import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.example.network.mapper.NetworkErrorMapper
import com.example.network.mapper.SimpleNetworkResult
import com.example.network.models.request.PostUserLoginData
import com.example.network.models.request.PostUserRegistrationData
import java.io.IOException

class UserRepositoryImpl(
    private val pomodoroApi: PomodoroApi,
    private val userDataMapper: UserDataMapper,
    private val networkErrorMapper: NetworkErrorMapper,
) : UserRepository {
    override suspend fun logInAccount(
        email: String,
        password: String,
    ): ResultOfOperation<LoginInfo> {
        return try {
            val response = pomodoroApi.loginAccount(
                user = PostUserLoginData(
                    email = email,
                    password = password,
                )
            )

            when (val result = networkErrorMapper.map(response.code(), response.errorBody())) {
                SimpleNetworkResult.Success -> {
                    val body = response.body()
                    if (body == null) {
                        ResultOfOperation.Error(OperationError.Unknown("Empty response body"))
                    } else {
                        ResultOfOperation.Success(
                            data = userDataMapper.mapToDomain(
                                userDataMapper.mapToData(body)
                            )
                        )
                    }
                }

                is SimpleNetworkResult.Error -> {
                    ResultOfOperation.Error(OperationError.Validation(result.message))
                }
            }
        } catch (_: IOException) {
            ResultOfOperation.Error(OperationError.NoInternet)
        } catch (exception: Exception) {
            ResultOfOperation.Error(OperationError.Unknown(exception.message))
        }
    }

    override suspend fun signUpAccount(
        email: String,
        username: String,
        fullName: String,
        password: String,
    ): ResultOfOperation<UserInfo> {
        return try {
            val response = pomodoroApi.registerAccount(
                user = PostUserRegistrationData(
                    email = email,
                    username = username,
                    fullName = fullName,
                    password = password,
                )
            )

            when (val result = networkErrorMapper.map(response.code(), response.errorBody())) {
                SimpleNetworkResult.Success -> {
                    val body = response.body()
                    if (body == null) {
                        ResultOfOperation.Error(OperationError.Unknown("Empty response body"))
                    } else {
                        ResultOfOperation.Success(
                            data = userDataMapper.mapToDomain(
                                userDataMapper.mapToData(body)
                            )
                        )
                    }
                }

                is SimpleNetworkResult.Error -> {
                    ResultOfOperation.Error(OperationError.Validation(result.message))
                }
            }
        } catch (_: IOException) {
            ResultOfOperation.Error(OperationError.NoInternet)
        } catch (exception: Exception) {
            ResultOfOperation.Error(OperationError.Unknown(exception.message))
        }
    }
}
