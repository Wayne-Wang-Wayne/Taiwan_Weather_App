package model;

public class WeatherData {
    public String locationName;
    public String elementName;
    public String startTime;
    public String endTime;
    public String parameterName;
    public String parameterValue;
    public String parameterUnit;

    public WeatherData(String locationName, String elementName, String startTime, String endTime, String parameterName, String parameterValue, String parameterUnit) {
        this.locationName = locationName;
        this.elementName = elementName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.parameterUnit = parameterUnit;
    }
}