package edu.team1540.recycle.basket.stand;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import edu.team1540.egg.core.ScoutingFragment;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;
import edu.team1540.recycle.file.DieggnosticsExport;

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
				DieggnosticsExport.exportReadable(RecyclingActivity.schema, "schema.txt");
				NotesFragment.this.attemptIncrementCurrentBasket();
			}
		});
		
		String contents = DieggnosticsExport.getFileContents("notes.txt");
		
		final EditText notes = this.<EditText> getAsView(R.id.edit_final_notes);
		notes.setText(contents);
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
