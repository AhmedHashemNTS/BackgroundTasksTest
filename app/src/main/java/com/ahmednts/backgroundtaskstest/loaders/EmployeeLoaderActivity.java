package com.ahmednts.backgroundtaskstest.loaders;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ahmednts.backgroundtaskstest.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Employee>>
{
	static final int empLoaderID = 1;
	EmployeeAdapter empAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loaders);

		empAdapter = new EmployeeAdapter(this, new ArrayList<Employee>());
		ListView employeeListView = (ListView) findViewById(R.id.employees);
		employeeListView.setAdapter(empAdapter);

		getSupportLoaderManager().initLoader(empLoaderID, null, this);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		getSupportLoaderManager().destroyLoader(empLoaderID);
	}

	@Override
	public Loader<List<Employee>> onCreateLoader(int id, Bundle args)
	{
		return new EmployeeLoader(EmployeeLoaderActivity.this);
	}

	@Override
	public void onLoadFinished(Loader<List<Employee>> loader, List<Employee> items)
	{
		empAdapter.setEmployees(items);
	}

	@Override
	public void onLoaderReset(Loader<List<Employee>> loader)
	{
		empAdapter.setEmployees(new ArrayList<Employee>());
	}
}
