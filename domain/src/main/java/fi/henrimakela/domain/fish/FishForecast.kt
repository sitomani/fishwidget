package fi.henrimakela.domain.fish

data class FishForecast(
    var description_fish_weather: String,
    var description_pressure: String,
    var description_wind: String,
    var main: String,
    var temp: Double,
    var pressure: Double,
    var wind_speed: Double,
    var wind_deg: Double )