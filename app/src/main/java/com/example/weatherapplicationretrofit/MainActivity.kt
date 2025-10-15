package com.example.weatherapplicationretrofit

import android.Manifest
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.weatherapplicationretrofit.databinding.ActivityMainBinding
import com.example.weatherapplicationretrofit.models.WeatherResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var  webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MainActivity, permissions, 100)
            return
        } else {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        }

        webView = viewBinding.webView
        webView.settings.javaScriptEnabled = true
        webView.setBackgroundColor(Color.TRANSPARENT)

        viewBinding.bGetWeather.setOnClickListener {
            getWeather()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            100 -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Toast.makeText(
                        this,
                        "Разрешения предоставлены, жди данон спортиков и милкивей",
                        Toast.LENGTH_SHORT
                    ).show()
                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                } else {
                    Toast.makeText(this, "Разрешения отклонены :(", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getWeather() {

        if (!::fusedLocationClient.isInitialized) {
            Toast.makeText(this@MainActivity, "Нет доступа к геоданным", Toast.LENGTH_SHORT).show()
            return
        }

        viewBinding.llMoreInfo.removeAllViews()
        viewBinding.progressCircular.isVisible = true

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val (latitude, longitude) = getUserLocation()

                val call = RetrofitClient.instance.getWeather(latitude,longitude)


                    call
                    .enqueue(object: Callback<WeatherResponse> {
                        override fun onResponse(
                            call: Call<WeatherResponse>,
                            response: Response<WeatherResponse>
                        ) {
                            viewBinding.progressCircular.isVisible = false

                            if (response.isSuccessful){
                                val weatherResponse = response.body()
                                if (weatherResponse != null){
                                    var weatherTemp = weatherResponse.main.temp
                                    weatherTemp = if ("." in weatherTemp){
                                        val parts = weatherTemp.split('.')
                                        parts[0]
                                    } else{
                                        weatherTemp
                                    }

                                    val weatherId = weatherResponse.weather.first().id
                                    val resourceId = resources.getIdentifier("code_$weatherId", "string", packageName)
                                    val weatherString = if (resourceId != 0){
                                        getString(resourceId)
                                    } else{
                                        "Погоды нет? 0_о"
                                    }

                                    val isDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) in 6..17
                                    val imageId = if (isDay){
                                        "${weatherId}d"
                                    } else{
                                        "${weatherId}n"
                                    }






                                    val imageCode = codeToImage.entries.first { (k,v) ->
                                        imageId in v
                                    }.key


                                    webView.webViewClient = object : WebViewClient(){
                                        override fun onPageFinished(view: WebView?, url: String?) {
                                            super.onPageFinished(view, url)
                                            with(viewBinding){
                                                tvWeatherDegree.text = "${weatherTemp}°C"
                                                tvWeather.text = weatherString

                                                val windView = TextView(this@MainActivity)
                                                windView.text = "Скорость ветра: ${weatherResponse.wind.speed} м/cек"
                                                llMoreInfo.addView(windView)
                                                val cloudsView = TextView(this@MainActivity)
                                                cloudsView.text = "Облачность: ${weatherResponse.clouds.all}%"
                                                llMoreInfo.addView(cloudsView)

                                                if (weatherResponse.rain != null && weatherResponse.rain.oneHour != null){
                                                    val rainView = TextView(this@MainActivity)
                                                    rainView.text = "Осадки: ${weatherResponse.rain.oneHour} мм/ч"
                                                    llMoreInfo.addView(rainView)
                                                }

                                                if (weatherResponse.snow != null){
                                                    val snowView = TextView(this@MainActivity)
                                                    snowView.text = "Снег: ${weatherResponse.snow.oneHour} мм/ч"
                                                    llMoreInfo.addView(snowView)
                                                }

                                            }
                                            setColors(imageId)

                                        }
                                    }
                                    webView.isVisible = true
                                    webView.loadUrl("file:///android_asset/${imageCode}.svg")


                                }
                            } else {
                                viewBinding.tvWeather.text = "Погоды не найдено :("
                            }
                        }

                        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                            viewBinding.progressCircular.isVisible = false
                            viewBinding.tvWeather.text = "Произошла ошибка запроса погоды"
                        }

                    })
            } catch (e: Exception){
                viewBinding.progressCircular.isVisible = false
                Toast.makeText(this@MainActivity, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setColors(imageId: String){
        val background = viewBinding.main.background
        val backgroundColorDrawableStart = when(background){
            is ColorDrawable -> {background as ColorDrawable}
            is TransitionDrawable -> {background.getDrawable(background.numberOfLayers - 1) as ColorDrawable}

            else -> {ColorDrawable(ContextCompat.getColor(this@MainActivity,R.color.background_default))}
        }

        val backgroundColorEndId = codeToBackgroundColor.entries.first{ (_,v) ->
            imageId in v
        }.key

        val backgroundColorEnd = ContextCompat.getColor(this@MainActivity, backgroundColorEndId)
        val backgroundColorDrawable = ColorDrawable(backgroundColorEnd)

        val transitionDrawable = TransitionDrawable(arrayOf(backgroundColorDrawableStart, backgroundColorDrawable))

        viewBinding.main.background = transitionDrawable
        transitionDrawable.startTransition(1000)


        val tvWeather = viewBinding.tvWeather
        val tvWeatherDegree = viewBinding.tvWeatherDegree

        if (tvWeather.alpha != 1f){
            val alphaAnimatorWeather = ObjectAnimator.ofFloat(tvWeather, "alpha", 0f, 1f)
            alphaAnimatorWeather.duration = 1000
            alphaAnimatorWeather.start()
        }

        if (tvWeather.alpha != 1f){
            val alphaAnimatorWeatherDegree = ObjectAnimator.ofFloat(tvWeatherDegree, "alpha", 0f, 1f)
            alphaAnimatorWeatherDegree.duration = 1000
            alphaAnimatorWeatherDegree.start()
        }

        val textColor = codeToTextColor.entries.first{ (_,v) ->
            imageId in v
        }.key

        val buttonColor = codeToButtonColor.entries.first{ (_,v) ->
            imageId in v
        }.key
        tvWeather .setTextColor(textColor)
        tvWeatherDegree.setTextColor(textColor)
        viewBinding.bGetWeather.setTextColor(textColor)

        for (i in 0 until viewBinding.llMoreInfo.childCount){
            val child = viewBinding.llMoreInfo.getChildAt(i) as TextView
            child.setTextColor(textColor)

        }
        viewBinding.bGetWeather.setBackgroundColor(buttonColor)

    }



    private suspend fun getUserLocation(): Pair<String, String> =
        suspendCancellableCoroutine { continuation ->

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location == null) {
                            continuation.resumeWithException(Exception("Данные недоступны"))

                        } else {
                            val latitude = location.latitude.toString()
                            val longtitude = location.longitude.toString()
                            continuation.resume(Pair(latitude, longtitude))
                        }
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            } else{
                continuation.resumeWithException(Exception("Разрешения не предоставлены"))
            }
        }
}