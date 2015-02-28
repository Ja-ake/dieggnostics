package edu.team1540.recycle.basket.stand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import android.content.Context;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.draw.SubmitDrawer;
import edu.team1540.recycle.file.DieggnosticsIO;

public class NotesFragment extends ScoutingFragment {

	public NotesFragment() {
		super(R.layout.stand_fragment_notes);
	}
	
	@Override
	public void readyLayout() {
		
		if (!((RecyclingActivity) getActivity()).loggedIn) {
			// don't touch this, Gregor
			FragmentBasket[] fb = ((RecyclingActivity) NotesFragment.this.getActivity()).getPages();
			for (FragmentBasket basket : fb) {
				if (basket.name.equals("Home")) {
					((RecyclingActivity) NotesFragment.this.getActivity()).itemSelected(basket);
					break;
				}
			}
		}
		
		View recycling = this.getActivity().findViewById(android.R.id.content).getRootView();
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recycling.getWindowToken(), 
                                  InputMethodManager.RESULT_UNCHANGED_SHOWN);
		
		this.<TextView> getAsView(R.id.robot_number_notes).setText(RecyclingActivity.robot);
		
		final Button submit = this.<Button> getAsView(R.id.button_submit_finally);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (submit.getText().toString().equals("Submit")) {
					submit.setText("Are you sure?");
					return;
				}
				
				if (RecyclingActivity.statetmp.exists()) RecyclingActivity.statetmp.delete();
				
				try {
					File file = new File(
							Environment
									.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
							"stacksLength.txt");
					StringBuilder builder = new StringBuilder();
					if (file.exists()) {
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						String s;
						while ((s = br.readLine()) != null) {
							builder.append(s+"\n");
						}
						br.close();
					}
					
					if (file.exists()) file.createNewFile();
					
					FileWriter writer = new FileWriter(file);
					writer.write(builder.toString());
					
					writer.append("t"+RecyclingActivity.schema.teamNumber + " m" + RecyclingActivity.schema.matchNumber + " s" + RecyclingActivity.schema.stacks.size());
					
					writer.close();
				} catch (IOException e) {

				}
				
				try {
					new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "schedule.txt").createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				new File(Environment.getExternalStoragePublicDirectory(
			            Environment.DIRECTORY_DOWNLOADS), "schemas").mkdirs();
				new File(Environment.getExternalStoragePublicDirectory(
			            Environment.DIRECTORY_DOWNLOADS), "modnotes").mkdirs();
				new File(Environment.getExternalStoragePublicDirectory(
			            Environment.DIRECTORY_DOWNLOADS), "unmodnotes").mkdirs();
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss");
				String formattedDate = sdf.format(date);
				
				String schemaName = "schemas/" + RecyclingActivity.schema.teamNumber + "-" + formattedDate + ".txt";
				String notesName = "modnotes/" + RecyclingActivity.schema.teamNumber + ".txt";
				
				DieggnosticsIO.export(RecyclingActivity.schema, schemaName, notesName);
				File version = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "version.txt");
				
				try {
					version.createNewFile();
					FileWriter f = new FileWriter(version.getAbsolutePath());
					f.write("" + RecyclingActivity.schema.matchNumber);
					
					FileWriter fprime = new FileWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "settings.txt"));
					fprime.write(RecyclingActivity.robotIndex + "," + (RecyclingActivity.matchIndex+1) + "," + RecyclingActivity.schema.competition + "\n");
					fprime.close();
					
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), ""));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/unmodnotes"));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/modnotes"));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/schemas"));
					
					f.close();
				} catch (IOException e) {
					RecyclingActivity.oldSubmitDrawer = null;
					RecyclingActivity.oldSubmitDrawerStack = new Stack<SubmitDrawer>();
					e.printStackTrace();
				}
				
				RecyclingActivity.oldSubmitDrawer = null;
				RecyclingActivity.oldSubmitDrawerStack = new Stack<SubmitDrawer>();
				
				NotesFragment.this.attemptIncrementCurrentBasket();
			}
		});
		
		if (RecyclingActivity.schema.generalNotes == null || RecyclingActivity.schema.generalNotes.equals("")) 
			RecyclingActivity.schema.generalNotes = DieggnosticsIO.getFileContents("unmodnotes/"+RecyclingActivity.schema.teamNumber+".txt");
		
		final EditText notes = this.<EditText> getAsView(R.id.edit_final_notes);
		notes.setText(RecyclingActivity.schema.generalNotes);
		notes.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				RecyclingActivity.schema.generalNotes = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
	}
}