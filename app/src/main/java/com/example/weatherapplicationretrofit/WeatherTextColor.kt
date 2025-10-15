package com.example.weatherapplicationretrofit

import android.graphics.Color

val codeToTextColor : Map<Int, List<String>> = mapOf(
    Color.parseColor("#F0F0F0") to listOf("221d", "230d", "231d", "232d", "200d", "201d", "202d", "210d", "211d", "212d"),
    Color.parseColor("#E0E0E0") to listOf("221n", "230n", "231n", "232n", "200n", "201n", "202n", "210n", "211n", "212n",  "751n", "731n", "761n", "762n", "781n","800n","801n", "802n", "803n", "804n"),

    Color.parseColor("#2E2E2E") to listOf("300d", "301d", "311d", "302d", "312d", "313d", "321d", "314d",),
    Color.parseColor("#CFD8DC") to listOf("300n", "301n", "311n", "302n", "312n", "313n", "321n", "314n", "500n", "501n", "520n", "502n", "521n", "503n", "504n", "522n", "531n", "511n", "615n","600n", "601n", "611n", "612n", "602n", "613n", "616n", "620n", "621n", "622n", "711n",  "721n", "701n", "741n", "771n"),

    Color.parseColor("#FFFFFF") to listOf(
        "500d", "501d", "520d", "502d", "521d", "503d", "504d", "522d", "531d", "511d", "615d","800d"
    ),

    Color.parseColor("#0D47A1") to listOf( "600d", "601d", "611d", "612d", "602d", "613d", "616d", "620d", "621d", "622d"),


    Color.parseColor("#1A237E") to listOf("711d",  "721d", "701d", "771d", "741d",),

    Color.parseColor("#4E342E") to listOf("751d",  "731d", "761d", "762d",  "781d",),
    Color.parseColor("#455A64") to listOf("801d", "802d", "803d", "804d"),





)