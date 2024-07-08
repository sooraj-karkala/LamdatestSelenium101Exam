package Testscripts;

import org.testng.annotations.DataProvider;


public class TestDataProvider 
{
    @DataProvider(name = "browserAndOsProvider")
    public static Object[][] provideBrowserAndOs() 
    {
        return new Object[][] {
            {"Chrome", "88.0", "Windows 10"},
            {"MicrosoftEdge", "87.0", "macOS Sierra"},
            //{"Firefox", "82.0", "Windows 7"},
            //{"Internet Explorer", "11.0", "Windows 10"}            
        };
        
//        String data[][]= {                                          
//        		{"Chrome", "88", "Windows 10"},
//                {"MicrosoftEdge", "87", "macOS Sierra"},
//                {"Firefox", "82", "Windows 7"},
//                {"Internet Explorer", "11", "Windows 10"}
//			};
//
//        return data;
    }
}
