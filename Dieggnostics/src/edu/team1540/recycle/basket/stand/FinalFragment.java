package edu.team1540.recycle.basket.stand;

import org.team1540.common.core.schema.impl.StandSchema;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;

public class FinalFragment extends ScoutingFragment {

	final SubmitFragment submit;
	final RecyclingActivity act = (RecyclingActivity) this.getActivity();
	
	public FinalFragment(SubmitFragment frag) {
		super(R.layout.stand_fragment_final);
		submit = frag;
	}
	
	@Override
	public void readyLayout() {
		
		if (!((RecyclingActivity) getActivity()).loggedIn) {
			// don't touch this, Gregor
			FragmentBasket[] fb = ((RecyclingActivity)FinalFragment.this.getActivity()).getPages();
			for (FragmentBasket basket : fb) {
				if (basket.name.equals("Home")) {
					((RecyclingActivity) FinalFragment.this.getActivity()).itemSelected(basket);
					break;
				}
			}
		}
		
		this.<TextView> getAsView(R.id.robot_number_final).setText(RecyclingActivity.robot);
		
		View recycling = this.getActivity().findViewById(android.R.id.content).getRootView();
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recycling.getWindowToken(), 
                                  InputMethodManager.RESULT_UNCHANGED_SHOWN);
    		
        switch (RecyclingActivity.schema.question1) {
        case 0:
        	this.<RadioGroup> getAsView(R.id.radio_q1).check(R.id.radio_well);
        	break;
        case 1:
        	this.<RadioGroup> getAsView(R.id.radio_q1).check(R.id.radio_poorly);
        	break;
        case 2:
        	this.<RadioGroup> getAsView(R.id.radio_q1).check(R.id.radio_did_not_attempt);
        	break;
        case 3:
        	this.<RadioGroup> getAsView(R.id.radio_q1).check(R.id.radio_tried_and_failed);
        	break;
        case 4:
        	this.<RadioGroup> getAsView(R.id.radio_q1).check(R.id.radio_unsure);
        	break;
        }

        switch (RecyclingActivity.schema.question2) {
        case 0:
        	this.<RadioGroup> getAsView(R.id.radio_q2).check(R.id.radio_well2);
        	break;
        case 1:
        	this.<RadioGroup> getAsView(R.id.radio_q2).check(R.id.radio_poorly2);
        	break;
        case 2:
        	this.<RadioGroup> getAsView(R.id.radio_q2).check(R.id.radio_did_not_attempt2);
        	break;
        case 3:
        	this.<RadioGroup> getAsView(R.id.radio_q2).check(R.id.radio_tried_and_failed2);
        	break;
        case 4:
        	this.<RadioGroup> getAsView(R.id.radio_q2).check(R.id.radio_unsure2);
        	break;
        }
        
        switch (RecyclingActivity.schema.question3) {
        case 0:
        	this.<RadioGroup> getAsView(R.id.radio_q3).check(R.id.radio_well3);
        	break;
        case 1:
        	this.<RadioGroup> getAsView(R.id.radio_q3).check(R.id.radio_poorly3);
        	break;
        case 2:
        	this.<RadioGroup> getAsView(R.id.radio_q3).check(R.id.radio_did_not_attempt3);
        	break;
        case 3:
        	this.<RadioGroup> getAsView(R.id.radio_q3).check(R.id.radio_tried_and_failed3);
        	break;
        case 4:
        	this.<RadioGroup> getAsView(R.id.radio_q3).check(R.id.radio_unsure3);
        	break;
        }
        
        switch (RecyclingActivity.schema.question4) {
        case 0:
        	this.<RadioGroup> getAsView(R.id.radio_q4).check(R.id.radio_none);
        	break;
        case 1:
        	this.<RadioGroup> getAsView(R.id.radio_q4).check(R.id.radio_one);
        	break;
        case 2:
        	this.<RadioGroup> getAsView(R.id.radio_q4).check(R.id.radio_multiple);
        	break;
        case 3:
        	this.<RadioGroup> getAsView(R.id.radio_q4).check(R.id.radio_unsure4);
        	break;
        }
        
