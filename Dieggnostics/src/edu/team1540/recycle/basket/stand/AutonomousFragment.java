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
		
		this.<ToggleButton> getAsView(R.id.button_containerr1).setPressed(RecyclingActivity.schema.leftContainerAuto);
		this.<ToggleButton> getAsView(R.id.button_container2).setPressed(RecyclingActivity.schema.middleContainerAuto);
		this.<ToggleButton> getAsView(R.id.button_container3).setPressed(RecyclingActivity.schema.rightContainerAuto);
		this.<ToggleButton> getAsView(R.id.button_tote1).setPressed(RecyclingActivity.schema.leftToteAuto);
		this.<ToggleButton> getAsView(R.id.button_tote2).setPressed(RecyclingActivity.schema.middleToteAuto);
		this.<ToggleButton> getAsView(R.id.button_tote3).setPressed(RecyclingActivity.schema.rightToteAuto);
		
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
		switch (id) {
		case R.id.button_containerr1: {
			RecyclingActivity.schema.leftContainerAuto = !RecyclingActivity.schema.leftContainerAuto;
			break;
		}
		case R.id.button_container2: {
			RecyclingActivity.schema.middleContainerAuto = !RecyclingActivity.schema.middleContainerAuto;
			break;
		}
		case R.id.button_container3: {
			RecyclingActivity.schema.rightContainerAuto = !RecyclingActivity.schema.rightContainerAuto;
			break;
		}
		case R.id.button_tote1: {
			RecyclingActivity.schema.leftToteAuto = !RecyclingActivity.schema.leftToteAuto;
			break;
		}
		case R.id.button_tote2: {
			RecyclingActivity.schema.middleToteAuto = !RecyclingActivity.schema.middleToteAuto;
			break;
		}
		case R.id.button_tote3: {
			RecyclingActivity.schema.rightToteAuto = !RecyclingActivity.schema.rightToteAuto;
			break;
		}
		default: {
			break;
		}
		}
	}
}
