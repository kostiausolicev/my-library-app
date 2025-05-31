package ru.guap.utils

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

object HashUtils {
    private val mac = Mac.getInstance("HmacSHA256")
    private val secretKey = SecretKeySpec("secret".toByteArray(), "HmacSHA256")

    fun hmacSha256(data: String): String {
        mac.init(secretKey)
        val hmacResult = mac.doFinal(data.toByteArray())
        return hmacResult.joinToString("") { String.format("%02x", it and 0xff.toByte()) }
    }

    fun validateHmac(data: String, receivedHmac: String): Boolean {
        val calculatedHmac = hmacSha256(data)
        return calculatedHmac.equals(receivedHmac, ignoreCase = true)
    }
}