        switch (RecyclingActivity.schema.question5) {
        case 0:
        	this.<RadioGroup> getAsView(R.id.radio_q5).check(R.id.radio_no);
        	break;
        case 1:
        	this.<RadioGroup> getAsView(R.id.radio_q5).check(R.id.radio_no_show);
        	break;
        case 2:
        	this.<RadioGroup> getAsView(R.id.radio_q5).check(R.id.radio_dead);
        	break;
        case 3:
        	this.<RadioGroup> getAsView(R.id.radio_q5).check(R.id.radio_broke_down);
        	break;
        case 4:
        	this.<RadioGroup> getAsView(R.id.radio_q5).check(R.id.radio_hindered);
        	break;
        }
        
		this.<Button> getAsView(R.id.button_final_submit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// don't touch this, Gregor
				FragmentBasket[] fb = ((RecyclingActivity)FinalFragment.this.getActivity()).getPages();
				for (FragmentBasket basket : fb) {
					if (basket.name.equals("Notes")) {
						((RecyclingActivity) FinalFragment.this.getActivity()).itemSelected(basket);
						break;
					}
				}
			}
		});
		
		this.<RadioGroup> getAsView(R.id.radio_q1).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				handleCheckboxes(checkedId);
			}
		});
		
		this.<RadioGroup> getAsView(R.id.radio_q2).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				handleCheckboxes(checkedId);
			}
		});
		
		this.<RadioGroup> getAsView(R.id.radio_q3).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				handleCheckboxes(checkedId);
			}
		});
		
		this.<RadioGroup> getAsView(R.id.radio_q4).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				handleCheckboxes(checkedId);
			}
		});
		
		this.<RadioGroup> getAsView(R.id.radio_q5).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				handleCheckboxes(checkedId);
			}
		});
	}
	
	public void handleCheckboxes(int id) {
		final StandSchema scheme = RecyclingActivity.schema;
		
		switch (id) {
		case R.id.radio_well: {
			scheme.question1 = 0;
			break;
		}
		case R.id.radio_poorly: {
			scheme.question1 = 1;
			break;
		}
		case R.id.radio_did_not_attempt: {
			scheme.question1 = 2;
			break;
		}
		case R.id.radio_tried_and_failed: {
			scheme.question1 = 3;
			break;
		}
		case R.id.radio_unsure: {
			scheme.question1 = 4;
			break;
		}
		
		case R.id.radio_well2: {
			scheme.question2 = 0;
			break;
		}
		case R.id.radio_poorly2: {
			scheme.question2 = 1;
			break;
		}
		case R.id.radio_did_not_attempt2: {
			scheme.question2 = 2;
			break;
		}
		case R.id.radio_tried_and_failed2: {
			scheme.question2 = 3;
			break;
		}
		case R.id.radio_unsure2: {
			scheme.question2 = 4;
			break;
		}
		
		case R.id.radio_well3: {
			scheme.question3 = 0;
			break;
		}
		case R.id.radio_poorly3: {
			scheme.question3 = 1;
			break;
		}
		case R.id.radio_did_not_attempt3: {
			scheme.question3 = 2;
			break;
		}
		case R.id.radio_tried_and_failed3: {
			scheme.question3 = 3;
			break;
		}
		case R.id.radio_unsure3: {
			scheme.question3 = 4;
			break;
		}
		
		case R.id.radio_none: {
			scheme.question4 = 0;
			break;
		}
		case R.id.radio_one: {
			scheme.question4 = 1;
			break;
		}
		case R.id.radio_multiple: {
			scheme.question4 = 2;
			break;
		}
		case R.id.radio_unsure4: {
			scheme.question4 = 3;
			break;
		}
		
		case R.id.radio_no: {
			scheme.question5 = 0;
			break;
		}
		case R.id.radio_no_show: {
			scheme.question5 = 1;
			break;
		}
		case R.id.radio_dead: {
			scheme.question5 = 2;
			break;
		}
		case R.id.radio_broke_down: {
			scheme.question5 = 3;
			break;
		}
		case R.id.radio_hindered: {
			scheme.question5 = 4;
			break;
		}
		default:
			break;
		}
	}
}
