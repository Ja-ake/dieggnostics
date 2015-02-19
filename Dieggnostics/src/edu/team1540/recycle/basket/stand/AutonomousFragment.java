package edu.team1540.recycle.basket.stand;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.user.GlobalData;

public class AutonomousFragment extends ScoutingFragment {

	final SubmitFragment submit;
	final GlobalData loggedIn;
	final RecyclingActivity act = (RecyclingActivity) this.getActivity();
	
	public AutonomousFragment(final SubmitFragment frag, final GlobalData b) {
		super(R.layout.stand_fragment_autonomous);
		submit = frag;
		loggedIn = b;
	}
	
	@Override
	public void readyLayout() {
		if (!((RecyclingActivity)getActivity()).loggedIn) {
			// don't touch this, Gregor
			FragmentBasket[] fb = ((RecyclingActivity)AutonomousFragment.this.getActivity()).getPages();
			for (FragmentBasket basket : fb) {
				if (basket.name.equals("Home")) {
					((RecyclingActivity)AutonomousFragment.this.getActivity()).itemSelected(basket);
					break;
				}
			}
		}
		
		this.<TextView> getAsView(R.id.robot_number_auto).setText(RecyclingActivity.robot);
		
		Button bc1 = this.<Button> getAsView(R.id.button_containerr1);
		Button bc2 = this.<Button> getAsView(R.id.button_container2);
		Button bc3 = this.<Button> getAsView(R.id.button_container3);
		Button bt1 = this.<Button> getAsView(R.id.button_tote1);
		Button bt2 = this.<Button> getAsView(R.id.button_tote2);
		Button bt3 = this.<Button> getAsView(R.id.button_tote3);
		
		if (RecyclingActivity.schema.leftContainerAuto) bc1.setText("C : Suc");
		else if (RecyclingActivity.schema.leftContainerAutoTried) bc1.setText("C : Fail");
		else bc1.setText("C : Ign");
		
		if (RecyclingActivity.schema.middleContainerAuto) bc2.setText("C : Suc");
		else if (RecyclingActivity.schema.middleContainerAutoTried) bc2.setText("C : Fail");
		else bc2.setText("C : Ign");
		
		if (RecyclingActivity.schema.rightContainerAuto) bc3.setText("C : Suc");
		else if (RecyclingActivity.schema.rightContainerAutoTried) bc3.setText("C : Fail");
		else bc3.setText("C : Ign");
		
		if (RecyclingActivity.schema.leftToteAuto) bt1.setText("T : Suc");
		else if (RecyclingActivity.schema.leftToteAutoTried) bt1.setText("T : Fail");
		else bt1.setText("T : Ign");
		
		if (RecyclingActivity.schema.middleToteAuto) bt2.setText("T : Suc");
		else if (RecyclingActivity.schema.middleToteAutoTried) bt2.setText("T : Fail");
		else bt2.setText("T : Ign");
		
		if (RecyclingActivity.schema.rightToteAuto) bt3.setText("T : Suc");
		else if (RecyclingActivity.schema.rightToteAutoTried) bt3.setText("T : Fail");
		else bt3.setText("T : Ign");
		
		this.<CheckBox> getAsView(R.id.check_stacked_totes).setChecked(RecyclingActivity.schema.stackedTotes);
		this.<CheckBox> getAsView(R.id.check_ended_in_auto).setChecked(RecyclingActivity.schema.endedInAuto);
		
		View recycling = this.getActivity().findViewById(android.R.id.content).getRootView();
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recycling.getWindowToken(), 
                                  InputMethodManager.RESULT_UNCHANGED_SHOWN);
        
		this.<Button> getAsView(R.id.button_next).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final RecyclingActivity activity = (RecyclingActivity) AutonomousFragment.this.getActivity();
				
