package com.example.kirin_dev31.toreger.fragments.authorization;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kirin_dev31.toreger.R;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 閉じるボタン
        view.findViewById(R.id.register_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    /**
     * 閉じるボタン押下時
     */
    public void close() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}
