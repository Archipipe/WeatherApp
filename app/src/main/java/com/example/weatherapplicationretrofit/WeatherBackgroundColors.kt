package com.example.weatherapplicationretrofit

val codeToBackgroundColor : Map<Int, List<String>> = mapOf(
    R.color.thunderstorm_day to listOf("221d", "230d", "231d", "232d", "200d", "201d", "202d", "210d", "211d", "212d"),
    R.color.thunderstorm_night to listOf("221n", "230n", "231n", "232n", "200n", "201n", "202n", "210n", "211n", "212n"),


    R.color.drizzle_day to listOf("300d", "301d", "311d", "302d", "312d", "313d", "321d", "314d"),
    R.color.drizzle_night to listOf("300n", "301n", "311n", "302n", "312n", "313n", "321n", "314n"),

    R.color.rainy_day to listOf(
        "500d", "501d", "520d", "502d", "521d", "503d", "504d", "522d", "531d", "511d", "615d"
    ),
    R.color.rainy_night to listOf(
        "500n", "501n", "520n", "502n", "521n", "503n", "504n", "522n", "531n", "511n", "615n"
    ),

    R.color.snowy_day to listOf(
        "600d", "601d", "611d", "612d", "602d", "613d", "616d", "620d", "621d", "622d"
    ),
    R.color.snowy_night to listOf(
        "600n", "601n", "611n", "612n", "602n", "613n", "616n", "620n", "621n", "622n"
    ),


    R.color.fog_day to listOf(
        "711d",  "721d", "701d", "771d", "741d"
    ),
    R.color.fog_night to listOf(
        "711n",  "721n", "701n",  "771n", "741n"
    ),

    R.color.dust_day to listOf(
         "751d",  "731d", "761d", "762d",  "781d",
    ),
    R.color.dust_night to listOf(
        "751n", "731n", "761n", "762n", "781n",
    ),

    R.color.clear_day to listOf("800d"),
    R.color.clear_night to listOf("800n"),

    R.color.cloudy_day to listOf("801d", "802d", "803d", "804d"),
    R.color.cloudy_night to listOf("801n", "802n", "803n", "804n")
 )