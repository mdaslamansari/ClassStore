//add below code to the file where you want to call the class.

InternetConnectionDetector icd = new InternetConnectionDetector(this);
		
boolean IsConnected=icd.isConnectedToInternet();
if(IsConnected)
{
	Toast.makeText(this, "Internet connection available", Toast.LENGTH_LONG).show();
} else
{
	Toast.makeText(this, "Internet connection not available", Toast.LENGTH_LONG).show();
}

//add below permission to the Manifest file

 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>