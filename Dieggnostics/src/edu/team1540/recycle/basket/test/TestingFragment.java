package edu.team1540.recycle.basket.test;

import org.team1540.common.core.pattern.Tuple;
import org.team1540.common.core.schema.impl.BluetoothTestSchema;
import org.team1540.common.core.schema.impl.StandSchema;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import edu.team1540.egg.impl.dispatching.DispatchingFragment;
import edu.team1540.recycle.R;

public class TestingFragment extends DispatchingFragment<BluetoothTestSchema> {

	public TestingFragment() {
		super(R.layout.bluetooth_test_fragment);
	}

	@Override
	public void readyLayout() {
		this.<Button> getAsView(R.id.button_test_submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TestingFragment.this.submitSchema();
			}
		});
	}

	@Override
	public void callback(Tuple<Boolean, String> arg0) {
		
	}
}
