import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

class Weather {
private Map<String,String> main;
private Map<String,String> wind;
private Map<String,String> clouds;
private Map<String,String> sys;
private String name;



    String getSys() {
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        final long unixTime1 = Long.valueOf(sys.get("sunrise"));
        final long unixTime2 = Long.valueOf(sys.get("sunset"));
        final String formattedSunrise = Instant.ofEpochSecond(unixTime1)
                .atZone(ZoneId.of("GMT+1"))
                .format(formatter);
        final String formattedSunset = Instant.ofEpochSecond(unixTime2)
                .atZone(ZoneId.of("GMT+1"))
                .format(formatter);

        return "Sunrise : "+ formattedSunrise +", sunset : "+ formattedSunset;
    }

    String getClouds() {
        return clouds.get("all");
    }

    String getWind() {
        return wind.get("speed");
    }

    String getMaxTemp() {
        return main.get("temp_max");
    }

    String getMinTemp() {
        return main.get("temp_min");
    }

    String getAvgTemp() {
        return main.get("temp");
    }

    String getName() {
        return name;
    }
}
