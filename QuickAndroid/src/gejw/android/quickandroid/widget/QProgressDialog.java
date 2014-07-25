package gejw.android.quickandroid.widget;

import gejw.android.quickandroid.R;
import gejw.android.quickandroid.ui.adapter.UIAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QProgressDialog extends Dialog {
	public QProgressDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public QProgressDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public QProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private UIAdapter uiAdapter;
	private ProgressBar progressBar;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progressbar);
		TextView msg = (TextView) findViewById(R.id.msg);
		if(message!=null)
			msg.setText(message);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);

		uiAdapter = UIAdapter.getInstance(getContext());

		uiAdapter.setTextSize(msg, 25);
		uiAdapter.setPadding(msg, 20, 10, 20, 10);
		uiAdapter.setMargin(progressBar, 30, 30, 10, 10, 10, 10);
	}

	public QProgressDialog setMessage(String txt) {
		message = txt;
		return this;
	}
}
