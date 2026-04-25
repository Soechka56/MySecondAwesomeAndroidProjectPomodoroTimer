package com.example.data.repository

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
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val pomodoroApi: PomodoroApi,
    private val userDataMapper: UserDataMapper,
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
                        )
                    }
                }

                else -> throw HttpException(response)
            }
        } catch (throwable: Throwable) {
            throwable.toResultOfOperation()
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
                        )
                    }
                }

                else -> throw HttpException(response)
            }
        } catch (throwable: Throwable) {
            throwable.toResultOfOperation()
        }
    }

    override suspend fun showProfile(id: Int): ResultOfOperation<UserInfo> {
        return try {
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

}
