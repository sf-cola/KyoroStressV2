package info.kyorohiro.helloworld.stressv2.menuparts;

import java.lang.ref.WeakReference;

import info.kyorohiro.helloworld.stressv2.KyoroSetting;
//import info.kyorohiro.helloworld.stressv2.KyoroStressActivity;
import android.app.Activity;
import android.app.Dialog;
import android.os.PowerManager.WakeLock;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class NotificationOfBigEaterDialog extends Dialog {

	private RadioGroup mNotification = null;
	private RadioButton mOn = null;
	private RadioButton mOff = null;

	private Button mOK = null;
	private LinearLayout mLayout = null;
	private ViewGroup.LayoutParams mParams = 
		new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	private WeakReference<Activity> mParent = null;

	public NotificationOfBigEaterDialog(Activity owner) {
		super(owner);
		mParent = new WeakReference<Activity>(owner);
		mLayout = new LinearLayout(getContext());
		TextView label = new TextView(getContext());
		label.setText("--show notifcation--");
		mNotification = new RadioGroup(getContext());
		mOn = new RadioButton(getContext());
		mOn.setText(KyoroSetting.NOTIFICATION_ON);
		mOn.setId(1);
		mOff = new RadioButton(getContext());
		mOff.setText(KyoroSetting.NOTIFICATION_OFF);
		mOff.setId(2);
		mNotification.addView(mOn);
		mNotification.addView(mOff);

		if (KyoroSetting.NOTIFICATION_ON.equals(KyoroSetting.getNotification())) {
			mNotification.check(1);
		} else {
			mNotification.check(2);
		}

		mLayout.setOrientation(LinearLayout.VERTICAL);
		mLayout.addView(mNotification);
		mLayout.addView(label, mParams);
		TextView memo = new TextView(getContext());
		memo.setText(
				"if on, show notification"+"\n" +
				" "+"\n"
				);
		mOK = new Button(getContext());
		mOK.setText("Update");
		mOK.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v) {
			    NotificationOfBigEaterDialog.this.dismiss();
			    Activity a = mParent.get();
			    if (mOn.isChecked()) {
			    	KyoroSetting.setNotification(KyoroSetting.NOTIFICATION_ON);
			    } else {
			    	KyoroSetting.setNotification(KyoroSetting.NOTIFICATION_OFF);		    	
			    }
			    dismiss();
			}
		});
		mLayout.addView(mOK, mParams);
		mLayout.addView(memo);
		setContentView(mLayout, mParams);
	}

	public static NotificationOfBigEaterDialog createDialog(Activity owner) {
		return new NotificationOfBigEaterDialog(owner);		
	}


}

