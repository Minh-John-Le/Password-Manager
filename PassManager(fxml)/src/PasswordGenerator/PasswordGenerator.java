package PasswordGenerator;

public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    
    /**
     * This method generate a password String based on user customize
     * @param numLow
     * @param numUpper
     * @param numSpecial
     * @param numDigits
     * @return
     */
	public static String generatePassword(int numLow, int numUpper, int numSpecial, int numDigits)
	{
		StringBuilder result = new StringBuilder("");
		
		for(int i = 0; i < numLow; i++)
		{
			int randomIndex = (int)(Math.random() * (LOWER.length() - 1));
			char randomChar = LOWER.charAt(randomIndex);
			int location = (int)(Math.random() * result.length());
			result = result.insert(location, randomChar);
		}
		
		for(int i = 0; i < numUpper; i++)
		{
			int randomIndex = (int)(Math.random() * (UPPER.length() - 1));
			char randomChar = UPPER.charAt(randomIndex);
			int location = (int)(Math.random() * result.length());
			result = result.insert(location, randomChar);
		}
		
		for(int i = 0; i < numSpecial; i++)
		{
			int randomIndex = (int)(Math.random() * (PUNCTUATION.length() - 1));
			char randomChar = PUNCTUATION.charAt(randomIndex);
			int location = (int)(Math.random() * result.length());
			result = result.insert(location, randomChar);
		}
		
		
		for(int i = 0; i < numDigits; i++)
		{
			int randomIndex = (int)(Math.random() * (DIGITS.length() - 1));
			char randomChar = DIGITS.charAt(randomIndex);
			int location = (int)(Math.random() * result.length());
			result = result.insert(location, randomChar);
		}
		
		
		return result.toString();
	}
}
