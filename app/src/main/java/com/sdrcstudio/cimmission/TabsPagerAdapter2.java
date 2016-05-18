package com.sdrcstudio.cimmission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sdrcstudio.cimmission.fragment.DokumenFragment;
import com.sdrcstudio.cimmission.fragment.ListCalonInvestorFragment;
import com.sdrcstudio.cimmission.fragment.ListInvestorFragment;
import com.sdrcstudio.cimmission.fragment.ProfilFragment;

/**
 * Created by ErfranRplB on 15/05/2016.
 */
public class TabsPagerAdapter2 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsPagerAdapter2(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ListCalonInvestorFragment tab1 = new ListCalonInvestorFragment();
                return tab1;
            case 1:
                ListInvestorFragment tab2 = new ListInvestorFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
