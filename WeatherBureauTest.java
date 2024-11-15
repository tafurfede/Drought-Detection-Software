import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.util.Scanner;

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
public class WeatherBureauTest
    extends TestCase
{
    //~ Fields ................................................................
    private WeatherBureau bureau;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WeatherBureauTest test object.
     */
    public WeatherBureauTest()
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
        bureau = new WeatherBureau();
    }
    
    // -----------------------------------------------------------    
    /**
     * Tests the functionality of the recordDailySummary method 
     * in the Bureau class. It verifies whether the daily summary 
     * is correctly recorded and the associated WeatherStation 
     * object is updated accordingly. More specifically it tests 
     * when precipitation in a certain month when the month 
     * evaluated is between 1-12
     */
    public void testRecordDailySummary()
    {
        // set up conditions
        
        // call the method
        bureau.recordDailySummary("Station1 1.0 2.0 3.0 3/10/21 5.0");
        WeatherStation station = bureau.getStation("Station1");

        // Assert the recorded data in the station
        assertThat(station.getAvgForMonth(3))
            .isEqualTo(5, within(0.001));
    }
    
    /**
     * Tests the functionality of the recordDailySummary method 
     * in the Bureau class. It verifies whether the daily summary 
     * is correctly recorded and the associated WeatherStation 
     * object is updated accordingly. More specifically it tests 
     * when precipitation in a certain month when the month 
     * evaluated is not between 1-12
     */
    public void testRecordDailySummary02() 
    {
        // set up conditions
        
        // call the method
        bureau.recordDailySummary("Station2 1.0 2.0 3.0 4/15/21 -1");
        WeatherStation station = bureau.getStation("Station2");

        // Assert the recorded data in the station
        assertThat(station.getAvgForMonth(4)).isEqualTo(-1);
    }
    
    /**
     * Tests the functionality of the recordDailySummary method 
     * in the Bureau class. It verifies whether the daily summary 
     * is correctly recorded and the associated WeatherStation 
     * object is updated accordingly. More specifically it tests 
     * when precipitation in a certain month when the month 
     * evaluated is not between 1-12
     */
    public void testRecordDailySummary03()
    {
        // Set up conditions
        
        // Call the method
        bureau.recordDailySummary("Station2 1.0 2.0 3.0 4/15/21 -1");
        WeatherStation station = bureau.getStation("Station2");

        // Assert the recorded data in the station
        if (station != null)
        {
            assertThat(station.getAvgForMonth(4)).isEqualTo(-1);
        } 
        else
        {
            fail("Station is null");
        }
    }
    
    /**
     * this method tests the recordDailySummary() method, in specific
     * cases and instances that occur in the logic of the code
     */
    public void testRecordDailySummary04()
    {
        // Set up conditions
        String input = "Station1 1.0 2.0 3.0 4/15/21 5.0";

        // Call the method
        bureau.recordDailySummary(input);

        // Assert the recorded data in the station
        WeatherStation station = bureau.getStation("Station1");
        assertThat(station.getCountForMonth(4)).isEqualTo(1);
        assertThat(station.getAvgForMonth(4)).isEqualTo(5.0, within(0.001));
    }
    
    /**
     * this method tests the recordDailySummary() method, in specific
     * cases and instances that occur in the logic of the code
     */
    public void testRecordDailySummary05() 
    {
        // Set up conditions
        String input = "Station2 1.0 2.0 3.0 4/15/21 -1";

        // Call the method
        bureau.recordDailySummary(input);

        // Assert the recorded data in the station
        WeatherStation station = bureau.getStation("Station2");
        assertThat(station.getCountForMonth(4)).isEqualTo(0);
        assertThat(station.getAvgForMonth(4)).isEqualTo(-1);
    }
    
    /**
     * this method tests the recordDailySummary() method, in specific
     * cases and instances that occur in the logic of the code
     */
    public void testRecordDailySummary06() 
    {
        // Set up conditions
        String input1 = "Station1 1.0 2.0 3.0 4/15/21 5.0";
        String input2 = "Station2 1.0 2.0 3.0 4/15/21 -1";

        // Call the method
        bureau.recordDailySummary(input1);
        bureau.recordDailySummary(input2);

        // Assert the recorded data in the station1
        WeatherStation station1 = bureau.getStation("Station1");
        assertThat(station1.getCountForMonth(4)).isEqualTo(1);
        assertThat(station1.getAvgForMonth(4)).isEqualTo(5.0, within(0.001));

        // Assert the recorded data in the station2
        WeatherStation station2 = bureau.getStation("Station2");
        assertThat(station2.getCountForMonth(4)).isEqualTo(0);
        assertThat(station2.getAvgForMonth(4)).isEqualTo(-1);
    }
    
    /**
     * Takes a Scanner object as a parameter that represents an 
     * input data source, such as a file containing a series of 
     * daily summary records for one or more weather stations. 
     * Record all of the daily summaries from the input source.
     * More specifically, this test the method where the data 
     * from will be retrieved every day from different Stations.
     */
    public void testRecordDailySummaries()
    {
        // set up conditions
        String inputString = "Station3 1.0 2.0 3.0 5/20/21 3.0 \n" + 
            "Station4 1.0 2.0 3.0 6/5/21 8.0";
        Scanner input = new Scanner(new java.io.StringReader(inputString));
        
        // call the method
        bureau.recordDailySummaries(input);
        WeatherStation station3 = bureau.getStation("Station3");
        WeatherStation station4 = bureau.getStation("Station4");

        // Assert the recorded data in the station
        assertThat(station3.getAvgForMonth(5)).isEqualTo(3.0);
        assertThat(station4.getAvgForMonth(6)).isEqualTo(8.0);
    }
    
    /**
     * Tests the getStation method when there is no station.
     */
    public void testGetStation()
    {
        // Arrange
        WeatherBureau weatherBureau = new WeatherBureau();

        // Act
        WeatherStation actualStation = weatherBureau.getStation(null);

        // Assert
        assertThat(actualStation).isEqualTo(null);
    }
    
    /**
     * Tests the lowestStation method when there is data available.
     */
    public void testLowestStation00() 
    {
        // Set up conditions
        String input1 = "Station1 1.0 2.0 3.0 4/15/21 5.0";
        String input2 = "Station2 1.0 2.0 3.0 4/15/21 3.0";

        // Call the method
        bureau.recordDailySummary(input1);
        bureau.recordDailySummary(input2);
        WeatherStation lowest = bureau.lowestStation();

        // Assert the result
        assertThat(lowest.getId()).isEqualTo("Station2");
    }

    /**
     * Tests the lowestStation method when there is no data available.
     */
    public void testLowestStation01()
    {
        // Set up conditions

        // Call the method
        WeatherStation lowest = bureau.lowestStation();

        // Assert the result
        assertThat(lowest).isNull();
    }
    
    /**
     * Tests the lowestStation method when there is no data for any month.
     */
    public void testLowestStation02()
    {
        // set up conditions
    
        // call the method
    
        // Assert that the result is null
        assertThat(bureau.lowestStation()).isEqualTo(null);
    }
    
    /**
     * Tests the lowestStation method when there is no data.
     */
    public void testLowestStation03()
    {
        // set up conditions
        
        // call the method
        WeatherStation lowestStation = bureau.lowestStation(1);

        // Assert the recorded data in the station
        assertThat(lowestStation).isEqualTo(null);
    }
    
    /**
     * Tests the lowestStation method for a specific month with 
     * data available.
     */
    public void testLowestStation04() 
    {
        // Set up conditions
        String input1 = "Station1 1.0 2.0 3.0 4/15/21 5.0";
        String input2 = "Station2 1.0 2.0 3.0 4/15/21 3.0";

        // Call the method
        bureau.recordDailySummary(input1);
        bureau.recordDailySummary(input2);
        WeatherStation lowest = bureau.lowestStation(4);

        // Assert the result
        assertThat(lowest.getId()).isEqualTo("Station2");
    }
    
    /**
     * Tests the lowestStation method for a specific month when 
     * there is no data available.
     */
    public void testLowestStation05()
    {
        // Set up conditions

        // Call the method
        WeatherStation lowest = bureau.lowestStation(4);

        // Assert the result
        assertThat(lowest).isNull();
    }
    
    /**
     * Tests the lowestStation method for a specific month with 
     * data available.
     */
    public void testLowestStation06() 
    {
        // Set up conditions
        String input1 = "Station1 1.0 2.0 3.0 4/15/21 5.0";
        String input2 = "Station2 1.0 2.0 3.0 4/15/21 3.0";

        // Call the method
        bureau.recordDailySummary(input1);
        bureau.recordDailySummary(input2);
        WeatherStation lowest = bureau.lowestStation(4);

        // Assert the result
        assertThat(lowest.getId()).isEqualTo("Station2");
    }
    
    /**
     * Tests the lowestStation method when there is data available.
     */
    public void testLowestStation07() 
    {
        // Set up conditions
        String input1 = "Station1 1.0 2.0 3.0 4/15/21 5.0";
        String input2 = "Station2 1.0 2.0 3.0 4/15/21 3.0";

        // Call the method
        bureau.recordDailySummary(input1);
        bureau.recordDailySummary(input2);
        WeatherStation lowest = bureau.lowestStation();

        // Assert the result
        assertThat(lowest.getId()).isEqualTo("Station2");
    }
}
