package com.example.ktorsample.data.server

import com.example.ktorsample.data.APIDataModel
import com.example.ktorsample.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.post
import io.ktor.http.headers
import io.ktor.http.isSuccess
import io.ktor.serialization.gson.gson
import io.ktor.util.StringValues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun ktorIOReqeuster(methodName: String, params: StringValues? = null): APIDataModel =
    withContext(Dispatchers.IO) {
        // CIO: Coroutine 비동기 코루틴 기반의 engine (JVM, Android, Kotlin/Native)
        val client = HttpClient(CIO) {

            // 로깅 플러그인 설치 및 구성
            // 디버그 모드일 때 출력한다.
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        if (BuildConfig.DEBUG) {
                            Timber.i(message)
                        }
                    }
                }
                level = LogLevel.BODY
            }

            install(ContentNegotiation) {
                gson()
            }

            // TimeOut 설정
            install(HttpTimeout) {
                requestTimeoutMillis = 5_000 // 5초
                connectTimeoutMillis = 5_000
                socketTimeoutMillis = 5_000
            }

            // HttpRequestRetry 플러그인 설치 및 구성
            // HTTP 요청을 자동으로 재시도하는 기능 제공
            install(HttpRequestRetry) {
                maxRetries = 1 // 최대 재시도 횟수를 설정 -> 현재는 1로 설정되어 있어 요청 실패 시 한 번만 재시도

                // 재시도 여부를 결정하는 조건을 정의
                // 요청(request)과 응답(response)을 인자로 받아서 재시도 여부를 boolean으로 반환
                // 응답 삳태 코드가 성공(isSuccess())가 아닌 경우 재시도.
                retryIf { request, response ->
                    if (BuildConfig.DEBUG) {
                        // 디버그 모드에서는 로그 출력
                        Timber.i("retryIf " + !response.status.isSuccess())
                    }
                    !response.status.isSuccess()
                }

                // 예외 발생 시, 재시도 여부 결정
                // retryIf와 동일한 인자를 받아서 재시도 여부를 boolean으로 반환
                // 현재 코드는 모든 예외 상황에서 재시도
                retryOnExceptionIf { request, cause ->
                    if (BuildConfig.DEBUG) {
                        Timber.i("error !!!  " + request.toString() + cause.message)
                    }
                    // cause is NetworkError
                    true
                }

                // 재시도 사이에 0.5초 딜레이 설정
                delayMillis { retry ->
                    500L
                }
            }
        }

        val serverURL = DataStoreManager.serverURL + Common.API_ADDRESS + methodName

        // Ktor 클라이언트를 사용해 서버에 POST 요청을 보내고 응답을 받음

        // client는 Ktor 클라이언트의 인스턴스
        // post(serverURL)은 serverURL에 POST 요청을 보내는 것을 의미
        // body() 메서드는 서버로부터 받은 응답 본문을 추출하며, 추출된 데이터는 APIDataModel 타입으로 반환
        val responseGson: APIDataModel = client.post(serverURL) {

            // 헤더블록은 HTTP 요청에 헤더를 추가한다.
            headers {
                append("User-Agent", DataStoreManager.userAgent)
            }

            // params가 null이 아닌 경우, url에 매개변수를 추가한다.
            if (params != null) {
                url {
                    // parameters.appendAll(params)는 주어진 params 맵에 있는 모든 키-값 쌍을 URL 매개변수로 추가
                    parameters.appendAll(params)
                }
            }
        }.body()

        if (BuildConfig.DEBUG) {
            Timber.e("$methodName ==> ${responseGson.resultCode} / ${responseGson.resultMsg} / ${responseGson.resultObject.toString()}")
        }

        client.close()

        return@withContext responseGson
    }