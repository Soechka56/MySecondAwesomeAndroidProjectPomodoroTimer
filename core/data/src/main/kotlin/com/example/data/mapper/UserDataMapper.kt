package com.example.data.mapper

import com.example.data.model.LoginDataModel
import com.example.data.model.UserDataModel
import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo
import com.example.network.models.response.SuccessLoginResponse
import com.example.network.models.response.UserInfoResponse
import javax.inject.Inject

class UserDataMapper @Inject constructor() {
    fun mapToDomain(loginDataModel: LoginDataModel): LoginInfo {
        return LoginInfo(
            accessToken = loginDataModel.accessToken,
            tokenType = loginDataModel.tokenType,
        )
    }

    fun mapToDomain(userDataModel: UserDataModel): UserInfo {
        return UserInfo(
            id = userDataModel.id,
            username = userDataModel.username,
            fullName = userDataModel.fullName,
            avatarUrl = userDataModel.avatarUrl,
            email = userDataModel.email,
            createdAt = userDataModel.createdAt,
            updatedAt = userDataModel.updatedAt,
        )
    }

    fun mapToData(response: SuccessLoginResponse): LoginDataModel {
        return LoginDataModel(
            accessToken = response.accessToken,
            tokenType = response.tokenType,
        )
    }

    fun mapToData(userInfo: UserInfo): UserDataModel {
        return UserDataModel(
            id = userInfo.id,
            username = userInfo.username,
            fullName = userInfo.fullName,
            avatarUrl = userInfo.avatarUrl,
            email = userInfo.email,
            createdAt = userInfo.createdAt,
            updatedAt = userInfo.updatedAt,
        )
    }

    fun mapToData(response: UserInfoResponse): UserDataModel {
        return UserDataModel(
            id = response.id,
            username = response.username,
            fullName = response.fullName,
            avatarUrl = response.avatarUrl,
            email = response.email,
            createdAt = response.createdAt,
            updatedAt = response.updatedAt,
        )
    }
}
