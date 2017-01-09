package com.example.zhangqian.myapplication.entity;

public class WeatherResult {
	private String weaid;

	private String days;

	private String week;

	private String cityno;

	private String citynm;

	private String cityid;

	private String temperature;

	private String humidity;

	private String weather;

	private String weather_icon;

	private String weather_icon1;

	private String wind;

	private String winp;

	private String temp_high;

	private String temp_low;

	private String humi_high;

	private String humi_low;

	private String weatid;

	private String weatid1;

	private String windid;

	private String winpid;

	public void setWeaid(String weaid) {
		this.weaid = weaid;
	}

	public String getWeaid() {
		return this.weaid;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDays() {
		return this.days;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeek() {
		return this.week;
	}

	public void setCityno(String cityno) {
		this.cityno = cityno;
	}

	public String getCityno() {
		return this.cityno;
	}

	public void setCitynm(String citynm) {
		this.citynm = citynm;
	}

	public String getCitynm() {
		return this.citynm;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCityid() {
		return this.cityid;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getHumidity() {
		return this.humidity;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeather() {
		return this.weather;
	}

	public void setWeather_icon(String weather_icon) {
		this.weather_icon = weather_icon;
	}

	public String getWeather_icon() {
		return this.weather_icon;
	}

	public void setWeather_icon1(String weather_icon1) {
		this.weather_icon1 = weather_icon1;
	}

	public String getWeather_icon1() {
		return this.weather_icon1;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWind() {
		return this.wind;
	}

	public void setWinp(String winp) {
		this.winp = winp;
	}

	public String getWinp() {
		return this.winp;
	}

	public void setTemp_high(String temp_high) {
		this.temp_high = temp_high;
	}

	public String getTemp_high() {
		return this.temp_high;
	}

	public void setTemp_low(String temp_low) {
		this.temp_low = temp_low;
	}

	public String getTemp_low() {
		return this.temp_low;
	}

	public void setHumi_high(String humi_high) {
		this.humi_high = humi_high;
	}

	public String getHumi_high() {
		return this.humi_high;
	}

	public void setHumi_low(String humi_low) {
		this.humi_low = humi_low;
	}

	public String getHumi_low() {
		return this.humi_low;
	}

	public void setWeatid(String weatid) {
		this.weatid = weatid;
	}

	public String getWeatid() {
		return this.weatid;
	}

	public void setWeatid1(String weatid1) {
		this.weatid1 = weatid1;
	}

	public String getWeatid1() {
		return this.weatid1;
	}

	public void setWindid(String windid) {
		this.windid = windid;
	}

	public String getWindid() {
		return this.windid;
	}

	public void setWinpid(String winpid) {
		this.winpid = winpid;
	}

	public String getWinpid() {
		return this.winpid;
	}

	@Override
	public String toString() {
		return "WeatherResult [weaid=" + weaid + ", days=" + days + ", week="
				+ week + ", cityno=" + cityno + ", citynm=" + citynm
				+ ", cityid=" + cityid + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", weather=" + weather
				+ ", weather_icon=" + weather_icon + ", weather_icon1="
				+ weather_icon1 + ", wind=" + wind + ", winp=" + winp
				+ ", temp_high=" + temp_high + ", temp_low=" + temp_low
				+ ", humi_high=" + humi_high + ", humi_low=" + humi_low
				+ ", weatid=" + weatid + ", weatid1=" + weatid1 + ", windid="
				+ windid + ", winpid=" + winpid + "]";
	}

	public WeatherResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeatherResult(String weaid, String days, String week, String cityno,
			String citynm, String cityid, String temperature, String humidity,
			String weather, String weather_icon, String weather_icon1,
			String wind, String winp, String temp_high, String temp_low,
			String humi_high, String humi_low, String weatid, String weatid1,
			String windid, String winpid) {
		super();
		this.weaid = weaid;
		this.days = days;
		this.week = week;
		this.cityno = cityno;
		this.citynm = citynm;
		this.cityid = cityid;
		this.temperature = temperature;
		this.humidity = humidity;
		this.weather = weather;
		this.weather_icon = weather_icon;
		this.weather_icon1 = weather_icon1;
		this.wind = wind;
		this.winp = winp;
		this.temp_high = temp_high;
		this.temp_low = temp_low;
		this.humi_high = humi_high;
		this.humi_low = humi_low;
		this.weatid = weatid;
		this.weatid1 = weatid1;
		this.windid = windid;
		this.winpid = winpid;
	}

}
