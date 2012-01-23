import java.awt.Toolkit;
import java.io.IOException;
import java.util.regex.Matcher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class UDownloadW extends Thread {
	private static Table yulist;
	private static Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Display display = Display.getDefault();
		Shell shell = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setBackgroundImage(SWTResourceManager
				.getImage("C:\\Users\\Demi\\Pictures\\My Art\\PNG\\Untitled-1.jpg"));
		shell.setBackground(SWTResourceManager.getColor(20, 84, 255));
		shell.setSize(576, 240);
		shell.setText("UDownload");

		final CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
		tabFolder.setBackgroundMode(SWT.INHERIT_DEFAULT);
		tabFolder.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NONE));

		tabFolder.setSimple(false);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(0, 0, 0));
		tabFolder.setBackground(SWTResourceManager.getColor(255, 255, 255));

		tabFolder.setBounds(0, 48, 571, 159);
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(153, 204,
				255));

		final CTabItem tbtmDownload = new CTabItem(tabFolder, SWT.NONE);
		tbtmDownload.setText("Download");

		final CTabItem tbtmConvert = new CTabItem(tabFolder, SWT.NONE);
		tbtmConvert.setText("Convert");

		final CTabItem tbtmActivity = new CTabItem(tabFolder, SWT.NONE);
		tbtmActivity.setText("Activity");

		yulist = new Table(tabFolder, SWT.FULL_SELECTION | SWT.HIDE_SELECTION
				| SWT.VIRTUAL);
		yulist.setHeaderVisible(true);
		yulist.setLinesVisible(true);
		tbtmActivity.setControl(yulist);
		yulist.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		final TableColumn column1 = new TableColumn(yulist, SWT.NONE);
		column1.setWidth(100);
		column1.setText("Task Type");

		final TableColumn column2 = new TableColumn(yulist, SWT.NONE);
		final TableColumn column3 = new TableColumn(yulist, SWT.NONE);
		final TableColumn column4 = new TableColumn(yulist, SWT.NONE);

		column2.setWidth(240);
		column2.setText("\tDetails");

		column3.setWidth(195);
		column3.setText("Output");
		column4.setWidth(30);
		column4.setText("%");

		final Composite composite = new Composite(tabFolder, SWT.NONE);

		CLabel lblDownloadQuality = new CLabel(composite, SWT.NONE);
		lblDownloadQuality.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblDownloadQuality.setBounds(184, 98, 60, 21);
		lblDownloadQuality.setText("Quality:");

		CCombo combo = new CCombo(composite, SWT.None);
		combo.setEditable(false);

		combo.setItems(new String[] { "High", "Medium", "Low" });
		combo.select(0);
		combo.setBounds(261, 98, 100, 28);
		combo.pack();
		combo.setTouchEnabled(false);
		Label lblAddTheUrl = new Label(composite, SWT.NONE);
		lblAddTheUrl.setFont(SWTResourceManager
				.getFont("Segoe UI", 9, SWT.NONE));
		lblAddTheUrl.setBounds(52, 10, 500, 20);
		lblAddTheUrl
		.setText("Add the URL of the video you want to download from Youtube");
		Button btnAddUrl = new Button(composite, SWT.NONE);
		btnAddUrl.setBounds(430, 36, 103, 30);
		btnAddUrl.setText("Add to Queue");

		final StyledText url = new StyledText(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		url.setBounds(52, 40, 369, 26);
		url.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.stateMask == SWT.CTRL)
						&& (e.keyCode == 'A' || e.keyCode == 'a')) {

					url.selectAll();

				}
			}
		});
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setForeground(SWTResourceManager.getColor(245, 184, 0));
		label.setBounds(34, 218, 486, 72);
		final Label isok = new Label(composite, SWT.NONE);
		isok.setBounds(155, 72, 566, 26);
		isok.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.BOLD));
		isok.setForeground(SWTResourceManager.getColor(245, 0, 0));
		isok.setText("");

		btnAddUrl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				String webpage;

				try {

					webpage = youtubeInterface.downloadWebpage(url.getText());

					TableItem tableItem = new TableItem(yulist, SWT.NONE);
					url.setText("");
					isok.setBounds(261, 72, 566, 26);
					isok.setForeground(SWTResourceManager.getColor(0, 200, 0));
					isok.setText("Added to Queue");
					webpage = webpage.replace("&#39;", "'");
					webpage = webpage.replace("&amp;", "&");
					webpage = webpage.replace("&quot;", "\"");
					tableItem.setText(0, "Download");
					tableItem.setText(1,
							youtubeInterface.FindVideoTitle(webpage));
				} catch (Exception e1) {

					isok.setBounds(155, 72, 566, 26);
					isok.setForeground(SWTResourceManager.getColor(245, 0, 0));
					isok.setText("Error! Are you sure you entered a valid Youtube URL? ");
					e1.printStackTrace();
				}

			}
		});
		tabFolder.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);

				// FIX
				if (tbtmActivity.isShowing()) {
					tbtmDownload.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.BOLD));
					tbtmConvert.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
					tbtmActivity.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
				} else if (tbtmActivity.isShowing()) {
					tbtmDownload.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
					tbtmConvert.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.BOLD));
					tbtmActivity.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
				} else if (tbtmActivity.isShowing()) {
					tbtmConvert.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
					tbtmDownload.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.NONE));
					tbtmActivity.setFont(SWTResourceManager.getFont("Segoe UI",
							9, SWT.BOLD));
				}
			}

			public void widgetSelected(SelectionEvent e) {
				tbtmDownload.setControl(composite);

			}
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**/

}
