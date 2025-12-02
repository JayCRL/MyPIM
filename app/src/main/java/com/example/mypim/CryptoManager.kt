package com.example.mypim

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.File
import java.io.FileOutputStream
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class CryptoManager {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val TRANSFORMATION = "AES/GCM/NoPadding"
    private val ALIAS = "secret"

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(ALIAS, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore").apply {
            init(
                KeyGenParameterSpec.Builder(
                    ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .build()
            )
        }.generateKey()
    }

    /**
     * 获取数据库密码。
     * 如果密码文件不存在，则创建一个新的随机密码，加密并保存。
     * 如果文件存在，则读取并解密。
     *
     * @param filesDir 上下文的文件目录 (context.filesDir)
     */
    fun getPassphrase(filesDir: File): ByteArray {
        val file = File(filesDir, "db_secret.enc")

        if (!file.exists()) {
            return generateAndSavePassphrase(file)
        }

        return try {
            val bytes = file.readBytes()
            // GCM IV 长度通常为 12 字节
            if (bytes.size < 12) return generateAndSavePassphrase(file) // 文件损坏，重新生成

            val iv = bytes.copyOfRange(0, 12)
            val encryptedData = bytes.copyOfRange(12, bytes.size)

            val cipher = Cipher.getInstance(TRANSFORMATION)
            val spec = GCMParameterSpec(128, iv)
            cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)

            cipher.doFinal(encryptedData)
        } catch (e: Exception) {
            // 如果解密失败（例如重装App导致KeyStore失效），则重新生成
            e.printStackTrace()
            generateAndSavePassphrase(file)
        }
    }

    private fun generateAndSavePassphrase(file: File): ByteArray {
        // 1. 生成 32 字节的随机密码
        val randomPassphrase = ByteArray(32)
        SecureRandom().nextBytes(randomPassphrase)

        // 2. 初始化加密
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getKey())

        val iv = cipher.iv
        val encryptedData = cipher.doFinal(randomPassphrase)

        // 3. 将 IV 和加密数据写入文件
        FileOutputStream(file).use { output ->
            output.write(iv)
            output.write(encryptedData)
        }

        return randomPassphrase
    }
}