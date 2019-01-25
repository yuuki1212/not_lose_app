package com.example.kirin_dev31.toreger.views.fragments.home;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.domain.models.ApiModel;
import com.example.kirin_dev31.toreger.domain.models.GridItem;
import com.example.kirin_dev31.toreger.domain.network.loaders.BaseAsyncTaskLoader;
import com.example.kirin_dev31.toreger.domain.network.loaders.home.GetSpaceItemLoader;
import com.example.kirin_dev31.toreger.domain.prefs.PreferenceUtil;
import com.example.kirin_dev31.toreger.util.Constants;
import com.example.kirin_dev31.toreger.views.adapter.GridItemAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GridFragment extends Fragment {

    private static final String GRID_ITEM_LIST = "GridItemList";

    private GridView gridView;
    private GridItemAdapter adapter;

    public GridFragment() {
        // 縦横切り替えしたときにFragmentの再作成をしないよう設定
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(GRID_ITEM_LIST, adapter.getItemList());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (adapter == null) {
            if (savedInstanceState != null) {
                List<GridItem> list = (List<GridItem>) savedInstanceState.get(GRID_ITEM_LIST);
                adapter = new GridItemAdapter(getContext(), R.layout.grid_item, list);
            } else {
                Bundle args = new Bundle();
                args.putInt("space_id", 0);
                args.putString("token", PreferenceUtil.getFindById(getContext(), PreferenceUtil.ACCESS_TOKEN_KEY));
                args.putString("cateogry_id", null);
                args.putInt("user_id", );

                        this.args.getString(Constants.KEY_TOKEN), this.args.getString(Constants.KEY_CATEGORY_ID),
                        this.args.getInt(Constants.KEY_USER_ID), this.args.getInt(Constants.KEY_USAGES_ID),
                        this.args.getInt(Constants.KEY_MAIN_NO), this.args.getInt(Constants.KEY_TREE_NO)
                getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_GET_SPACE_ITEM_ID, )
//                List<GridItem> itemList = new ArrayList<GridItem>();
//                for (int i = 0; i < 5; i++) {
//                    GridItem item = new GridItem();
//                    GridItem.Space space = new GridItem.Space();
//                    space.name = "1";
//                    item.space = space;
//                    itemList.add(item);
//                }
//                adapter = new GridItemAdapter(this.getContext(), R.layout.grid_item, itemList);

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.grid_flagment, container, false);
        gridView = view.findViewById(R.id.grid_item);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Loader
     */
    private final LoaderManager.LoaderCallbacks<Object> mCallBack = new LoaderManager.LoaderCallbacks<Object>() {
        @Override
        public Loader<Object> onCreateLoader(int id, Bundle args) {
            BaseAsyncTaskLoader loader = null;

            // Loaderごとに処理を分ける
            switch (id) {
                case Constants.LOADER_ID.LOADER_GET_SPACE_ITEM_ID :
                    loader = new GetSpaceItemLoader(getContext(), args);
            }
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Object> loader, Object data) {
            // 処理が終わったLoaderを破棄
            getLoaderManager().destroyLoader(loader.getId());

            // Loaderごとに処理を分ける
            switch (loader.getId()) {
                case Constants.LOADER_ID.LOADER_GET_SPACE_ITEM_ID :
                    adapter = new GridItemAdapter(getContext(), R.layout.grid_item, (List<GridItem>) data);
                    adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onLoaderReset(Loader<Object> loader) {

        }
    };
}
