package edu.team1540.recycle.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.team1540.common.core.schema.Schema;
import org.team1540.common.core.schema.impl.StandSchema;

import android.os.Environment;

public class DieggnosticsIO {
	public static void export(StandSchema schema, String filename, String notesFilename) {
		File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS), filename);
		File fileNotes = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS), notesFilename);
		
		FileOutputStream outputNotes = null;
		FileOutputStream output = null;
		
		try {
			file.createNewFile();
			fileNotes.createNewFile();
			
			output = new FileOutputStream(file.getAbsolutePath());
			output.write(schema.export().getBytes());
			
			outputNotes = new FileOutputStream(fileNotes.getAbsolutePath());
			if (schema.generalNotes != null) outputNotes.write(schema.generalNotes.getBytes());
			
			System.out.println(schema.export());
			System.out.println(schema.generalNotes);
			
			System.out.println(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exportReadable(StandSchema schema, String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(schema.endedInAuto			+ " schema.endedInAuto\n");
		sb.append(schema.errorsAlpha			+ " schema.errorsAlpha\n");
		sb.append(schema.errorsDelta			+ " schema.errorsDelta\n");
		sb.append(schema.leftContainerAuto		+ " schema.leftContainerAuto\n");
		sb.append(schema.leftToteAuto			+ " schema.leftToteAuto\n");
		sb.append(schema.litterContainer		+ " schema.litterContainer\n");
		sb.append(schema.litterLandfill			+ " schema.litterLandfill\n");
		sb.append(schema.loginName				+ " schema.loginName\n");
		sb.append(schema.matchNumber			+ " schema.matchNumber\n");
		sb.append(schema.middleContainerAuto	+ " schema.middleContainerAuto\n");
		sb.append(schema.middleToteAuto			+ " schema.middleToteAuto\n");
		sb.append(schema.autoNotes				+ " schema.notes\n");
		sb.append(schema.question1				+ " schema.question1\n");
		sb.append(schema.question2				+ " schema.question2\n");
		sb.append(schema.question3				+ " schema.question3\n");
		sb.append(schema.question4				+ " schema.question4\n");
		sb.append(schema.question5				+ " schema.question5\n");
		sb.append(schema.rightContainerAuto		+ " schema.rightContainerAuto\n");
		sb.append(schema.rightToteAuto			+ " schema.rightToteAuto\n");
		sb.append(schema.stackedTotes			+ " schema.stackedTotes\n");
		sb.append(schema.stacks.size() 			+ " schema.stacks.size()\n");
		sb.append(schema.containerStates[0] 	+ " schema.containerStates[0]\n");
		sb.append(schema.containerStates[1] 	+ " schema.containerStates[1]\n"); 
		sb.append(schema.containerStates[2] 	+ " schema.containerStates[2]\n"); 
		sb.append(schema.containerStates[3] 	+ " schema.containerStates[3]\n"); 
		
		File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS), filename);
		try {
			file.createNewFile();
			FileOutputStream output = new FileOutputStream(file.getAbsolutePath());
			output.write(sb.toString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getFileContents(String filename) {
		StringBuilder ret = new StringBuilder();
		
		File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS), filename);
		FileInputStream input = null;
		
		try {
			input = new FileInputStream(file.getAbsolutePath());
			int in, l = 0;
			byte[] character = new byte[2];
			while ((in = input.read()) != -1) {
				character[l] = (byte) in;
				if (l == 1) ret.append(new String(character));
				
				if (l == 0) l = 1;
				else l = 0;
			}
		} catch (IOException e) {
			
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) { }
		}
		
		return ret.toString();
	}
	
	public static void createFileListing(File directory) throws IOException {
		String[] files = directory.list();
		File filestxt = new File(directory.getAbsolutePath() + "/files.txt");
		filestxt.createNewFile();
		FileWriter fos = new FileWriter(filestxt.getAbsolutePath());
		for (String file : files) {
			fos.write(file + "\n");
			System.out.println("Exporting " + file);
		}
		fos.close();
	}
}
