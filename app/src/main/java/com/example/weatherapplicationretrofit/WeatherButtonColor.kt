package com.example.weatherapplicationretrofit

import android.graphics.Color

val codeToButtonColor : Map<Int, List<String>> = mapOf(
    Color.parseColor("#FFA726") to listOf("221d", "230d", "231d", "232d", "200d", "201d", "202d", "210d", "211d", "212d","221n", "230n", "231n", "232n", "200n", "201n", "202n", "210n", "211n", "212n"),

    Color.parseColor("#FFC107") to listOf("300d", "301d", "311d", "302d", "312d", "313d", "321d", "314d","600d", "601d", "611d", "612d", "602d", "613d", "616d", "620d", "621d", "622d", "500d", "501d", "520d", "502d", "521d", "503d", "504d", "522d", "531d", "511d", "615d","800d"),
    Color.parseColor("#78909C") to listOf("300n", "301n", "311n", "302n", "312n", "313n", "321n", "314n","800n", "500n", "501n", "520n", "502n", "521n", "503n", "504n", "522n", "531n", "511n", "615n","600n", "601n", "611n", "612n", "602n", "613n", "616n", "620n", "621n", "622n", "711n",  "721n", "701n", "741n","801n", "802n", "803n", "804n"),


    Color.parseColor("#FF9800") to listOf("711d",  "721d", "701d","741d"),

    Color.parseColor("#8D6E63") to listOf("751d",  "731d", "761d", "762d",  "781d",),
    Color.parseColor("#569FED") to listOf("771d","771n"),
    Color.parseColor("#D84315") to listOf( "751n", "731n", "761n", "762n", "781n",),
    Color.parseColor("#4DD0E1") to listOf("801d", "802d", "803d", "804d"),

)