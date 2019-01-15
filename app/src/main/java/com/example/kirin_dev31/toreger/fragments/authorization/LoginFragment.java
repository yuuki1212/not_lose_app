package com.example.kirin_dev31.toreger.fragments.authorization;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.activity.MainActivity;
import com.example.kirin_dev31.toreger.network.loaders.LoginLoader;
import com.example.kirin_dev31.toreger.utils.UtilValidator;
import com.example.kirin_dev31.toreger.views.Constants;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmailView     = view.findViewById(R.id.email);
        mPasswordView  = view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_DONE == actionId || EditorInfo.IME_NULL == actionId) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mSubmitButton = view.findViewById(R.id.login_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // 新規登録フラグメントの起動
        getActivity().findViewById(R.id.register_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.BOTTOM);

                TransitionSet set = new TransitionSet();
                set.addTransition(slide);

                Fragment fragment = new RegisterFragment();
                fragment.setEnterTransition(set);
                getFragmentManager().beginTransaction()
                        .add(R.id.login_layout, fragment).commit();
            }
        });

        mProgressView = view.findViewById(R.id.auth_progress);
        mLoginFormView = view.findViewById(R.id.login_form);
    }


    /**
     * ログイン処理
     */
    private void attemptLogin () {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        Map<TextView, String[]> validates = new HashMap<TextView, String[]>();
        // バリデーションルール
        String[] email_param = {email, "required/email/hankaku"};
        String[] password_param = {password, "required/hankaku"};
        validates.put(mEmailView, email_param);
        validates.put(mPasswordView, password_param);

        // バリデーションの実行
        UtilValidator validator = new UtilValidator(getContext());
        Map<TextView, String> validateResponse = validator.validate(validates);

        for (TextView key : validateResponse.keySet()) {
            cancel = true;
            key.setError(validateResponse.get(key));
            focusView = key;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            // 引数を格納し、ログイン通信
            Bundle args = new Bundle();
            args.putString(Constants.KEY_USER_ID, mEmailView.getText().toString());
            args.putString(Constants.KEY_PASSWORD, mPasswordView.getText().toString());
            getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_LOGIN_ID, args, mCallBack);
            showProgress(true);
        }
    }

    /**
     * ProgressBar表示制御
     * @param show
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private final LoaderManager.LoaderCallbacks<String> mCallBack = new LoaderManager.LoaderCallbacks<String>() {

        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            return new LoginLoader(getContext(), args);
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String message) {
            // ローダーの破棄
            getLoaderManager().destroyLoader(loader.getId());
            Intent intent = null;
            if (message.isEmpty()) {
                intent = new Intent(getContext(), MainActivity.class);
            } else {

            }

            startActivity(intent);
            getActivity().finish();
            showProgress(false);
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
    };
}
