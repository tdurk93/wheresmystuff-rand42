package com.rand42.views;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.rand42.model.LocalModel;
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
    
        /**
         * Called on creation of a HomeActivity view
         * @param savedInstanceState The "bundle" passed to this object
         */
        @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
        presenter = new HomePresenter(LocalModel.getModel());
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Welcome");

        ActionBar.Tab tab = actionBar.newTab().setText("Lost").setTabListener(new MyTabListener<ItemListFragment>(this,"lost",ItemListFragmentPresenter.LOST_ITEMS,ItemListFragment.class));
        actionBar.addTab(tab);
        ActionBar.Tab tab2 = actionBar.newTab().setText("Found").setTabListener(new MyTabListener<ItemListFragment>(this,"lost",ItemListFragmentPresenter.FOUND_ITEMS,ItemListFragment.class));
        actionBar.addTab(tab2);
	}
    
        /**
         * Called when the options menu is created
         * @param menu The menu to display ("inflate")
         * @return true (if the operation was successful)
         */
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
		//TODO: Stefano really doesn't like looking at this. Like for real.
		switch(item.getItemId())
		{
		/*case R.id.menu_logout:
			presenter.logOut();
			this.finish();
			return true;*/
        case R.id.menu_addItem:
            Intent i = new Intent(this, NewItemActivity.class);
            startActivity(i);
            return true;
        case R.id.menu_filter:
            Intent i2 = new Intent(this, NewFilterActivity.class);
            startActivity(i2);
            return true;
        case R.id.menu_search:
            super.onSearchRequested();
            return true;
		default:
			return true;

		}
	}
        /**
         * Called when the back button on the device is pressed.
         */
	@Override
	public void onBackPressed()
	{
        if(!LocalModel.getModel().getCurrentUser().isAdmin())
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
        
        /**
         * Creates a MyTabListener with the specified parameters
         * @param activity
         * @param tag
         * @param filter
         * @param clazz 
         */
        public MyTabListener(Activity activity, String tag, int filter, Class<T> clazz)
        {
            mActivity =   activity;
            mTag = tag;
            mClass = clazz;
            this.filter=filter;
        }
        
        /**
         * Called when a tab is selected
         * @param tab the tab that is now selected
         * @param fragmentTransaction The FragmentTransaction associated with this view change
         */
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
        
        /**
         * Called when a tab is unselected
         * @param tab the tab that is unselected
         * @param fragmentTransaction The FramgentTransaction associated with this view change
         */
        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
        {
            if(mFragment!=null)
            {
                fragmentTransaction.detach(mFragment);
            }
        }
        
        /**
         * Called when a tab is re-selected
         * Method is not used in this implementation
         * @param tab The tab that is re-selected
         * @param fragmentTransaction The FragmentTransaction associated with this view change
         */
        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
