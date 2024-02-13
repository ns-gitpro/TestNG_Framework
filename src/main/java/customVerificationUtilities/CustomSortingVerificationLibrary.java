package customVerificationUtilities;

import com.google.common.collect.Comparators;
import com.google.common.collect.Ordering;
import commonUtilities.FunctionLibrary;
import commonUtilities.PrintUtility;

import java.util.*;


public class CustomSortingVerificationLibrary
{

    public static void verifyListAscendingOrder(List<String> list)
    {
        long start = System.currentTimeMillis();
        try
        {
            Boolean result = Comparators.isInOrder(list, Comparator.<String> naturalOrder());

           if(result==true)
           {
               long finish = System.currentTimeMillis();
               long totalTime = finish - start;
               PrintUtility.logPass(PrintUtility.printWaitTime("verifyListAscendingOrder(): ", totalTime, "Found List Order : Ascending"));
           }
           else
           {
               long finish = System.currentTimeMillis();
               long totalTime = finish - start;
               PrintUtility.logFail(PrintUtility.printWaitTime("verifyListAscendingOrder(): ",totalTime, "Found List Order : Unordered"));
           }

        }
        catch (Exception var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyListAscendingOrder(): ",totalTime, "Error Occurred :")+var3);
        }
    }

    public static void verifyListDescendingOrder(List<String> list)
    {
        long start = System.currentTimeMillis();
        List<String> unOrdered = new ArrayList<String>(list);
        try
        {
            Collections.sort(list, Ordering.natural().nullsLast().reverse());
            FunctionLibrary.softAssertions.assertThat(unOrdered).containsExactly(list.toArray(new String[0]));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyListDescendingOrder(): ", totalTime, "Found List Order : Descending"));
        }
        catch (Exception var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyListDescendingOrder(): ",totalTime, "Error Occurred :")+var3);
        }
    }

    public static void verifyListsEqualIgnoringOrder(List<String> list1, List<String> list2)
    {
        long start = System.currentTimeMillis();
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        if(set1.equals(set2))
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyListsEqualIgnoringOrder(): ", totalTime, "Found both list are equal"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyListsEqualIgnoringOrder(): ", totalTime, "Found both list are not equal"));
        }
    }

}
