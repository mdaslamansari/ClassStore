package com.aslsoft.pocapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class is to find if the phone is online or off-line.
 * @author Mohd Aslam
 *
 */
public class InternetConnectionDetector {

    private Context _context;
    
    public InternetConnectionDetector(Context context){
        this._context = context;
    }
 
    /**
     * This function is for checking all possible Internet providers connectivity:
     * [NetworkInfo: type: mobile[UMTS], WIFI[], mobile_mms[UMTS], mobile_supl[UMTS], mobile_hipri[UMTS]]. It will return 
     * true if mobile is on-line or else it will return false for off-line.
     * @return true or false 
     * @author Mohd Aslam
     * **/
    public boolean isConnectedToInternet(){    	
    	
    	ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
              { 
            	  for (int i = 0; i < info.length; i++)
            	  {
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
                  }
              } 
          }
          return false;
    }
}
