package model;

import java.util.List;

public class WeatherResponse {
    public boolean success;
    public WeatherRecords records;

    public static class WeatherRecords {
        public String datasetDescription;
        public List<WeatherLocation> location;

        public static class WeatherLocation {

            public String locationName;
            public List<WeatherElement> weatherElement;

            public static class WeatherElement {
                public String elementName;
                public List<WeatherTime> time;

                public static class WeatherTime {
                    public String startTime;
                    public String endTime;
                    public WeatherParameter parameter;

                    public static class WeatherParameter {
                        public String parameterName;
                        public String parameterValue;
                        public String parameterUnit;

                    }
                }
            }
        }
    }
}
