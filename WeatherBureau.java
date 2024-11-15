import java.util.*;

//------------------------------------------------------------------------
/**
 *  This class represents a weather service that keeps track of 
 *  all the weather stations. Internally, it should use a map to 
 *  associate weather station IDs with weather station objects. 
 *  
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2023.11.14)
 */
public class WeatherBureau
{
    //~ Fields ................................................................
    private Map<String, WeatherStation> weatherStations;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherBureau object.
     */
    public WeatherBureau()
    {
        /*# Do any work to initialize your class here. */
        this.weatherStations = new HashMap<>();
    }


    //~ Methods ...............................................................
    /**
     * Records a single one-line daily weather summary for 
     * one day at one weather station.
     * If the rainfall amount is -1, ignores the line. 
     * Otherwise, records the rainfall in the corresponding 
     * weather station object.
     *
     * @param text A string representing a single one-line 
     * daily weather summary.
     */
    public void recordDailySummary(String text)
    {
        Scanner textScanner = new Scanner(text);
        String stationId = textScanner.next();
        for (int i = 0; i < 3; i++)
        {
            textScanner.nextDouble();
        }
        String dayRainfall = textScanner.next();
        Scanner dayScanner = new Scanner(dayRainfall);
        dayScanner.useDelimiter("/");
        int month = dayScanner.nextInt();
        dayScanner.close();
        
        double rain = textScanner.nextDouble();
        if (weatherStations.containsKey(stationId))
        {
            if (rain != -1)
            {
                weatherStations.get(stationId).recordDailyRain(month, rain);
            }
        }
        else
        {
            WeatherStation costaRica = new WeatherStation(stationId);
            if (rain != -1)
            {
                costaRica.recordDailyRain(month, rain);
            }
            weatherStations.put(stationId, costaRica);
        }
    }
    
    /**
     * Records all daily summaries from the input data source 
     * represented by a Scanner object.
     *
     * @param input A Scanner object representing an input 
     * data source (e.g., a file) containing daily summary 
     * records.
     */
    public void recordDailySummaries(Scanner input)
    {
        while (input.hasNextLine())
        {
            recordDailySummary(input.nextLine());
        }
    }

    /**
     * Returns the weather station object for the given 
     * weather station ID.
     *
     * @param identifier The weather station ID.
     * @return The WeatherStation object for the given ID, 
     * or null if the identifier doesn't match any weather 
     * station.
     */
    public WeatherStation getStation(String identifier)
    {
        if (!weatherStations.containsKey(identifier))
        {
            return null;
        }
        return weatherStations.get(identifier);
    }
    
    /**
     * Returns the weather station that has the lowest average 
     * rainfall for the specified month.
     *
     * @param month The specified month (1-12).
     * @return The WeatherStation object with the lowest 
     * average rainfall for the specified month, or null `
     * if no data is available.
     */
    public WeatherStation lowestStation(int month)
    {
        double average = 1000000.0;
        WeatherStation lowAverage = new WeatherStation(null);
        for (String str : weatherStations.keySet())
        {
            double avgRainfall = weatherStations.get(str).getAvgForMonth(month);
            if (avgRainfall < average && avgRainfall != -1)
            {
                average = avgRainfall;
                lowAverage = weatherStations.get(str);
            }
        }
        if (average == 1000000.0)
        {
            return null;
        }
        return lowAverage;
    }

    /**
     * Returns the weather station that has the lowest 
     * average rainfall recorded for any month (1-12).
     *
     * @return The WeatherStation object with the lowest 
     * average rainfall, or null if no data is available.
     */
    public WeatherStation lowestStation()
    {
        double average = 1000000.0;
        WeatherStation lowAverage = new WeatherStation(null);
        for (String str : weatherStations.keySet())
        {
            int month = weatherStations.get(str).getLowestMonth();
            double avgRainfall = weatherStations.get(str).getAvgForMonth(month);
            if (avgRainfall < average && avgRainfall != -1)
            {
                average = avgRainfall;
                lowAverage = weatherStations.get(str);
            }
        }
        if (average == 1000000.0)
        {
            return null;
        }
        return lowAverage;
    }
}
