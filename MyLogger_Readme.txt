//usage

public MyLogger mylog=new MyLogger();

mylog.RecordLog("clsService_OnCreate", "In OnCreate Method - registerReceivers");

//delete log file after 10 days

mylog.DeleteLog(10);