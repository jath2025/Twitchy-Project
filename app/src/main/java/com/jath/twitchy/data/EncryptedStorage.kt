package com.jath.twitchy.data

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.File
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

private const val KEY_ALIAS = "twitchy_key"

class EncryptedStorage(private val context: Context) {

    // Structure with some changes from: https://medium.com/@khorassani64/using-android-keystore-in-kotlin-and-jetpack-compose-b85b3a922cf3
    fun generateKey(alias: String): SecretKey {
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {load(null)}
        if (keyStore.containsAlias(alias)) {
            return keyStore.getKey(alias, null) as SecretKey
        }

        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    // Source I found helpful for encrypt and decrypt: https://developer.android.com/privacy-and-security/cryptography
    fun encrypt(text: String): ByteArray {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(KEY_ALIAS))
        val encryptedText = cipher.doFinal(text.toByteArray())
        return cipher.iv + encryptedText
    }
    fun decrypt(encryptedData: ByteArray): String {
        val iv = encryptedData.copyOfRange(0, 12)
        val encryptedText = encryptedData.copyOfRange(12, encryptedData.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, generateKey(KEY_ALIAS), GCMParameterSpec(128, iv))
        return String(cipher.doFinal(encryptedText))
    }

    fun save(fileName: String, text: String) {
        File(context.filesDir, fileName).writeBytes(encrypt(text))
    }
    fun load(fileName: String): String? {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            return null
        }
        return try {
            decrypt(file.readBytes())
        } catch (e: Exception) {
            null
        }
    }
}