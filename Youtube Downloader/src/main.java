import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class main {
	public static String URLtoVideo;

	public static void main(String[] args) throws Exception {
		String oursite = GetURL();
		String source = getPageSource(oursite);
		String videotitle = youtubeInterface.FindVideoTitle(source);
		System.out.println("\t" + videotitle);
		DownloadVideo(oursite, videotitle);

		ConvertVideoToMp3(videotitle);

	}

	public static String GetURL() {

		Scanner scan = new Scanner(System.in);

		System.out
				.println("Go ahead and enter in URL you want to download the youtube video of: ");

		String URL = scan.nextLine(); // input url
		URLtoVideo = URL;
		if (!URL.startsWith("http://"))
			URL = "http://" + URL;
		if (!URL.startsWith("http://www."))
			URL = "http://www." + URL;
		return URL;

	}

	public static String getFileName() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Filename? ");
		String filename = scan.nextLine();
		return filename;
	}

	public static String getPageSource(String URL) throws IOException {

		return youtubeInterface.downloadWebpage(URL);

	}

	public static void DownloadVideo(String URL, String FileName)
			throws Exception {

		final youtubeInterface ninja = new youtubeInterface();

		String youtubeURL = youtubeInterface
				.FindYoutubeVideo(getPageSource(URL)); // returns source of the
														// specified url

		URL correct = new URL(youtubeURL); // the url of the video file.
		File output = new File(FileName + ".flv");

		final Downloader cat = new Downloader(correct, output);

		Runnable r = new Runnable() {
			public void run() {

				StringBuilder sb = new StringBuilder(200);

				while (cat.isRunning() == true)
					ninja.printProgBar(cat.getProgressPercent(),
							cat.getDownloadedLength(), cat.getLength());
			}
		};
		Thread thr1 = new Thread(r);
		thr1.start();
		cat.run();
		cat.waitUntilCompleted();
	}

	public static void ConvertVideoToMp3(String input) {
		convert2mp3 tomp3 = new convert2mp3();
		tomp3.conversion(input, input);
	}
}
