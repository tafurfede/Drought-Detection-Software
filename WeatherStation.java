
//-------------------------------------------------------------------------
/**
 *  This class represents the basic statistics collected by one 
 *  weather observation station. Internally, a weather station 
 *  should use an array to hold 12 monthly rain totals that represent 
 *  the sum of precipitation numbers for all the days reported in 
 *  that month. It should also use a separate array to hold 12 monthly 
 *  counts that represent the number of daily records that have been 
 *  processed for that month.
 *  
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2023.11.14)
 */
public class WeatherStation
{
    //~ Fields ................................................................
    private String id;
    private double[] monthlyRainTotals;
    private int[] monthlyCounts;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created WeatherStation object.
     * 
     * @param identifier identifies the ID of the station
     */
    public WeatherStation(String identifier)
    {
        super();
        this.id = identifier;
        this.monthlyRainTotals = new double[12];
        this.monthlyCounts = new int[12];
    }


    //~ Methods ...............................................................
    /**
     * Gets the ID of the weather station.
     * 
     * @return The weather station ID.
     */    
    public String getId()
    {
        return id;
    }
    
    /**
     * Records the daily rainfall for a specific month.
     * 
     * @param month The month (1-12) for which the rainfall 
     * is recorded.
     * 
     * @param rainfall The amount of rainfall for the day.
     */
    public void recordDailyRain(int month, double rainfall)
    {
        if (month >= 1 && month <= 12)
        {
            monthlyRainTotals[month - 1] += rainfall;
            monthlyCounts[month - 1]++;
        }
    }
    
    /**
     * Gets the count of recorded rainfall values for a 
     * specific month.
     * 
     * @param month The month (1-12) for which the count is 
     * requested.
     * 
     * @return The count of recorded rainfall values for the 
     * specified month.
     */
    public int getCountForMonth(int month)
    {
        if (month >= 1 && month <= 12)
        {
            return monthlyCounts[month - 1];
        }
        return 0;
    }
    
    /**
     * Gets the average daily rainfall for a specific month.
     * 
     * @param month The month (1-12) for which the average 
     * is requested.
     * 
     * @return The average daily rainfall for the specified 
     * month. Returns -1 if no data is available.
     */
    public double getAvgForMonth(int month)
    {
        if (month >= 1 && month <= 12 && monthlyCounts[month - 1] > 0)
        {
            return monthlyRainTotals[month - 1] / monthlyCounts[month - 1];
        }
        return -1;
    }
    
    /**
     * Gets the month with the lowest average rainfall.
     * 
     * @return The month (1-12) with the lowest average 
     * rainfall. If multiple months have the same lowest 
     * average, return the earliest one. If no data is 
     * available, return the earliest month (1).
     */
    public int getLowestMonth()
    {
        int lowestMonth = -1;
        double lowestAverage = Double.MAX_VALUE;

        for (int i = 1; i <= 12; i++)
        {
            double avg = getAvgForMonth(i);
            if (avg != -1 && avg < lowestAverage)
            {
                lowestAverage = avg;
                lowestMonth = i;
            }
        }
        return (lowestMonth == -1) ? 1 : lowestMonth;
    }
}
