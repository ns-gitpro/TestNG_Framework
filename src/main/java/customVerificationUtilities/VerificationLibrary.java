package customVerificationUtilities;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificationLibrary
{
    private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap();

    public static boolean verifyTrue(boolean condition) {
        try {
            Assert.assertTrue(condition);
            return true;
        } catch (Throwable var2) {
            addVerificationFailure(var2);
            return false;
        }
    }

    public static boolean verifyTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean verifyFalse(boolean condition) {
        try {
            Assert.assertFalse(condition);
            return true;
        } catch (Throwable var2) {
            addVerificationFailure(var2);
            return false;
        }
    }

    public static boolean verifyFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean verifyEquals(boolean actual, boolean expected) {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean verifyEquals(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean verifyEquals(Object[] actual, Object[] expected) {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (Throwable var3) {
            addVerificationFailure(var3);
            return false;
        }
    }

    public static boolean fail(String message) {
        try {
            Assert.fail(message);
            return true;
        } catch (Throwable var2) {
            addVerificationFailure(var2);
            return false;
        }
    }

    public static List<Throwable> getVerificationFailures() {
        List<Throwable> verificationFailures = (List) verificationFailuresMap.get(Reporter.getCurrentTestResult());
        return (List) (verificationFailures == null ? new ArrayList() : verificationFailures);
    }

    public static void addVerificationFailure(Throwable e) {
        List<Throwable> verificationFailures = getVerificationFailures();
        verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
        verificationFailures.add(e);
    }
}
