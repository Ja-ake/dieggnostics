package edu.team1540.recycle.basket.home;

import java.util.concurrent.atomic.AtomicBoolean;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.basket.stand.SubmitFragment;
import edu.team1540.recycle.user.GlobalData;

public class HomeFragment extends ScoutingFragment {

	final SubmitFragment submit;
	final GlobalData loggedIn;
	final RecyclingActivity act = (RecyclingActivity) this.getActivity();
	
	public HomeFragment(SubmitFragment frag, GlobalData l) {
		super(R.layout.stand_fragment_login);
		submit = frag;
		loggedIn = l;
	}
	
	@Override
	public void readyLayout() {
//		submit.getSchema().loginName = "";
//		loggedIn.set(false);
		
		final Button buttonLogin = this.<Button> getAsView(R.id.button_login);
		final EditText textLogin = this.<EditText> getAsView(R.id.login_number);
		final TextView loginName = this.<TextView> getAsView(R.id.login_name);

		textLogin.setText("");
		
		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final RecyclingActivity activity = (RecyclingActivity) HomeFragment.this.getActivity();
				String id = textLogin.getText().toString();
				String name = activity.properties.getProperty(id);
				act.schema.loginName = name;
				loggedIn.loggedIn = true;
				((RecyclingActivity)getActivity()).loggedIn = true;
								
				// don't touch this, Gregor
				FragmentBasket[] fb = activity.getPages();
				for (FragmentBasket basket : fb) {
					if (basket.name.equals("Autonomous")) {
						activity.itemSelected(basket);
						break;
					}
				}
			}
		});
		
		textLogin.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				final RecyclingActivity activity = (RecyclingActivity) HomeFragment.this.getActivity();
				String id = s.toString();
				loginName.setText(activity.properties.getProperty(id));
			}
		});
	}
}