package com.rand42.views;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import com.rand42.presenters.HomePresenter;
import com.rand42.presenters.ItemListFragmentPresenter;

/**
 * Activity representing the home page for the logged in user. Contains a actionbar tab with listFragments
 * @author Rand-42
 *
 */
public class HomeActivity extends Activity
{
              private HomePresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
        presenter = new HomePresenter();
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Welcome ");

        ActionBar.Tab tab = actionBar.newTab().setText("Lost").setTabListener(new MyTabListener<ItemListFragment>(this,"lost",ItemListFragmentPresenter.LOST_ITEMS,ItemListFragment.class));
        actionBar.addTab(tab);
        ActionBar.Tab tab2 = actionBar.newTab().setText("Found").setTabListener(new MyTabListener<ItemListFragment>(this,"lost",ItemListFragmentPresenter.FOUND_ITEMS,ItemListFragment.class));
        actionBar.addTab(tab2);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

    /**
     * Starts the process of populating the listview
     */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//TODO: Stefano really doesn't like looking at this.
		switch(item.getItemId())
		{
		case R.id.menu_logout:
			presenter.logOut();
			this.finish();
			return true;
        case R.id.menu_addItem:
            Intent i = new Intent(this, NewItemActivity.class);
            startActivity(i);
            return true;
		default:
			return true;
		}
	}
	@Override
	public void onBackPressed()
	{
	    presenter.logOut();
		this.finish();
	}


    /**
     * The TabListener for this Acitivty. Controls tab logic
     * @param <T> Fragment Class
     */
    public static class MyTabListener<T> implements ActionBar.TabListener
    {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final int filter;
        public MyTabListener(Activity activity, String tag, int filter, Class<T> clazz)
        {
            mActivity =   activity;
            mTag = tag;
            mClass = clazz;
            this.filter=filter;
        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
        {
            if(mFragment==null)
            {
                mFragment = ItemListFragment.newInstance(filter);
                fragmentTransaction.add(android.R.id.content, mFragment, mTag);

            }
            else
            {
                fragmentTransaction.attach(mFragment);
                fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out );
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
        {
            if(mFragment!=null)
            {
                fragmentTransaction.detach(mFragment);
            }
        }


        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}