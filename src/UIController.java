import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UIController {
	
	public static boolean isFileExist(String fileName) {
		boolean ret = false;
		Path fp =Paths.get(fileName);
		ret = Files.exists(fp);
		return ret;
	}

	public static String readFromFile(String fileName) {
		String strFromFile = new String();
		try {
			Path fp =Paths.get(fileName);
			if (Files.exists(fp)) {
				byte[] data = Files.readAllBytes(fp); 
				if (data.length == MorrisBoard.TOTALPOS) {
					for (int i = 0; i < data.length; i++) {
						char ch = (char) data[i];
						strFromFile += ch;
					}
				}
			}			
		} catch  ( IOException e) {
			
        }
		return strFromFile;
	}

	public static void writeToFile(String fileName, String content) {
		try {
			Path fp =Paths.get(fileName);
			byte[] data = content.getBytes();
			Files.write(fp, data); 	
		} catch  ( IOException e) {
			
        }
	}

}
