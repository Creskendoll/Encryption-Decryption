import java.util.*;

public class en_de {
	public String encrypt(String s) {
		String encryptedText = "";
		Random r = new Random();
		int textLength = s.length();
		int[] asciiOfCharacters = new int[textLength];
		int[] encryptedAscii = new int[textLength];
		for (int i = 0; i < textLength; i++) {
			int randomNumber = r.nextInt(9) + 1;
			asciiOfCharacters[i] = s.charAt(i);
			if (asciiOfCharacters[i] + randomNumber > 126) {
				encryptedAscii[i] = (asciiOfCharacters[i] + randomNumber) - 95;
			} else {
				encryptedAscii[i] = asciiOfCharacters[i] + randomNumber;
			}
			encryptedText += (char) encryptedAscii[i];
			encryptedText += (char) (randomNumber + 32);
		}

		return encryptedText;
	}

	public String decrypt(String encryptedText) {
		String decryptedText = "";
		int[] alterers = new int[encryptedText.length()/2];
		int[] characters = new int[encryptedText.length()/2];
		int evenCounter = 0;
		int oddCounter = 0;
		for (int i = 0; i < encryptedText.length(); i++) {
			if(i%2 == 0){
				characters[evenCounter] = (int)encryptedText.charAt(i);
				evenCounter++;
			}else{
				alterers[oddCounter] = (int)encryptedText.charAt(i)-32;
				oddCounter++;
			}
		}
		for (int j = 0; j < characters.length; j++) {
			if(characters[j] - alterers[j] < 32){
				int ch = (int)characters[j] - alterers[j]+95;
				decryptedText += (char) ch;
			}else{
				int ch = (int)characters[j] - alterers[j];
				decryptedText += (char) ch;	
			}
		}

		return decryptedText;
	}

}
