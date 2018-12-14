import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BondPriceReader {

	private static final String FILENAME = "/Users/yannipeng/Desktop/testBigFile.drl";
	private static final String CUSIP_REGEX = "^[a-zA-Z0-9]{8,}$";

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String lastCusip = null;
			String latestPrice = null;
			String lastLine = "";

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.matches(CUSIP_REGEX)){
					if(lastCusip==null) {
						lastCusip = sCurrentLine;
					}
					if(!lastCusip.equals(sCurrentLine)){
						System.out.println(lastCusip+":"+latestPrice);
						lastCusip = sCurrentLine;
					}
				} else {
					latestPrice = sCurrentLine;
				}
				lastLine = sCurrentLine;
			}
			if(!lastLine.matches(CUSIP_REGEX))
				System.out.println(lastCusip+":"+latestPrice);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}