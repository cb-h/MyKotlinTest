package com.pci.mylibrary.net.ssl

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * Created by h on 2022/2/16.
 * 作用：
 */
class SSLSocketClient {

    companion object {

        fun getSSLSocketFactory(): SSLSocketFactory {
            val sslSocketClient = SSLContext.getInstance("SSL")
            sslSocketClient.init(null, getTrustManager(), SecureRandom())
            return sslSocketClient.socketFactory
        }

        fun getTrustManager(): Array<TrustManager> {
            return arrayOf(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?,
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?,
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            })
        }

        fun getHostnameVerifier():HostnameVerifier{
            return HostnameVerifier { _, _ -> true }
        }
    }
}