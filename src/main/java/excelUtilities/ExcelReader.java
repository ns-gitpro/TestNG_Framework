package excelUtilities;

import com.creditdatamw.zerocell.Reader;

import java.io.File;
import java.util.List;


public final class ExcelReader
{
    private ExcelReader()
    {

    }

    private static Class pojo_className_Generic;
    private static String excelFileName_Generic;
    private static String sheetName_Generic;

    public static Object[][] getTestData(Class pojo_className_Generic, String excelFileName_Generic, String sheetName_Generic)
    {
        List<Class> td =  Reader.of(pojo_className_Generic)
                .from(new File(excelFileName_Generic))
                .sheet(sheetName_Generic)
                .skipHeaderRow(true)
                .list();

        Object[][] data = new Object[td.size()][1];
        for (int i = 0; i < td.size(); i++)
        {
            data[i][0] = td.get(i);
        }
        return data;
    }
}
