package com.iteye.weimingtom.reversiwins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class BugReport implements UncaughtExceptionHandler {
	private static final String BUG_FILE = "BugReport.txt";
	
	private Context context;
	private UncaughtExceptionHandler defaultHandler;
	
	public BugReport(Context context) {
		this.context = context;
		this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
        PrintWriter printwriter1 = null;
        FileOutputStream fileoutputstream = null;
		try {
			fileoutputstream = context.openFileOutput(BUG_FILE, Context.MODE_WORLD_READABLE);
	        printwriter1 = new PrintWriter(fileoutputstream);
	        throwable.printStackTrace(printwriter1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
	        if (printwriter1 != null) {
	            printwriter1.close();
	        }
	        if (fileoutputstream != null) {
	        	try {
					fileoutputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		if (defaultHandler != null) {
			defaultHandler.uncaughtException(thread, throwable);
		}
	}

	public static void SendBugReport(final Activity activity) {
	    final File bugfile = activity.getFileStreamPath(BUG_FILE);
	    if (bugfile.exists()) {
	    	AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(activity);
	    	localBuilder1
	    		.setTitle(R.string.bug_report_title)
	    		.setMessage(R.string.bug_report_message)
	    		.setPositiveButton(R.string.bug_report_send, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						BugReport.SendMail(activity, bugfile);
					}
		    	})
		    	.setNegativeButton(R.string.bug_report_not_send, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
			            if (bugfile.exists()) {
			                bugfile.delete();
			            }
			            System.exit(-1);
					}
		    	})
		    	.show();
    	}
    }
	
	private static void SendMail(Activity activity, File bugFile) {
        if (bugFile.exists()) {
			String s;
			StringBuilder stringbuilder = new StringBuilder();
	        FileReader filereader = null;
	        BufferedReader bufferedreader = null;
	        try {
	        	filereader = new FileReader(bugFile);
	        	bufferedreader = new BufferedReader(filereader);
		        while (null != (s = bufferedreader.readLine())) {
		        	stringbuilder.append(s);
		        	stringbuilder.append("\n");
		        }
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bufferedreader != null) {
					try {
						bufferedreader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (filereader != null) {
					try {
						filereader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SENDTO);
				intent.setData(Uri.parse(activity.getString(R.string.mail_address)));
				intent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.subject_title));
				intent.putExtra(Intent.EXTRA_TEXT, stringbuilder.toString());
				try {
					activity.startActivity(intent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(activity, activity.getString(R.string.activity_not_found), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				bugFile.delete();
			}
        }
	}
}
