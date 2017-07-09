package com.ahmednts.backgroundtaskstest.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedNTS on 2016-09-25.
 */
public class EmployeeLoader extends AsyncTaskLoader<List<Employee>>
{
	private List<Employee> list;

	public EmployeeLoader(Context context)
	{
		super(context);
	}

	@Override
	protected void onStartLoading()
	{
		if (list != null && list.size() > 0)
			deliverResult(list);
		else
			forceLoad();
	}

	@Override
	public List<Employee> loadInBackground()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		list = new ArrayList<>();
		list.add(new Employee("emp1", "Brahma"));
		list.add(new Employee("emp2", "Vishnu"));
		list.add(new Employee("emp3", "Mahesh"));
		return list;
	}
}
