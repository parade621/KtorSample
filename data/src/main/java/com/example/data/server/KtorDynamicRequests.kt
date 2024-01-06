package com.example.data.server


//suspend fun requestServerLogin(
//    loginId: String,
//    password: String,
//    referer: String,
//    vehicle: String,
//    latitude: String,
//    longitude: String,
//): APIModel {
//    val client = createHttpClient()
//    val params = StringValues.Companion.build {
//        append("login_id", loginId)
//        append("password", password)
//        append("referer", referer)
//        append("vehicle", vehicle)
//        append("latitude", latitude)
//        append("longitude", longitude)
//        append("admin_yn", "N")
//        append("chanel", Def.APP_ID)
//        append("ip", "")
//        append("app_id", Def.APP_ID)
//        append("nation_cd", Def.NATION_CD)
//    }
//    return client.dynamicRequest(
//        methodName = "LoginQXQuick",
//        params = params
//    ).also {
//        client.close()
//    }
//}