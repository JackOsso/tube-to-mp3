import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class youtubeInterface {
	
		public static String FindYoutubeVideo(String input) {
		//finds video
		String ResultString = null;
		try {
			Pattern regex = Pattern.compile("http:\\\\/\\\\/.{0,100}generate_.{0,1000}\";");
			Matcher regexMatcher = regex.matcher(input);
			if (regexMatcher.find()) {
				ResultString = regexMatcher.group();
			} 
		} catch (PatternSyntaxException ex) {
			// Syntax error in the regular expression
		}

		//before we return, we find the REAL video...
		//do some ninja decoding sh!t to find the actual video file.
		ResultString = ResultString.substring(0, ResultString.length() - 2);
		ResultString = ResultString.replace("\\/", "/");
		ResultString = ResultString.replace("\\u0026","&");
		ResultString = ResultString.replace("generate_204", "videoplayback");
		return ResultString;

	}


		public static String downloadWebpage(String link) throws IOException {

			URL url = new URL(link);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			BufferedReader breader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			StringBuilder stringBuilder = new StringBuilder();

			String line;
			while ((line = breader.readLine()) != null) {
				stringBuilder.append(line);
			}

		
			return stringBuilder.toString();
		}	
	public void printProgBar(int percent, int dld, int total){
	    StringBuilder bar = new StringBuilder("[");
	    StringBuilder bytes = new StringBuilder("50");
	    for(int i = 0; i < 50; i++){
	    
	        if( i < (percent/2)){
	            bar.append("=");
	        }else if( i == (percent/2)){
	            bar.append(">");
	        }else{
	            bar.append(" ");
	        }
	    }

	    bar.append("] " + percent + "%   ");
	    
	    System.out.print("\r" + bar.toString() + dld/1024 + "kB of " + total/1024 + "kB");


	}



}