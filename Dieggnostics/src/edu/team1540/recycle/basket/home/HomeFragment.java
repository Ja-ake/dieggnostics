package edu.team1540.recycle.basket.home;

import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
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
import edu.team1540.recycle.compititon.Schedule;
import edu.team1540.recycle.file.DieggnosticsIO;
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
		final TextView errorMessage = this.<TextView> getAsView(R.id.text_error_message);
		final Schedule schedule = Schedule.load("schedule.txt");
		String contents = DieggnosticsIO.getFileContents("settings.txt");
		String[] settings = contents.split(",");
		int robotIndex = Integer.parseInt(settings[0].replaceAll("\\s",""));
		int matchIndex = Integer.parseInt(settings[1].replaceAll("\\s",""));
		if (settings.length > 2) RecyclingActivity.schema.competition = settings[2];
		
		this.<TextView> getAsView(R.id.competition_name).setText(" "+RecyclingActivity.schema.competition);
		
		if (matchIndex < 1 || matchIndex > schedule.schedule.size()) {
			errorMessage.setText("There was an error in the settings file, resetting. ");
			matchIndex = 1;
			try {
				FileWriter fw = new FileWriter(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/settings.txt");
				fw.write(robotIndex + "," + matchIndex + "," + RecyclingActivity.schema.competition + "\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (robotIndex < 0 || robotIndex > 5) {
			errorMessage.setText("There was an error in the settings file, resetting. ");
			robotIndex = 0;
			try {
				FileWriter fw = new FileWriter(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/settings.txt");
				fw.write(robotIndex + "," + matchIndex + "," + RecyclingActivity.schema.competition + "\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		RecyclingActivity.robot = " Robot " + schedule.schedule.get(matchIndex-1)[robotIndex] + " : Match " + (matchIndex) + " : " + (robotIndex < 3 ? "RED " + (robotIndex+1) : "BLUE" + (robotIndex-2));
		RecyclingActivity.schema.teamNumber = schedule.schedule.get(matchIndex-1)[robotIndex];
		RecyclingActivity.schema.matchNumber = matchIndex;
		
		RecyclingActivity.matchIndex = RecyclingActivity.schema.matchNumber;
		RecyclingActivity.robotIndex = robotIndex;
		
		TextView tabletID = this.<TextView> getAsView(R.id.tablet_id);
		switch (robotIndex) {
		case 0:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Rachel "));
			break;
		case 1:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Monica "));
			break;
		case 2:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Phoebe "));
			break;
		case 3:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Ross "));
			break;
		case 4:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Chandler "));
			break;
		case 5:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "Joey "));
			break;
		default:
			tabletID.setText(tabletID.getText().toString().replace("<None>", "<Incorrect Setup> "));
			break;
		}

		this.<TextView> getAsView(R.id.robot_number_login).setText(RecyclingActivity.robot);
		
		final EditText setMatch = this.<EditText> getAsView(R.id.edit_match_number);
		setMatch.setText("" + (matchIndex));
		final int roboIndex = robotIndex;
		final Button setMatchButton = this.<Button> getAsView(R.id.manual_match);
		setMatchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int newMatchIndex = Integer.parseInt(setMatch.getText().toString());
				if (schedule.schedule.size() < newMatchIndex || newMatchIndex < 1) {
					errorMessage.setText("Error: Invalid match number. ");
					return;
				}
				RecyclingActivity.robot = " Robot " + schedule.schedule.get(newMatchIndex-1)[roboIndex] + " : Match " + (newMatchIndex) + " : " + (roboIndex < 3 ? "RED " + (roboIndex+1) : "BLUE" + (roboIndex-2));
				RecyclingActivity.schema.teamNumber = schedule.schedule.get(newMatchIndex-1)[roboIndex];
				RecyclingActivity.schema.matchNumber = newMatchIndex;
				RecyclingActivity.matchIndex = RecyclingActivity.schema.matchNumber;
				RecyclingActivity.robotIndex = roboIndex;
				
				HomeFragment.this.<TextView> getAsView(R.id.robot_number_login).setText(RecyclingActivity.robot);
				errorMessage.setText("Match number set successfully to " + newMatchIndex + ". ");
				try {
					FileWriter fw = new FileWriter(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/settings.txt");
					fw.write(roboIndex + "," + newMatchIndex + "\n");
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		final Button buttonLogin = this.<Button> getAsView(R.id.button_login);
		final EditText textLogin = this.<EditText> getAsView(R.id.login_number);
		final TextView loginName = this.<TextView> getAsView(R.id.login_namee);

		textLogin.setText("");
		
		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final RecyclingActivity activity = (RecyclingActivity) HomeFragment.this.getActivity();
				String id = textLogin.getText().toString();
				String name = activity.properties.getProperty(id);
				RecyclingActivity.schema.loginName = name;
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
