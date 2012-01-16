import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws Exception {
		String oursite = GetURL();
		getPageSource(oursite);
		String thename = getFileName();
		DownloadVideo(oursite, thename);
		ConvertVideoToMp3();

	}

	public static String GetURL() {

		Scanner scan = new Scanner(System.in);

		System.out
				.println("Go ahead and enter in URL you want to download the youtube video of: ");

		String URL = scan.nextLine(); // input url

		if (!URL.startsWith("http://"))
			URL = "http://" + URL;
		if (!URL.startsWith("http://www."))
			URL = "http://www." + URL;
		return URL;

	}

	public static String getFileName() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What should I save the file as? ");
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
		File output = new File("test" + ".flv");

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

	public static void ConvertVideoToMp3() {
		convert2mp3 tomp4 = new convert2mp3();
		tomp4.conversion("test.flv");
	}
}
