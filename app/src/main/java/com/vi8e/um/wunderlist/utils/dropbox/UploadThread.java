package com.vi8e.um.wunderlist.utils.dropbox;
/**
 * Created by um.anusorn on 10/10/2015.
 */
public class UploadThread implements Runnable {

private String fileLocation;

public UploadThread(String s){
	this.fileLocation=s;
}

@Override
public void run() {
	//your api call to uploadBtn file using fileLocation
}
/*
@Override
public String toString(){
	return this.command;
}*/
}
