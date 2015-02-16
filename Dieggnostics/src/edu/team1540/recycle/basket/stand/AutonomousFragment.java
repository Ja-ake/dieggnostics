package edu.team1540.recycle.basket.stand;

import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
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
				act.schema.stackedTotes = isChecked;
			}
		});
		
		this.<CheckBox> getAsView(R.id.check_ended_in_auto).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				act.schema.endedInAuto = isChecked;
			}
		});
		
		this.<EditText> getAsView(R.id.edit_auto_notes).addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				act.schema.autoNotes = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
			
		});
		
		registerButton(R.id.button_container1);
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
		case R.id.button_container1: {
			act.schema.leftContainerAuto = !act.schema.leftContainerAuto;
			break;
		}
		case R.id.button_container2: {
			act.schema.middleContainerAuto = !act.schema.middleContainerAuto;
			break;
		}
		case R.id.button_container3: {
			act.schema.rightContainerAuto = !act.schema.rightContainerAuto;
			break;
		}
		case R.id.button_tote1: {
			act.schema.leftToteAuto = !act.schema.leftToteAuto;
			break;
		}
		case R.id.button_tote2: {
			act.schema.middleToteAuto = !act.schema.middleToteAuto;
			break;
		}
		case R.id.button_tote3: {
			act.schema.rightToteAuto = !act.schema.rightToteAuto;
			break;
		}
		default: {
			break;
		}
		}
	}
}
