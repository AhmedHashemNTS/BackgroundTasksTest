package com.ahmednts.backgroundtaskstest.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class MyIntentService extends IntentService
{
	private static final String ACTION_FOO = "com.ahmednts.backgroundtaskstest.Services.action.FOO";

	private static final String EXTRA_PARAM1 = "com.ahmednts.backgroundtaskstest.Services.extra.PARAM1";
	private static final String EXTRA_PARAM2 = "com.ahmednts.backgroundtaskstest.Services.extra.PARAM2";

	public static void startActionFoo(Context context, String param1, String param2)
	{
		Intent intent = new Intent(context, MyIntentService.class);
		intent.setAction(ACTION_FOO);
		intent.putExtra(EXTRA_PARAM1, param1);
		intent.putExtra(EXTRA_PARAM2, param2);
		context.startService(intent);
	}

	public MyIntentService()
	{
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		if (intent != null)
		{
			final String action = intent.getAction();
			if (ACTION_FOO.equals(action))
			{
				final String param1 = intent.getStringExtra(EXTRA_PARAM1);
				final String param2 = intent.getStringExtra(EXTRA_PARAM2);
				handleActionFoo(param1, param2);
			}
		}
	}

	private void handleActionFoo(String param1, String param2)
	{
	}
}
