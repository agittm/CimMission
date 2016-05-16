package com.sdrcstudio.cimmission;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ErfranRplB on 15/05/2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index){
        switch (index){
            case 0:
                return new ProfilFragment();
            case 1:
                return new DokumenFragment();
        }
        return null;
    }

    @Override
    public int getCount(){
        return 2;
    }
}
