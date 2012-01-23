import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.custom.CCombo;


public class Download extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Download(Composite parent, int style) {
		super(parent, style);
		
		Button btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setBounds(27, 93, 111, 20);
		btnCheckButton.setText("Check Button");
		
		Button btnCheckButton_1 = new Button(this, SWT.CHECK);
		btnCheckButton_1.setBounds(27, 119, 111, 20);
		btnCheckButton_1.setText("Check Button");
		
		Button btnCheckButton_2 = new Button(this, SWT.CHECK);
		btnCheckButton_2.setBounds(27, 145, 111, 20);
		btnCheckButton_2.setText("No video");
		
		CLabel lblFfmpegCommand = new CLabel(this, SWT.NONE);
		lblFfmpegCommand.setBounds(10, 10, 133, 26);
		lblFfmpegCommand.setText("ffmpeg command: ");
		
		StyledText styledText = new StyledText(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.SINGLE);
		styledText.setDoubleClickEnabled(false);
		styledText.setBounds(146, 10, 423, 24);
		
		Label lblQuality = new Label(this, SWT.NONE);
		lblQuality.setBounds(10, 224, 70, 20);
		lblQuality.setText("Quality:");
		
		Scale scale_1 = new Scale(this, SWT.NONE);
		scale_1.setBounds(104, 218, 210, 48);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(152, 197, 417, 20);
		
		
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
