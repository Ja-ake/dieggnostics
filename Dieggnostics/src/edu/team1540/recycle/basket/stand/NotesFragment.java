package edu.team1540.recycle.basket.stand;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.file.DieggnosticsIO;

public class NotesFragment extends ScoutingFragment {

	public NotesFragment() {
		super(R.layout.stand_fragment_notes);
	}
	
	@Override
	public void readyLayout() {
		final Button submit = this.<Button> getAsView(R.id.button_submit_finally);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (submit.getText().toString().equals("Submit")) {
					submit.setText("Are you sure?");
					return;
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
				
				try {
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), ""));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/unmodnotes"));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/modnotes"));
					DieggnosticsIO.createFileListing(new File(Environment.getExternalStoragePublicDirectory(
					        Environment.DIRECTORY_DOWNLOADS), "/schemas"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				NotesFragment.this.attemptIncrementCurrentBasket();
			}
		});
		
		String contents = DieggnosticsIO.getFileContents("unmodnotes/"+RecyclingActivity.schema.teamNumber);
		
		final EditText notes = this.<EditText> getAsView(R.id.edit_final_notes);
		notes.setText(contents);
		RecyclingActivity.schema.generalNotes = contents;
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
