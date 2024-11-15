import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 *  This class is where testing methods will be written, making sure 
 *  the methods and the code written run as intended
 *  
 *  Summarize what your test objectives are.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2023.11.14)
 */
public class WeatherStationTest
    extends TestCase
{
    //~ Fields ................................................................
    private WeatherStation station;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherStationTest test object.
     */
    public WeatherStationTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        station = new WeatherStation("TestStation");
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * this testing method tests the method written in the 
     * WeatherStation class. This method tests the getId() 
     * method, more specifically, this test method get's the
     * id for the station, checking if true. 
     */
    public void testGetId() 
    {
        // set up conditions
        
        // call the method 
        
        // make assertions and test expectations
        assertThat("TestStation").isEqualTo(station.getId());
    }
    
    /**
     * this testing method tests the recordDailyRain() method 
     * written in the WeatherStation class. More specifically 
     * this method tests that the code works by recording the 
     * amount of rain in one day, and then returning the 
     * information when asked for. This method tests to retrieve
     * the amount of rainfall that fell in a specific month 1-12.
     */
    public void testRecordDailyRain01()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(3, 12.5);
        // make assertions and test expectations
        assertThat(1).isEqualTo(station.getCountForMonth(3));
        assertThat(station.getAvgForMonth(3))
            .isEqualTo(12.5, within(0.001));
    }
    
    /**
     * this testing method tests the recordDailyRain() method 
     * written in the WeatherStation class. More specifically 
     * this method tests that the code works by recording the 
     * amount of rain in one day, and then returning the 
     * information when asked for. This method tests to retrieve
     * the amount of rainfall that fell in a specific month 1-12.
     */
    public void testRecordDailyRain02()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(15, 12.5);
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(15));
        assertThat(station.getAvgForMonth(15)).isEqualTo(-1);
    }
    
    /**
     * this testing method tests the recordDailyRain() method
     * written in the WeatherStation class. More specifically
     * this method tests that the code works by recording the
     * amount of rain in one day, and then returning the
     * information when asked for, this tests when the month
     * is not in between the parameters.
     */
    public void testRecordDailyRain03()
    {
        // set up conditions
        
        // call the method
        station.recordDailyRain(0, 3.5);
        
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(0));
        assertThat(station.getAvgForMonth(0)).isEqualTo(-1);
    }
    
    /**
     * this method tests the getCountForMonth() method. Returns the 
     * number of daily rainfall values that have been recorded for 
     * the specified month (a number 1-12). Return zero when no 
     * values have been recorded for the specified month. More
     * specifically, this method tests when there's several rainfall
     * in one month
     */
    public void testGetCountForMonth01()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(5, 20.0);
        station.recordDailyRain(5, 7.0);
        station.recordDailyRain(5, 2.0);
        station.recordDailyRain(5, 6.0);
        station.recordDailyRain(5, 3.21);
        station.recordDailyRain(5, 5.0);
        // make assertions and test expectations
        assertThat(6).isEqualTo(station.getCountForMonth(5));
    }
    
    /**
     * this method tests the getCountForMonth() method. Returns the 
     * number of daily rainfall values that have been recorded for 
     * the specified month (a number 1-12). Return zero when no 
     * values have been recorded for the specified month. More
     * specifically, this method tests when there's no rainfall
     * in one month
     */
    public void testGetCountForMonth02()
    {
        // set up conditions
        
        // call the method 
        
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(5));
    }
    
    /**
     * this method tests the getCountForMonth() method. Returns the
     * number of daily rainfall values that have been recorded for
     * the specified month (a number 1-12). Return zero when no
     * values have been recorded for the specified month. More
     * specifically, this method tests when there's no rainfall
     * in one month, and outside the boundaries
     */
    public void testGetCountForMonth03()
    {
        // set up conditions
        
        // call the method
        
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(14));
    }
    
    /**
     * this method tests the getCountForMonth() method. Returns the 
     * number of daily rainfall values that have been recorded for
     * the specified month (a number 1-12). Return zero when no
     * values have been recorded for the specified month. More 
     * specifically, this method tests when there's several rainfall
     * in one month, but the month is outside the boundaries
     */
    public void testGetCountForMonth04()
    {
        // set up conditions

        // call the method
        station.recordDailyRain(0, 20.0);
        station.recordDailyRain(0, 7.0);
        station.recordDailyRain(0, 2.0);
        station.recordDailyRain(0, 6.0);
        station.recordDailyRain(0, 3.21);
        station.recordDailyRain(0, 5.0);
        
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(0));
    }
    
    /**
     * this method tests the getCountForMonth() method. Returns the 
     * number of daily rainfall values that have been recorded for 
     * the specified month (a number 1-12). Return zero when no 
     * values have been recorded for the specified month. More
     * specifically, this method tests when there's several rainfall
     * in one month, but the month is outside the boundaries
     */
    public void testGetCountForMonth05()
    {
        // set up conditions
 
        // call the method 
        station.recordDailyRain(15, 20.0);
        station.recordDailyRain(15, 7.0);
        station.recordDailyRain(15, 2.0);
        station.recordDailyRain(15, 6.0);
        station.recordDailyRain(15, 3.21);
        station.recordDailyRain(15, 5.0);
        
        // make assertions and test expectations
        assertThat(0).isEqualTo(station.getCountForMonth(15));
    }
    
    
    /**
     * Returns the average daily rainfall for the specified month 
     * (a number 1-12). This is the total rainfall across all 
     * reported daily values for that month, divided by the number 
     * of daily values that have been recorded for that month. 
     * Return -1 if no rainfall amounts have been recorded for 
     * the specified month.
     */
    public void testGetAvgForMonth()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(1, 5.0);
        station.recordDailyRain(1, 2.5);
        station.recordDailyRain(1, 7.5);

        // make assertions and test expectations
        assertThat(station.getAvgForMonth(1)).isEqualTo(
            5.0, within(0.001));
    }
    
    /**
     * Returns the average daily rainfall for the specified month 
     * (a number 1-12). This is the total rainfall across all 
     * reported daily values for that month, divided by the number 
     * of daily values that have been recorded for that month. 
     * Return -1 if no rainfall amounts have been recorded for 
     * the specified month.
     */
    public void testGetAvgForMonth02()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(10, 5.0);
        station.recordDailyRain(12, 2.5);
        station.recordDailyRain(3, 7.5);

        // make assertions and test expectations
        assertThat(station.getAvgForMonth(1)).isEqualTo(-1.0);
    }
    
    /**
     * Returns the average daily rainfall for the specified month 
     * (a number 1-12). This is the total rainfall across all 
     * reported daily values for that month, divided by the number 
     * of daily values that have been recorded for that month. 
     * Return -1 if no rainfall amounts have been recorded for 
     * the specified month.
     */
    public void testGetAvgForMonth03()
    {
        // set up conditions
        
        // call the method
        station.recordDailyRain(2, -5.0);
        station.recordDailyRain(2, -2.5);
        station.recordDailyRain(2, -7.5);

        // make assertions and test expectations
        assertThat(station.getAvgForMonth(2)).isEqualTo(-5.0);
    }
    
    /**
     * Returns the average daily rainfall for the specified month 
     * (a number 1-12). This is the total rainfall across all 
     * reported daily values for that month, divided by the number 
     * of daily values that have been recorded for that month. 
     * Return -1 if no rainfall amounts have been recorded for 
     * the specified month.
     */
    public void testGetAvgForMonth04()
    {
        // set up conditions
        
        // call the method
        station.recordDailyRain(5, 0);
        station.recordDailyRain(5, 0);
        station.recordDailyRain(5, 0);

        // make assertions and test expectations
        assertThat(station.getAvgForMonth(5)).isEqualTo(0);
    }
    
    /**
     * Returns the number of the month (a number 1-12) indicating 
     * the month that had the lowest average rainfall recorded at 
     * this station. If multiple months have the same lowest 
     * rainfall average, return the earliest one (the lowest 
     * month number). If no rainfall records have been entered 
     * for any month, return the earliest month as well (1).
     */
    public void testGetLowestMonth()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(1, 5.0);
        station.recordDailyRain(2, 2.5);
        station.recordDailyRain(3, 7.5);
        station.recordDailyRain(4, 1.0);
        station.recordDailyRain(5, 10.5);
        station.recordDailyRain(6, 9.5);
        station.recordDailyRain(7, 4.0);
        station.recordDailyRain(8, 12.5);
        station.recordDailyRain(9, 8.5);
        station.recordDailyRain(10, 0.2);
        station.recordDailyRain(11, 1.5);
        station.recordDailyRain(12, 8.5);
        // make assertions and test expectations
        assertThat(10).isEqualTo(station.getLowestMonth());
    }
    
    /**
     * Returns the number of the month (a number 1-12) indicating 
     * the month that had the lowest average rainfall recorded at 
     * this station. If multiple months have the same lowest 
     * rainfall average, return the earliest one (the lowest 
     * month number). If no rainfall records have been entered 
     * for any month, return the earliest month as well (1).
     */
    public void testGetLowestMonthWithNoData()
    {
        // set up conditions
        
        // call the method 

        // make assertions and test expectations
        assertThat(1).isEqualTo(station.getLowestMonth());
    }
    
    /**
     * Returns the number of the month (a number 1-12) indicating 
     * the month that had the lowest average rainfall recorded at 
     * this station. If multiple months have the same lowest 
     * rainfall average, return the earliest one (the lowest 
     * month number). If no rainfall records have been entered 
     * for any month, return the earliest month as well (1).
     */
    public void testGetLowestMonthWithSameLowestAverage()
    {
        // set up conditions
        
        // call the method 
        station.recordDailyRain(1, 5.0);
        station.recordDailyRain(2, 2.5);
        station.recordDailyRain(3, 7.5);
        station.recordDailyRain(4, 0.2);
        station.recordDailyRain(5, 10.5);
        station.recordDailyRain(6, 9.5);
        station.recordDailyRain(7, 4.0);
        station.recordDailyRain(8, 12.5);
        station.recordDailyRain(9, 8.5);
        station.recordDailyRain(10, 0.2);
        station.recordDailyRain(11, 1.5);
        station.recordDailyRain(12, 8.5);
        
        // make assertions and test expectations
        assertThat(4).isEqualTo(station.getLowestMonth());
        assertThat(station.getLowestMonth()).isNotEqualTo(10);
    }
    
    /**
     * Returns the number of the month (a number 1-12) indicating 
     * the month that had the lowest average rainfall recorded at 
     * this station. If multiple months have the same lowest 
     * rainfall average, return the earliest one (the lowest 
     * month number). If no rainfall records have been entered 
     * for any month, return the earliest month as well (1).
     */
    public void testGetLowestMonthWithNegativeValues()
    {
        // set up conditions
        
        // call the method
        station.recordDailyRain(1, -5.0);
        station.recordDailyRain(2, -2.5);
        station.recordDailyRain(3, -3.5);

        // make assertions and test expectations
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
    
    /**
     * Returns the number of the month (a number 1-12) indicating 
     * the month that had the lowest average rainfall recorded at 
     * this station. If multiple months have the same lowest 
     * rainfall average, return the earliest one (the lowest 
     * month number). If no rainfall records have been entered 
     * for any month, return the earliest month as well (1).
     */
    public void testGetLowestMonthWithZeroValues()
    {
        // set up conditions
        
        // call the method
        station.recordDailyRain(1, 0.0);
        station.recordDailyRain(2, 0.0);
        station.recordDailyRain(3, 0.0);

        // make assertions and test expectations
        assertThat(station.getLowestMonth()).isEqualTo(1);
    }
}
