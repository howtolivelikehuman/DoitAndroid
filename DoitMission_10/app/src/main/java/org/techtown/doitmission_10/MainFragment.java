package org.techtown.doitmission_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_main,container,false);

        pager = (ViewPager)view.findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(3);

        MypaperAdapter adapter = new MypaperAdapter(getChildFragmentManager());
        fragment1 = new Fragment1();
        adapter.addItem(fragment1);
        fragment2 = new Fragment2();
        adapter.addItem(fragment2);
        fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        return view;
    }

    class MypaperAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MypaperAdapter(FragmentManager fa) {
            super(fa);
        }

        public void addItem(Fragment f){
            items.add(f);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return  items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}

