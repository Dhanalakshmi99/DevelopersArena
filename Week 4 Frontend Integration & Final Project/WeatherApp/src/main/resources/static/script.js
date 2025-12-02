function getWeather() {
    const city = document.getElementById("city").value;

    fetch(`/api/weather/${city}`)
        .then(res => res.json())
        .then(data => {

            if (data.cod !== 200) {
                document.getElementById("error").innerText = "City not found!";
                document.getElementById("weather-box").classList.add("hidden");
                return;
            }

            document.getElementById("error").innerText = "";
            document.getElementById("city-name").innerText = data.name;
            document.getElementById("temp").innerText = data.main.temp + " °C";
            document.getElementById("desc").innerText = data.weather[0].description;

            document.getElementById("weather-box").classList.remove("hidden");
        })
        .catch(() => {
            document.getElementById("error").innerText = "Error fetching weather!";
        });
}
