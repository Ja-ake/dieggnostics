package edu.team1540.recycle.basket.stand;

import java.io.IOException;

import org.team1540.common.core.schema.impl.StandSchema;
import org.team1540.common.core.schema.impl.ToteStackSchema.ContainerState;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import edu.team1540.recycle.R;
import edu.team1540.recycle.RecyclingActivity;

public class StandButtonHandler {
	private final RecyclingActivity act;
	private final TeleOpFragment fragment;

	public StandButtonHandler(final RecyclingActivity sub, TeleOpFragment frag) {
		fragment = frag;
		act = sub;
	}

	public void onClick(final int id) {
		StandSchema scheme = (StandSchema) RecyclingActivity.schema;
				
		switch (id) {
		case R.id.button_container_minus: {
			scheme.litterContainer--;
			scheme.litterContainer = Math.max(scheme.litterContainer, 0);
			final TextView tvm = fragment.<TextView> getAsView(R.id.text_container_countt);
			tvm.setText(Math.max(Integer.parseInt(tvm.getText().toString()) - 1, 0) + "");
			break;
		}
		case R.id.button_container_plus: {
			scheme.litterContainer++;
			scheme.litterContainer = Math.max(scheme.litterContainer, 0);
			final TextView tvm = fragment.<TextView> getAsView(R.id.text_container_countt);
			tvm.setText(Math.max(Integer.parseInt(tvm.getText().toString()) + 1, 0) + "");
			break;
		}
		case R.id.button_landfill_minus: {
			scheme.litterLandfill--;
			scheme.litterLandfill = Math.max(scheme.litterLandfill, 0);
			final TextView tvm = fragment.<TextView> getAsView(R.id.text_landfill_count);
			tvm.setText(Math.max(Integer.parseInt(tvm.getText().toString()) - 1, 0) + "");
			break;
		}
		case R.id.button_landfill_plus: {
			scheme.litterLandfill++;
			scheme.litterLandfill = Math.max(scheme.litterLandfill, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_landfill_count);
			tvp.setText(Math.max(Integer.parseInt(tvp.getText().toString()) + 1, 0) + "");
			break;
		}
		case R.id.button_error_alpha_minus: {
			scheme.errorsAlpha--;
			scheme.errorsAlpha = Math.max(scheme.errorsAlpha, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_alpha);
			tvp.setText(scheme.errorsAlpha + "");
			break;
		}
		case R.id.button_error_alpha_plus: {
			scheme.errorsAlpha++;
			scheme.errorsAlpha = Math.max(scheme.errorsAlpha, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_alpha);
			tvp.setText(scheme.errorsAlpha + "");
			break;
		}
		case R.id.button_error_beta_minus: {
			scheme.errorsBeta--;
			scheme.errorsBeta = Math.max(scheme.errorsBeta, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_beta);
			tvp.setText(scheme.errorsBeta + "");
			break;
		}
		case R.id.button_error_beta_plus: {
			scheme.errorsBeta++;
			scheme.errorsBeta = Math.max(scheme.errorsBeta, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_beta);
			tvp.setText(scheme.errorsBeta + "");
			break;
		}
		case R.id.button_error_delta_minus: {
			scheme.errorsDelta--;
			scheme.errorsDelta = Math.max(scheme.errorsDelta, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_delta);
			tvp.setText(scheme.errorsDelta + "");
			break;
		}
		case R.id.button_error_delta_plus: {
			scheme.errorsDelta++;
			scheme.errorsDelta = Math.max(scheme.errorsDelta, 0);
			final TextView tvp = fragment.<TextView> getAsView(R.id.text_error_delta);
			tvp.setText(scheme.errorsDelta + "");
			break;
		}
		case R.id.container00: {
			final Button bt = fragment.<Button> getAsView(R.id.container00);
			if (scheme.containerStates[0] != null && scheme.containerStates[0].equals(ContainerState.OTHER_SIDE)) {
				bt.setText("");
				scheme.containerStates[0] = null;
				break;
			}
			
			scheme.containerStates[0] = ContainerState.OTHER_SIDE;
			final Button oa = fragment.<Button> getAsView(R.id.container01);
			final Button ob = fragment.<Button> getAsView(R.id.container02);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container01: {
			final Button bt = fragment.<Button> getAsView(R.id.container01);
			if (scheme.containerStates[0] != null && scheme.containerStates[0].equals(ContainerState.COLLECTED)) {
				bt.setText("");
				scheme.containerStates[0] = null;
				break;
			}
			
			scheme.containerStates[0] = ContainerState.COLLECTED;
			final Button oa = fragment.<Button> getAsView(R.id.container00);
			final Button ob = fragment.<Button> getAsView(R.id.container02);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container02: {
			final Button bt = fragment.<Button> getAsView(R.id.container02);
			if (scheme.containerStates[0] != null && scheme.containerStates[0].equals(ContainerState.TIPPED)) {
				bt.setText("");
				scheme.containerStates[0] = null;
				break;
			}
			
			scheme.containerStates[0] = ContainerState.TIPPED;
			final Button ob = fragment.<Button> getAsView(R.id.container00);
			final Button oa = fragment.<Button> getAsView(R.id.container01);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container10: {
			final Button bt = fragment.<Button> getAsView(R.id.container10);
			if (scheme.containerStates[1] != null && scheme.containerStates[1].equals(ContainerState.OTHER_SIDE)) {
				bt.setText("");
				scheme.containerStates[1] = null;
				break;
			}
			
			scheme.containerStates[1] = ContainerState.OTHER_SIDE;
			final Button oa = fragment.<Button> getAsView(R.id.container11);
			final Button ob = fragment.<Button> getAsView(R.id.container12);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container11: {
			final Button bt = fragment.<Button> getAsView(R.id.container11);
			if (scheme.containerStates[1] != null && scheme.containerStates[1].equals(ContainerState.COLLECTED)) {
				bt.setText("");
				scheme.containerStates[1] = null;
				break;
			}
			
			scheme.containerStates[1] = ContainerState.COLLECTED;
			final Button oa = fragment.<Button> getAsView(R.id.container10);
			final Button ob = fragment.<Button> getAsView(R.id.container12);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container12: {
			final Button bt = fragment.<Button> getAsView(R.id.container12);
			if (scheme.containerStates[1] != null && scheme.containerStates[1].equals(ContainerState.TIPPED)) {
				bt.setText("");
				scheme.containerStates[1] = null;
				break;
			}
			
			scheme.containerStates[1] = ContainerState.TIPPED;
			final Button ob = fragment.<Button> getAsView(R.id.container10);
			final Button oa = fragment.<Button> getAsView(R.id.container11);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container20: {
			final Button bt = fragment.<Button> getAsView(R.id.container20);
			if (scheme.containerStates[2] != null && scheme.containerStates[2].equals(ContainerState.OTHER_SIDE)) {
				bt.setText("");
				scheme.containerStates[2] = null;
				break;
			}
			
			scheme.containerStates[2] = ContainerState.OTHER_SIDE;
			final Button oa = fragment.<Button> getAsView(R.id.container21);
			final Button ob = fragment.<Button> getAsView(R.id.container22);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container21: {
			final Button bt = fragment.<Button> getAsView(R.id.container21);
			if (scheme.containerStates[2] != null && scheme.containerStates[2].equals(ContainerState.COLLECTED)) {
				bt.setText("");
				scheme.containerStates[2] = null;
				break;
			}
			
			scheme.containerStates[2] = ContainerState.COLLECTED;
			final Button oa = fragment.<Button> getAsView(R.id.container20);
			final Button ob = fragment.<Button> getAsView(R.id.container22);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container22: {
			final Button bt = fragment.<Button> getAsView(R.id.container22);
			if (scheme.containerStates[2] != null && scheme.containerStates[2].equals(ContainerState.TIPPED)) {
				bt.setText("");
				scheme.containerStates[2] = null;
				break;
			}
			
			scheme.containerStates[2] = ContainerState.TIPPED;
			final Button ob = fragment.<Button> getAsView(R.id.container20);
			final Button oa = fragment.<Button> getAsView(R.id.container21);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container30: {
			final Button bt = fragment.<Button> getAsView(R.id.container30);
			if (scheme.containerStates[3] != null && scheme.containerStates[3].equals(ContainerState.OTHER_SIDE)) {
				bt.setText("");
				scheme.containerStates[3] = null;
				break;
			}
			
			scheme.containerStates[3] = ContainerState.OTHER_SIDE;
			final Button oa = fragment.<Button> getAsView(R.id.container31);
			final Button ob = fragment.<Button> getAsView(R.id.container32);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container31: {
			final Button bt = fragment.<Button> getAsView(R.id.container31);
			if (scheme.containerStates[3] != null && scheme.containerStates[3].equals(ContainerState.COLLECTED)) {
				bt.setText("");
				scheme.containerStates[3] = null;
				break;
			}
			
			scheme.containerStates[3] = ContainerState.COLLECTED;
			final Button oa = fragment.<Button> getAsView(R.id.container30);
			final Button ob = fragment.<Button> getAsView(R.id.container32);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		case R.id.container32: {
			final Button bt = fragment.<Button> getAsView(R.id.container32);
			if (scheme.containerStates[3] != null && scheme.containerStates[3].equals(ContainerState.TIPPED)) {
				bt.setText("");
				scheme.containerStates[3] = null;
				break;
			}
			
			scheme.containerStates[3] = ContainerState.TIPPED;
			final Button ob = fragment.<Button> getAsView(R.id.container30);
			final Button oa = fragment.<Button> getAsView(R.id.container31);
			bt.setText("x");
			oa.setText("");
			ob.setText("");
			break;
		}
		default:
			Log.w("DIE", "A button was pressed that is not handled.");
			break;
		}
		
		try {
			RecyclingActivity.saveState();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
