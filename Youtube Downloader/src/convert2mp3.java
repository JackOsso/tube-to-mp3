import java.io.BufferedReader;
import java.io.InputStreamReader;

public class convert2mp3 {

	{

	}

	public void conversion(String t) {
		try {
			String line;
			Process p = Runtime
					.getRuntime()
					.exec("ffmpeg -i test.flv -vn -ar 44100 -ac 2 -ab 320 -f mp3 sound.mp3");
			
			BufferedReader bri = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
			System.out.println("Done.1");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
