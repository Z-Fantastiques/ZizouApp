package co.ogury.ferreolgodebarge.testapk.tabSlider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import co.ogury.ferreolgodebarge.testapk.tabFragment.OguryTestAds;
import co.ogury.ferreolgodebarge.testapk.tabFragment.Soundbox;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        // Fragment selection
        if (position == 0) {
            return Soundbox.newInstance();
        }
        if (position == 1) {
            return OguryTestAds.newInstance();
        } else {
            //Useless fragment
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}
