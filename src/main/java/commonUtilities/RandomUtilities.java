package commonUtilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
public class RandomUtilities
{
    private RandomUtilities()
    {

    }

    public static String generateRandomString(int length)
    {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString.substring(0, length);
    }

    public static int generateRandomNumber(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String generateAlphaNumeric(int minLengthInclusive, int maxLengthExclusive)
    {
        RandomStringUtils random = new RandomStringUtils();
        return random.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);
    }

}
