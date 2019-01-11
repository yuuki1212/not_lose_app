package com.example.kirin_dev31.toreger.activity.authorization;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.fragments.authorization.LoginFragment;

public class AuthorizationActivity extends Activity {
    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.auth_activity);

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.BOTTOM);

        TransitionSet set = new TransitionSet();
        set.addTransition(slide);

        Fragment fragment = new LoginFragment();
        fragment.setEnterTransition(set);

        getFragmentManager().beginTransaction()
                .add(R.id.auth_layout, fragment).commit();
    }
}