				// don't touch this, Gregor
				FragmentBasket[] fb = activity.getPages();
				for (FragmentBasket basket : fb) {
					if (basket.name.equals("TeleOp")) {
						activity.itemSelected(basket);
						break;
					}
				}
			}
		});
		
		this.<CheckBox> getAsView(R.id.check_stacked_totes).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				RecyclingActivity.schema.stackedTotes = isChecked;
			}
		});
		
		this.<CheckBox> getAsView(R.id.check_ended_in_auto).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				RecyclingActivity.schema.endedInAuto = isChecked;
			}
		});
		
		registerButton(R.id.button_containerr1);
		registerButton(R.id.button_container2);
		registerButton(R.id.button_container3);
		registerButton(R.id.button_tote1);
		registerButton(R.id.button_tote2);
		registerButton(R.id.button_tote3);
	}
	
	public void registerButton(final int id) {
		final Button b = this.<Button> getAsView(id);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleButtons(id, b);
			}
		});
	}
	
	public void handleButtons(int id, Button b) {
		if (b.getText().toString().contains("Ign")) b.setText(b.getText().toString().replace("Ign", "Suc"));
		else if (b.getText().toString().contains("Suc")) b.setText(b.getText().toString().replace("Suc", "Fail"));
		else b.setText(b.getText().toString().replace("Fail", "Ign"));
		switch (id) {
		case R.id.button_containerr1: {
			if (b.getText().toString().contains("No")) {
				RecyclingActivity.schema.leftContainerAuto = false;
				RecyclingActivity.schema.leftContainerAutoTried = false;
			} else if (b.getText().toString().contains("Yes")) {
				RecyclingActivity.schema.leftContainerAuto = true;
				RecyclingActivity.schema.leftContainerAutoTried = true;
			} else {
				RecyclingActivity.schema.leftContainerAuto = false;
				RecyclingActivity.schema.leftContainerAutoTried = true;
			}
			break;
		}
		case R.id.button_container2: {
			if (b.getText().toString().contains("Ign")) {
				RecyclingActivity.schema.middleContainerAuto = false;
				RecyclingActivity.schema.middleContainerAutoTried = false;
			} else if (b.getText().toString().contains("Suc")) {
				RecyclingActivity.schema.middleContainerAuto = true;
				RecyclingActivity.schema.middleContainerAutoTried = true;
			} else {
				RecyclingActivity.schema.middleContainerAuto = false;
				RecyclingActivity.schema.middleContainerAutoTried = true;
			}
			break;
		}
		case R.id.button_container3: {
			if (b.getText().toString().contains("Ign")) {
				RecyclingActivity.schema.rightContainerAuto = false;
				RecyclingActivity.schema.rightContainerAutoTried = false;
			} else if (b.getText().toString().contains("Suc")) {
				RecyclingActivity.schema.rightContainerAuto = true;
				RecyclingActivity.schema.rightContainerAutoTried = true;
			} else {
				RecyclingActivity.schema.rightContainerAuto = false;
				RecyclingActivity.schema.rightContainerAutoTried = true;
			}
			break;
		}
		case R.id.button_tote1: {
			if (b.getText().toString().contains("Ign")) {
				RecyclingActivity.schema.leftToteAuto = false;
				RecyclingActivity.schema.leftToteAutoTried = false;
			} else if (b.getText().toString().contains("Suc")) {
				RecyclingActivity.schema.leftToteAuto = true;
				RecyclingActivity.schema.leftToteAutoTried = true;
			} else {
				RecyclingActivity.schema.leftToteAuto = false;
				RecyclingActivity.schema.leftToteAutoTried = true;
			}
			break;
		}
		case R.id.button_tote2: {
			if (b.getText().toString().contains("Ign")) {
				RecyclingActivity.schema.middleToteAuto = false;
				RecyclingActivity.schema.middleToteAutoTried = false;
			} else if (b.getText().toString().contains("Suc")) {
				RecyclingActivity.schema.middleToteAuto = true;
				RecyclingActivity.schema.middleToteAutoTried = true;
			} else {
				RecyclingActivity.schema.middleToteAuto = false;
				RecyclingActivity.schema.middleToteAutoTried = true;
			}
			break;
		}
		case R.id.button_tote3: {
			if (b.getText().toString().contains("Ign")) {
				RecyclingActivity.schema.rightToteAuto = false;
				RecyclingActivity.schema.rightToteAutoTried = false;
			} else if (b.getText().toString().contains("Suc")) {
				RecyclingActivity.schema.rightToteAuto = true;
				RecyclingActivity.schema.rightToteAutoTried = true;
			} else {
				RecyclingActivity.schema.rightToteAuto = false;
				RecyclingActivity.schema.rightToteAutoTried = true;
			}
			break;
		}
		default: {
			break;
		}
		}
	}
}
