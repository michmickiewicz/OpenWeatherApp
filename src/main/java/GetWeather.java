import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class GetWeather {
    private static final Gson GSON = new Gson();
    private static final String APIKEY = "30664f18e3c7d2efa7c848a84947c902";

    public static void main(String[] args) throws IOException {
        System.out.println("Weather for public IP address:");
        printWeather(checkIp().getCity());
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("For which city would you like to check weather ? Type 'end' to close");
            String city = input.nextLine();
            if(city.equals("end")) {
                break;
            }
            printWeather(city);
        }
    }

    private static void printWeather(String city) throws IOException {
        try {
            System.out.println("Weather for : " + downloadWeather(city).getName());
            System.out.println("Avg temperatur : " + downloadWeather(city).getAvgTemp());
            System.out.println("        Max temp: : " + downloadWeather(city).getMaxTemp());
            System.out.println("        Min temp : " + downloadWeather(city).getMinTemp());
            System.out.println("Wind : " + downloadWeather(city).getWind() + "m/s");
            System.out.println("Clouds : " + downloadWeather(city).getClouds() + "%");
            System.out.println(downloadWeather(city).getSys());
            System.out.println("\n******************************\n");
        }
        catch (FileNotFoundException e){
            System.out.println("Could find that city !");
        }
    }

    private static Weather downloadWeather (String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+APIKEY+"&units=metric");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("user-agent", "Chrome");
        InputStream is = connection.getInputStream();
        Scanner scanner = new Scanner(is);
        String line = scanner.nextLine();
        return GSON.fromJson(line, Weather.class);
    }

    private static String getIP(){
        String systemipaddress;
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Couldn't receive IP address!";
        }
        return systemipaddress;
    }

    private static Ip checkIp() throws IOException {
        URL url = new URL("http://ip-api.com/json/"+getIP()+"?fields=16");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("user-agent", "Chrome");
        InputStream is = connection.getInputStream();
        Scanner scanner = new Scanner(is);
        String line = scanner.nextLine();
        return GSON.fromJson(line, Ip.class);
    }
}

