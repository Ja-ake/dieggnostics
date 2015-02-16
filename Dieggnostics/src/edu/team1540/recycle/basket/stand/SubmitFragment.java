package edu.team1540.recycle.basket.stand;

import org.team1540.common.core.schema.impl.StandSchema;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;

public class SubmitFragment extends ScoutingFragment {

	public boolean loggedIn;
	final RecyclingActivity act = (RecyclingActivity) this.getActivity();
	
	public SubmitFragment() {
		super(R.layout.stand_fragment_submit);
	}
	
	@Override
	public void readyLayout() {
		RecyclingActivity.schema = new StandSchema();
		((RecyclingActivity)this.getActivity()).loggedIn = false;
		
		View recycling = this.getActivity().findViewById(android.R.id.content).getRootView();
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recycling.getWindowToken(), 
                                  InputMethodManager.RESULT_UNCHANGED_SHOWN);
        
		final RecyclingActivity activity = (RecyclingActivity) SubmitFragment.this.getActivity();
		this.<Button> getAsView(R.id.button_return).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SubmitFragment.this.attemptDecrementCurrentBasket();
				
				// don't touch this, Gregor
				FragmentBasket[] fb = activity.getPages();
				for (FragmentBasket basket : fb) {
					if (basket.name.equals("Home")) {
						activity.itemSelected(basket);
						break;
					}
				}
			}
		});
	}
}
