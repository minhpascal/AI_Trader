import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import xAPI.ApiCandleConverterTest;
import xAPI.CalendarSelectorTest;
import xAPI.CandleChartDrawerTest;
import xAPI.ChartRangeInfoTest;
import xAPI.FireBaseHandlerTest;
import xAPI.MainActivityRobolectricTest;
import xAPI.PeriodSelectorTest;
import xAPI.SymbolDataTest;
import xAPI.SymbolRecordTest;
import xAPI.SymbolTest;
import xAPI.xApiConnectionLoginRobolectricTest;
import xAPI.xApiConnectionLoginTest;
import xAPI.xApiRangeDataLoaderTest;
import xAPI.xApiSymbolLoaderTest;

/**
 * Created by Piotr on 2017-01-06.
 * Main point of test running
 * It contain tests list for AI_Trader unit testing
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
                     SymbolDataTest.class,
                     SymbolRecordTest.class,
                     SymbolTest.class,
                     PeriodSelectorTest.class,
                     FireBaseHandlerTest.class,
                     CandleChartDrawerTest.class,
                     ChartRangeInfoTest.class,
                     xApiRangeDataLoaderTest.class,
                     CalendarSelectorTest.class,
                     xApiConnectionLoginTest.class,
                     ApiCandleConverterTest.class,
                     MainActivityRobolectricTest.class,
                     xApiConnectionLoginRobolectricTest.class,
                     xApiSymbolLoaderTest.class



})
public class xAPITestSuite {
}