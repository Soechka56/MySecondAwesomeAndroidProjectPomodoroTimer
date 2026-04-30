package com.example.data.datastore

import androidx.datastore.core.Serializer
import com.example.utils.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import kotlin.io.encoding.Base64

class UserPreferencesSerializer @Inject constructor(
    private val crypto: Crypto
) : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        val encryptedBytes = withContext(Dispatchers.IO) {
            input.use {
                it.readBytes()
            }
        }
        val encryptedBytesDecoded = Base64.decode(encryptedBytes)
        val decryptedBytes = crypto.decrypt(encryptedBytesDecoded)
        val decodedJsonString = decryptedBytes.decodeToString()
        return Json.decodeFromString(decodedJsonString)
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        val encryptedBytes = crypto.encrypt(bytes)
        val encryptedBytesBase64 = Base64.encode(encryptedBytes)

        withContext(Dispatchers.IO) {
            output.write(encryptedBytesBase64.toByteArray())
        }
    }
}