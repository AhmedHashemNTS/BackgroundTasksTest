package com.ahmednts.backgroundtaskstest.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ahmednts.backgroundtaskstest.R;

public class MyBindServiceActivity extends AppCompatActivity
{
	private MyBindService mBoundService;

	boolean mIsBound;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_service);

		doBindService();
	}

	void doBindService()
	{
		// Establish a connection with the service.  We use an explicit
		// class name because we want a specific service implementation that
		// we know will be running in our own process (and thus won't be
		// supporting component replacement by other applications).
		bindService(new Intent(MyBindServiceActivity.this, MyBindService.class), mConnection, Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService()
	{
		if (mIsBound)
		{
			// Detach our existing connection.
			unbindService(mConnection);
			mIsBound = false;
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		doUnbindService();
	}

	private ServiceConnection mConnection = new ServiceConnection()
	{
		public void onServiceConnected(ComponentName className, IBinder service)
		{
			// This is called when the connection with the service has been
			// established, giving us the service object we can use to
			// interact with the service.  Because we have bound to a explicit
			// service that we know is running in our own process, we can
			// cast its IBinder to a concrete class and directly access it.
			mBoundService = ((MyBindService.LocalBinder) service).getService();

			// Tell the user about this for our demo.
			Toast.makeText(MyBindServiceActivity.this, "local_service_connected", Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className)
		{
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			// Because it is running in our same process, we should never
			// see this happen.
			mBoundService = null;
			Toast.makeText(MyBindServiceActivity.this, "local_service_disconnected", Toast.LENGTH_SHORT).show();
		}
	};
}
