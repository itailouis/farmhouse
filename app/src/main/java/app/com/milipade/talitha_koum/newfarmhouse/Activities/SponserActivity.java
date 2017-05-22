package app.com.milipade.talitha_koum.newfarmhouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseAuthenticatedActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Fragments.BuyerFragment;
import app.com.milipade.talitha_koum.newfarmhouse.R;
import app.com.milipade.talitha_koum.newfarmhouse.Views.MainNavDrawer;


public class SponserActivity extends BaseAuthenticatedActivity{
	private TabHost host;
	public FloatingActionButton fabBtn;

	@Override
	public void onSocialCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_home);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setNavDrawer(new MainNavDrawer(this));
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_sponser);
		ViewPager viewPager  = (ViewPager) findViewById(R.id.viewpager_contract);
		//appbarLayout.addOnOffsetChangedListener(this);
		//mMaxScrollSize = appbarLayout.getTotalScrollRange();

		viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
		fabBtn= (FloatingActionButton) findViewById(R.id.fab);
		fabBtn.setVisibility(View.GONE);
		fabBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
     //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
	}

	public static void start(Context c) {
		c.startActivity(new Intent(c, SponserActivity.class));
	}


	}

	class TabsAdapter extends FragmentPagerAdapter {
		public TabsAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 1;
		}

		@Override
		public Fragment getItem(int i) {
			switch(i) {
				case 0:
					return BuyerFragment.newInstance();
				//case 1:
				//	return MySaleFragment.newInstance();
				//case 1:
					//return DealFragment.newInstance();
			}
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch(position) {
				case 0: return "Buyers";
				//case 1: return "My Sales";
				//case 1: return "Deals";
			}
			return "";
		}


}
