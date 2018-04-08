package com.awn.app.bingkainusantara.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.awn.app.bingkainusantara.fragment.KulinerFragment;
import com.awn.app.bingkainusantara.fragment.SeniFragment;
import com.awn.app.bingkainusantara.fragment.WisataFragment;

/**
 * Created by adewijanugraha on 31/03/18.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    //nama tab nya
    String[] title = new String[]{
            "Wisata", "Kuliner", "Seni"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new WisataFragment();
                break;
            case 1:
                fragment = new KulinerFragment();
                break;
            case 2:
                fragment = new SeniFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}