import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		final youtubeInterface ninja = new youtubeInterface();

		System.out
				.println("Go ahead and enter in URL you want to download the youtube video of: ");

		String URL = scan.nextLine(); // input url
		if (!URL.startsWith("http://"))
			URL = "http://" + URL;
		if (!URL.startsWith("http://www."))
			URL = "http://www." + URL;
		System.out.println("What should I save the file as? ");
		String FileName = scan.nextLine();
		String downloadedURL = youtubeInterface.downloadWebpage(URL); // download http
		String youtubeURL = youtubeInterface.FindYoutubeVideo(downloadedURL); // scan

		URL correct = new URL(youtubeURL);
		File output = new File(FileName + ".flv");

		final Downloader cat = new Downloader(correct, output);

		Runnable r = new Runnable() {
			public void run() {

				StringBuilder sb = new StringBuilder(200);

				while (cat.isRunning() == true)
					ninja.printProgBar(cat.getProgressPercent(), cat.getDownloadedLength(), cat.getLength());
			}
		};
		Thread thr1 = new Thread(r);
		thr1.start();
		cat.run();
	}

}
