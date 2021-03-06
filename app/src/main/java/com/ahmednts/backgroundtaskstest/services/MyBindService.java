package com.ahmednts.backgroundtaskstest.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ahmednts.backgroundtaskstest.R;

public class MyBindService extends Service
{
	private NotificationManager mNM;

	// Unique Identification Number for the Notification.
	// We use it on Notification start, and to cancel it.
	private int NOTIFICATION = R.string.local_service_started;

	// This is the object that receives interactions from clients.  See
	// RemoteService for a more complete example.
	private final IBinder mBinder = new LocalBinder();

	/**
	 * Class for clients to access.  Because we know this service always
	 * runs in the same process as its clients, we don't need to deal with
	 * IPC.
	 */
	public class LocalBinder extends Binder
	{
		MyBindService getService()
		{
			return MyBindService.this;
		}
	}

	@Override
	public void onCreate()
	{
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// Display a notification about us starting.  We put an icon in the status bar.
		showNotification();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i("MyBindService", "Received start id " + startId + ": " + intent);
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy()
	{
		// Cancel the persistent notification.
		mNM.cancel(NOTIFICATION);

		// Tell the user we stopped.
		Toast.makeText(this, "Local Service Stopped", Toast.LENGTH_SHORT).show();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return mBinder;
	}

	/**
	 * Show a notification while this service is running.
	 */
	private void showNotification()
	{
		// In this sample, we'll use the same text for the ticker and the expanded notification
		CharSequence text = getText(R.string.local_service_started);

		// The PendingIntent to launch our activity if the user selects this notification
//		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, LocalServiceActivities.Controller.class), 0);
		// Set the info for the views that show in the notification panel.
		Notification notification = new Notification.Builder(this)
//				.setSmallIcon(R.drawable.ic_menu_gallery)  // the status icon
				.setTicker(text)  // the status text
				.setWhen(System.currentTimeMillis())  // the time stamp
				.setContentTitle(getText(R.string.app_name))  // the label of the entry
				.setContentText(text)  // the contents of the entry
//				.setContentIntent(contentIntent)  // The intent to send when the entry is clicked
				.build();

		// Send the notification.
		mNM.notify(NOTIFICATION, notification);
	}

//	public void uploadBinary(final Context context)
//	{
//		try
//		{
//			String filePath = "/storage/extSdCard/DCIM/Camera/20161010_165055.mp4";
//			String uploadId = new BinaryUploadRequest(context, "http://ftp.mccarabia.com/ShowReYApp/Showrey.svc/PostImages?PostId=14&UserId=28&LevelId=0")
//					.setFileToUpload(filePath)
////							.addHeader("file-name", new File(filePath).getName())
//					.setNotificationConfig(new UploadNotificationConfig())
//					.setMaxRetries(2)
//					.startUpload();
//		}
//		catch (Exception exc)
//		{
//			Log.e("AndroidUploadService", exc.getMessage(), exc);
//		}
//	}
}
