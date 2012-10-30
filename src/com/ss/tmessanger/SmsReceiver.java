package com.ss.tmessanger;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class SmsReceiver extends BroadcastReceiver
{
	static final String TAG = "SMS Receiver";
	Context context;
	String translatedText;
	
	@Override
	public void onReceive(Context context, Intent arg1)
	{
		// TODO Auto-generated method stub
		this.context = context;

		ReceiveSMS(arg1.getExtras());
		
	}
	@TargetApi(16)
	public void ReceiveSMS(Bundle bundle)
	{
		String recMsgString = "";            
        SmsMessage recMsg = null;
        if (bundle != null)
        {
            //---retrieve the SMS message received---
           Object[] pdus = (Object[]) bundle.get("pdus");
            for (int i=0; i<pdus.length; i++)
            {
                recMsg = SmsMessage.createFromPdu((byte[])pdus[i]);
                recMsgString = recMsg.getMessageBody();
                Log.d(TAG, recMsg.getOriginatingAddress());
            }
        }		

        new MyAsyncTasks()
        {
        	protected void onPostExecute(Boolean result)
        	{
                Toast.makeText(context, translatedText, Toast.LENGTH_LONG).show();
        	}
        }.execute(recMsgString);
        
        
        NotificationManager notifyMgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

//        notify.icon = android.R.drawable.stat_notify_chat;
//        notify.tickerText = translatedText;
//        notify.when = System.currentTimeMillis();

        CharSequence contentTitle = "Translated Message";
        CharSequence contentText = translatedText;

        Notification.Builder blder = new Notification.Builder(context);
        blder.setContentTitle(contentTitle);
        blder.setContentText(contentText);
        blder.setSmallIcon(android.R.drawable.stat_notify_chat);
        //blder.setLargeIcon(R.drawable.ic_launcher);
        Notification note = blder.build();
        //(context, contentTitle, contentText, null);
        notifyMgr.notify(1, note);
	}
	
	class MyAsyncTasks extends AsyncTask<String, Integer, Boolean>
	{
		@Override
		protected Boolean doInBackground(String... params) 
		{
		/*	new Runnable()
			{
				public void run() 
				{*/
					Translate.setClientId("02e77b67-885e-497c-aa81-d77fce891f1b");
					Translate.setClientSecret("HTTgIhQlBKGV/3yB2O0yuXqJtq6DekvKWva66o9Vtds=");
		            try 
		            {
		            	translatedText = Translate.execute(params[0], Language.AUTO_DETECT, Language.SPANISH);
		            } 
		            catch(Exception e) 
		            {
		            	translatedText = e.toString();
		            }
				/*}
			};*/

            return true;
		}
		
	}
}
