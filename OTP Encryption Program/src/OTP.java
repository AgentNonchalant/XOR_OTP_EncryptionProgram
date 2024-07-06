import java.security.SecureRandom;

/* AUTHOR: AgentNonchalant
 * DATE: Jan 16, 2024 

 * Purpose: To provide resources for OTP encryption and decryption. Inspired by Descent098's class on 
 * github.com (credited below), which I completely reworked for my GUI implementation.

 * "https://github.com/Descent098/simple-otp/blob/master/Java/otp.java" 
 */

public class OTP {
	
/** Generates a one-time pad of random hexadecimal characters.
 * @param length - The length of the pad you want to generate.
 * @return The randomly generated one-time pad.
 */
public static String generatePad(int length){
	
    StringBuilder pad = new StringBuilder();
    SecureRandom r = new SecureRandom();
    
    for( int i = 0; i < length; i++ ){
        int randomHexChar = r.nextInt(255); //Generates a random character with a decimal value between 0 and 255 so that it is represented by exactly 2 characters in hexadecimal. 
		pad.append(String.format("%02X", randomHexChar));
    	}
    return pad.toString();
    }

/** Encrypts the plaintext using the exclusive OR (XOR) bitwise operation.
 * @param plain - The plaintext you want to encrypt.
 * @param pad - The one-time pad you want to encrypt the text with.
 * @return The resulting ciphertext.
 */
public static String encrypt(String plain, String pad){//TODO Run a check to ensure the plaintext contains only valid ASCII characters; UTF-8 characters can be easily pasted accidently

	StringBuilder ciphertext = new StringBuilder();
    pad = fromHexString(pad); //NumberFormatException may be thrown by fromHexString which will be caught where this method is called.
    
    for ( int i = 0; i < plain.length(); i++ ){//TODO same change made here as in decrypt
    	int xoredValue = plain.charAt(i) ^ pad.charAt(i); //XOR the int representations of the current plaintext and pad characters.
    	ciphertext.append(String.format("%02X", xoredValue));
    	}
    return ciphertext.toString();
    }

/** Decrypts the ciphertext using the exclusive OR (XOR) bitwise operation.
 * @param pad - The one time pad you want to decrypt the ciphertext with.
 * @param cipher - The ciphertext you want to decrypt.
 * @return The resulting plaintext.
 */
public static String decrypt(String pad, String cipher){
   
	StringBuilder plaintext = new StringBuilder();
    try {pad = fromHexString(pad);} catch (NumberFormatException e) {throw new NumberFormatException("Pad improperly formatted");} //Catches fromHexString error to specify the pad as the cause in another exception.
    try {cipher = fromHexString(cipher);} catch (NumberFormatException e) {throw new NumberFormatException("Ciphertext improperly formatted");} //Catches fromHexString error to specify the ciphertext as the cause in another exception.
    
    for ( int i = 0; i < cipher.length(); i++ ){ //TODO changed pad.length() to cipher.length() to account for an error encountered when the pad is longer than the ciphertext, at which point the count eventually exceeds the ciphertext's length
    	int xoredValue = cipher.charAt(i) ^ pad.charAt(i); //XOR the int representations of the current ciphertext and pad characters
        plaintext.append((char) xoredValue);
        }   
    return plaintext.toString();
    }

/** Converts strings of ASCII characters into hexadecimal format for user readability. 
 * @param asciiString - The ASCII string to be converted.
 * @return The resulting hexadecimal string.
 */
public static String toHexString(String asciiString) {
	
    StringBuilder hexString = new StringBuilder();
    for (char ch : asciiString.toCharArray()) {
        hexString.append(String.format("%02X", (int) ch));
    }
    return hexString.toString();
	}

/** Converts strings of hexadecimal characters into ASCII format for processing within the program.
 * @param hexString - The hexadecimal string to be converted.
 * @return The resulting ASCII string.
 * @throws NumberFormatException Exception thrown when the input string is not properly formatted in hexadecimal.  
 */
public static String fromHexString(String hexString) throws NumberFormatException{
	
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < hexString.length(); i += 2) {
        if (i + 1 < hexString.length()) { //Ensures there are at least two characters remaining
            String hexByte = hexString.substring(i, i + 2);
            result.append((char) Integer.parseInt(hexByte, 16));
        }
    }
    return result.toString();
	}
}
