package edu.team1540.recycle.basket.stand;

import java.util.ArrayList;

import org.team1540.common.core.schema.impl.StandSchema;
import org.team1540.common.core.schema.impl.ToteStackSchema;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.draw.StackSurfaceView;
import edu.team1540.recycle.draw.SubmitDrawer;

public class TeleOpFragment extends ScoutingFragment {
	final SubmitFragment submit;
	final RecyclingActivity act = (RecyclingActivity) this.getActivity();

	public TeleOpFragment(SubmitFragment sub) {
		super(R.layout.stand_fragment_teleop);
		submit = sub;
	}

	@Override
	public void readyLayout() {
		if (!((RecyclingActivity)getActivity()).loggedIn) {
			// don't touch this, Gregor
			FragmentBasket[] fb = ((RecyclingActivity)TeleOpFragment.this.getActivity()).getPages();
			for (FragmentBasket basket : fb) {
				if (basket.name.equals("Home")) {
					((RecyclingActivity)TeleOpFragment.this.getActivity()).itemSelected(basket);
					break;
				}
			}
		}
		
		this.<TextView> getAsView(R.id.robot_number_tele).setText(RecyclingActivity.robot);
		
		View recycling = this.getActivity().findViewById(android.R.id.content).getRootView();
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recycling.getWindowToken(), 
                                  InputMethodManager.RESULT_UNCHANGED_SHOWN);
		
		final StackSurfaceView stackSurfaceView = new StackSurfaceView(this.getActivity());
		final SurfaceView sv = this.<SurfaceView> getAsView(R.id.totes_surface);
		sv.getHolder().addCallback(stackSurfaceView);

		final Button[][] button_container = new Button[4][3];

		final Button button_container_minus = this.<Button> getAsView(R.id.button_container_minus);
		final Button button_container_plus = this.<Button> getAsView(R.id.button_container_plus);
		final Button button_landfill_minus = this.<Button> getAsView(R.id.button_landfill_minus);
		final Button button_landfill_plus = this.<Button> getAsView(R.id.button_landfill_plus);
		final Button button_error_alpha_minus = this.<Button> getAsView(R.id.button_error_alpha_minus);
		final Button button_error_alpha_plus = this.<Button> getAsView(R.id.button_error_alpha_plus);
		final Button button_error_beta_minus = this.<Button> getAsView(R.id.button_error_beta_minus);
		final Button button_error_beta_plus = this.<Button> getAsView(R.id.button_error_beta_plus);
		final Button button_error_delta_minus = this.<Button> getAsView(R.id.button_error_delta_minus);
		final Button button_error_delta_plus = this.<Button> getAsView(R.id.button_error_delta_plus);
		button_container[0][0] = this.<Button> getAsView(R.id.container00);
		button_container[0][1] = this.<Button> getAsView(R.id.container01);
		button_container[0][2] = this.<Button> getAsView(R.id.container02);
		button_container[1][0] = this.<Button> getAsView(R.id.container10);
		button_container[1][1] = this.<Button> getAsView(R.id.container11);
		button_container[1][2] = this.<Button> getAsView(R.id.container12);
		button_container[2][0] = this.<Button> getAsView(R.id.container20);
		button_container[2][1] = this.<Button> getAsView(R.id.container21);
		button_container[2][2] = this.<Button> getAsView(R.id.container22);
		button_container[3][0] = this.<Button> getAsView(R.id.container30);
		button_container[3][1] = this.<Button> getAsView(R.id.container31);
		button_container[3][2] = this.<Button> getAsView(R.id.container32);

		final SurfaceView ssv = this.<SurfaceView> getAsView(R.id.totes_surface);

		final StandButtonHandler standButtonHandler = new StandButtonHandler((RecyclingActivity) this.getActivity(), this);
		class TeleOnClickListener implements OnClickListener {
			public int id;

			public TeleOnClickListener(final int i) {
				super();
				id = i;
			}

			@Override
			public void onClick(final View v) {
				if (!(v instanceof Button)) {
					return;
				}
				standButtonHandler.onClick(id);
			}
		}

