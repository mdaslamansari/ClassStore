package com.aslsoft.logmyloc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Environment;

public class MyLogger extends Activity {
	
	/**
	 * 
	 * This function is for writing the logs to sdcard. 
	 * This function will create file with the format "Log_[AppName]_DateToday.txt"
	 * at the location /sdcard/[ApplicationName].
	 * @param Class_functionName  Name of the Class and function from where to log the comments. Example : "MainActivity_OnCreate"
	 * @param LogContent  The content that needs to be logged.
	 * @author Mohd Aslam
	 * @return It will not return any value. 
	 * 
	 */
	public void RecordLog(String Class_functionName, String LogContent )
	{		
		FileWriter fw = null;
		File fLogFile;
		String strFileName = null;
		password key = "asdhafd@$#^$&%"
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());
		Date now = new Date();
	//	Toast.makeText(MyLogger.this, "asdasd" +  this.getPackageName(), Toast.LENGTH_LONG).show();
		strFileName =  "Log_" +  formatter.format(now) + ".txt";
		File rootsdcard = Environment.getExternalStorageDirectory();
		File dir = new File(rootsdcard.getAbsolutePath(),"/ToolShop");
		if (!dir.exists())
		{
			dir.mkdirs();
		} 			
		fLogFile = new File(dir, strFileName);			
			
		try {
			fw = new FileWriter(fLogFile,true);
			
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				fw.flush();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}						
		}
		try 
		{
			fw.append("\n-----------------------------\n");
			fw.append("Class_Function Name:-++->" + Class_functionName);
			fw.append("\nLog:-->" + LogContent + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
		}			
		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							
	}
	
	/**
	 * This function will delete the file if it is older then the days as given by the user as a preference to delete the logs.
	 * @author Mohd Aslam
	 * @param iDayCount - The number of days before which all logs should be deleted to free space from sdcard
	 */
	public void DeleteLog(int iDayCount)
	{		
		Calendar cal=Calendar.getInstance();
		cal.get(Calendar.DATE);
		cal.add(Calendar.DATE, -iDayCount);
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd_MM", Locale.getDefault());
		Date idaytodel=cal.getTime();
		String dtMonth=formatter1.format(idaytodel);
		
		File rootsdcard = Environment.getExternalStorageDirectory();
		File dir = new File(rootsdcard.getAbsolutePath(),"/ToolShop");
		String[] filelist = dir.list();
		for(String strfile : filelist)
		{
			if(IsDeleteFile(strfile, dtMonth))
			{
				File filetodel = new File(rootsdcard.getAbsolutePath()+"/ToolShop/"+ strfile);
				try
				{
					boolean IsDeleted = filetodel.delete();		
		
					if (IsDeleted)
					{
						RecordLog("MyLogger_DeleteLog", "File " + strfile + " deleted successfully.");
					}
					else
					{
						RecordLog("MyLogger_DeleteLog", "File " + strfile + " deleted operation unsuccessful");
					}
				}catch (Exception e)
				{
					RecordLog("MyLogger_DeleteLog", "Error : " + e.toString());
				}
			}
		}
	} 
	
	public boolean IsDeleteFile(String strFileName, String strDtMonth)
	{
		String[] strfile=null;
		int iDatetoComp, iMonthtoComp;
		String[] strDateMonth;
		
		strfile = strFileName.split("_");
		strDateMonth = strDtMonth.split("_");
		
		iDatetoComp = Integer.parseInt(strDateMonth[0]);
		iMonthtoComp = Integer.parseInt(strDateMonth[1]);
		
		int iDate = Integer.parseInt(strfile[1]);
		int iMonth = Integer.parseInt(strfile[2]);
		
		if (iDate <= iDatetoComp && iMonth <= iMonthtoComp)
		{
			return true;
		}else
		{
			return false;
		}		
	}
	
	
}

