package edu.team1540.recycle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.UUID;

import org.team1540.common.core.bluetooth.Address;
import org.team1540.common.core.schema.impl.StandSchema;

import android.os.Bundle;
import android.os.Environment;
import edu.team1540.egg.core.FragmentBasket;
import edu.team1540.egg.core.ScoutingActivity;
import edu.team1540.egg.impl.dispatching.DispatchingBasket;
import edu.team1540.egg.system.SystemConnectionHandler;
import edu.team1540.recycle.basket.home.HomeFragment;
import edu.team1540.recycle.basket.stand.AutonomousFragment;
import edu.team1540.recycle.basket.stand.FinalFragment;
import edu.team1540.recycle.basket.stand.NotesFragment;
import edu.team1540.recycle.basket.stand.SubmitFragment;
import edu.team1540.recycle.basket.stand.TeleOpFragment;
import edu.team1540.recycle.compititon.Schedule;
import edu.team1540.recycle.draw.SubmitDrawer;
import edu.team1540.recycle.file.DieggnosticsIO;
import edu.team1540.recycle.user.GlobalData;
import edu.team1540.recycle.user.Properties;

public final class RecyclingActivity extends ScoutingActivity {
	
	public Properties properties;
	public boolean loggedIn;
	public static StandSchema schema = new StandSchema();
	public static String robot = "Error";
	
	public static Stack<SubmitDrawer> oldSubmitDrawerStack;
	public static SubmitDrawer oldSubmitDrawer;
	public static int robotIndex;
	public static int matchIndex;
	
	public static Thread saveThread;
	
	@Override
	public FragmentBasket[] getPages() {
		final SubmitFragment sf = new SubmitFragment();
		final GlobalData b = new GlobalData();
		return new FragmentBasket[] {
				new FragmentBasket(this, "Home", new HomeFragment(sf, b)),
				new FragmentBasket(this, "Autonomous", new AutonomousFragment(sf, b)),
				new FragmentBasket(this, "TeleOp", new TeleOpFragment(sf)),
				new FragmentBasket(this, "Final", new FinalFragment(sf)),
				new FragmentBasket(this, "Notes", new NotesFragment(), sf) };
	}

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		properties = new Properties();
		try {
			new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "settings.txt").createNewFile();
			properties.load("userid.txt", this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		saveThread = new Thread() {
//			@Override
//			public void run() {
//				for (;;) {
//				System.out.println("Stack total: " + schema.stacks.size());
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}}
//			}
//		};
//		
//		saveThread.start();
		
		System.out.println("Started");
	}
	
	public final static File statetmp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "state.tmp");

	public static void saveState() throws IOException {
		statetmp.createNewFile();
		final String out = schema.export();
		FileWriter fw = new FileWriter(statetmp);
		fw.write(out);
		fw.close();
	}
	
	public static void loadState() throws IOException {
		if (statetmp.exists()) schema = StandSchema.create(readFile(statetmp.getAbsolutePath()));
	}
	
	private static String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}

		reader.close();

		return stringBuilder.toString();
	}
}

