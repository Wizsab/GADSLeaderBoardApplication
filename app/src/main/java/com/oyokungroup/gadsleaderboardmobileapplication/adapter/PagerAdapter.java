package com.oyokungroup.gadsleaderboardmobileapplication.adapter;


import com.oyokungroup.gadsleaderboardmobileapplication.LearningLeadersFragments;
import com.oyokungroup.gadsleaderboardmobileapplication.SkillIqFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter  extends FragmentStatePagerAdapter {

    int NoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.NoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                LearningLeadersFragments tabLearn = new LearningLeadersFragments();
                return tabLearn;

            case 1:
                SkillIqFragment tabSkill = new SkillIqFragment();
                return tabSkill;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NoOfTabs;
    }
}