		this.<Button> getAsView(R.id.button_container1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final RecyclingActivity activity = (RecyclingActivity) TeleOpFragment.this.getActivity();
				
				final StandSchema schema = RecyclingActivity.schema;
				
				schema.stacks.clear();
				if (stackSurfaceView.oldSubmitDrawer != null) schema.stacks.add(new ToteStackSchema(stackSurfaceView.oldSubmitDrawer.oldStack, stackSurfaceView.oldSubmitDrawer.mainStack, stackSurfaceView.oldSubmitDrawer.oContainer, stackSurfaceView.oldSubmitDrawer.mContainer, stackSurfaceView.oldSubmitDrawer.coop));
				for (SubmitDrawer sd : stackSurfaceView.oldSubmitDrawerStack) {
					schema.stacks.add(new ToteStackSchema(sd.oldStack, sd.mainStack, sd.oContainer, sd.mContainer, sd.coop)); // old, new
				}
				
				// don't touch this, Gregor
				FragmentBasket[] fb = activity.getPages();
				for (FragmentBasket basket : fb) {
					if (basket.name.equals("Final")) {
						activity.itemSelected(basket);
						break;
					}
				}
			}
		});
		
		button_container_minus.setOnClickListener(new TeleOnClickListener(R.id.button_container_minus));
		button_container_plus.setOnClickListener(new TeleOnClickListener(R.id.button_container_plus));
		button_landfill_minus.setOnClickListener(new TeleOnClickListener(R.id.button_landfill_minus));
		button_landfill_plus.setOnClickListener(new TeleOnClickListener(R.id.button_landfill_plus));
		button_container[0][0].setOnClickListener(new TeleOnClickListener(R.id.container00));
		button_container[0][1].setOnClickListener(new TeleOnClickListener(R.id.container01));
		button_container[0][2].setOnClickListener(new TeleOnClickListener(R.id.container02));
		button_container[1][0].setOnClickListener(new TeleOnClickListener(R.id.container10));
		button_container[1][1].setOnClickListener(new TeleOnClickListener(R.id.container11));
		button_container[1][2].setOnClickListener(new TeleOnClickListener(R.id.container12));
		button_container[2][0].setOnClickListener(new TeleOnClickListener(R.id.container20));
		button_container[2][1].setOnClickListener(new TeleOnClickListener(R.id.container21));
		button_container[2][2].setOnClickListener(new TeleOnClickListener(R.id.container22));
		button_container[3][0].setOnClickListener(new TeleOnClickListener(R.id.container30));
		button_container[3][1].setOnClickListener(new TeleOnClickListener(R.id.container31));
		button_container[3][2].setOnClickListener(new TeleOnClickListener(R.id.container32));
		button_error_alpha_minus.setOnClickListener(new TeleOnClickListener(R.id.button_error_alpha_minus));
		button_error_alpha_plus.setOnClickListener(new TeleOnClickListener(R.id.button_error_alpha_plus));
		button_error_beta_minus.setOnClickListener(new TeleOnClickListener(R.id.button_error_beta_minus));
		button_error_beta_plus.setOnClickListener(new TeleOnClickListener(R.id.button_error_beta_plus));
		button_error_delta_minus.setOnClickListener(new TeleOnClickListener(R.id.button_error_delta_minus));
		button_error_delta_plus.setOnClickListener(new TeleOnClickListener(R.id.button_error_delta_plus));
		ssv.setOnTouchListener(new OnTouchListener() {
			double submitX, oSubmitY;
			
			@Override
			public boolean onTouch(final View v, final MotionEvent event) {
				v.performClick();				
				if (event.getAction() != MotionEvent.ACTION_UP) {
					if (event.getY() > 230) {
						stackSurfaceView.submitDrawer.beingMoved = false;
						stackSurfaceView.submitDrawer.x = 70.f;
						if (event.getX() > 475) {
							if (event.getX() < 900) stackSurfaceView.mainStackDrawer.stackHeight = (int) (1+((1000 - event.getY()) / (700/5)));						
						} else stackSurfaceView.oldStackDrawer.stackHeight = (int) (1+((1000 - event.getY()) / (700/5)));
					} else if (event.getY() > 140) {
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							if (event.getX() > 475) {
								if (event.getX() < 900) stackSurfaceView.mainStackDrawer.container = !stackSurfaceView.mainStackDrawer.container;
							} else stackSurfaceView.oldStackDrawer.container = !stackSurfaceView.oldStackDrawer.container;
						}
					} else {
						if (event.getX() >= (stackSurfaceView.submitDrawer.x - 40) && event.getX() <= stackSurfaceView.submitDrawer.x + 150) {
							if (stackSurfaceView.submitDrawer.beingMoved) {
								stackSurfaceView.submitDrawer.x += (event.getX() - submitX);
								stackSurfaceView.refreshDrawableState();
							} else {
								stackSurfaceView.submitDrawer.beingMoved = true;
							}

							submitX = event.getX();
						}
					}
					
					
					if (stackSurfaceView.oldSubmitDrawer != null) {
						synchronized (stackSurfaceView.oldSubmitDrawer) {
							if (event.getX() >= (stackSurfaceView.oldSubmitDrawer.x - 40) && event.getX() <= stackSurfaceView.oldSubmitDrawer.x + 150) {
								if (event.getY() >= (stackSurfaceView.oldSubmitDrawer.y - 40) && event.getY() <= stackSurfaceView.oldSubmitDrawer.y + 150) {
									if (stackSurfaceView.oldSubmitDrawer.beingMoved) {
										stackSurfaceView.oldSubmitDrawer.y += (event.getY() - oSubmitY);
										stackSurfaceView.refreshDrawableState();
									} else {
										stackSurfaceView.oldSubmitDrawer.beingMoved = true;
									}

									oSubmitY = event.getY();
								}
							}
						}
					}
				} else {
					stackSurfaceView.submitDrawer.beingMoved = false;
					if (stackSurfaceView.oldSubmitDrawer != null) stackSurfaceView.oldSubmitDrawer.beingMoved = false;
					stackSurfaceView.refreshDrawableState();
				}
				
				stackSurfaceView.submitDrawer.reset();
				if (stackSurfaceView.oldSubmitDrawer != null) stackSurfaceView.oldSubmitDrawer.reset();
				
				return true;
			}
		});
		
		this.<CheckBox> getAsView(R.id.check_coopertition).setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				stackSurfaceView.submitDrawer.coop = isChecked;
				stackSurfaceView.coop = isChecked;
			}
			
		});;
	}
}
