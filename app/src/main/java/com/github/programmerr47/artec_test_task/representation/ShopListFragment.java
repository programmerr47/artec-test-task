package com.github.programmerr47.artec_test_task.representation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.programmerr47.artec_test_task.R;
import com.github.programmerr47.artec_test_task.api.MakePOSTRequest;
import com.github.programmerr47.artec_test_task.api.objects.GetShopsObject;
import com.github.programmerr47.artec_test_task.api.objects.Position;
import com.github.programmerr47.artec_test_task.api.objects.Radius;
import com.github.programmerr47.artec_test_task.api.objects.Shop;
import com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.GetShopsObjectParserToJSON;
import com.github.programmerr47.artec_test_task.api.util.ContentType;
import com.github.programmerr47.artec_test_task.representation.adapters.ShopsAdapter;
import com.github.programmerr47.artec_test_task.representation.tasks.AsyncTaskWithListener;
import com.github.programmerr47.artec_test_task.representation.tasks.MakePostRequestTask;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public class ShopListFragment extends Fragment implements AsyncTaskWithListener.OnTaskFinishedListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String URL = "http://qbank.ru:3015/MobileBankServer/AdvertisementService.svc/Query";

    private MakePostRequestTask<GetShopsObject, JSONObject> getShopsTask;

    private SwipeRefreshLayout mRefreshLayout;
    private ListView mTripListView;
    private ProgressBar mLoadingProgress;

    private List<Shop> mShops;
    private ShopsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mTripListView = (ListView) view.findViewById(R.id.shopListView);
        mLoadingProgress = (ProgressBar) view.findViewById(R.id.loadingProgress);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRefreshLayout.setColorScheme(R.color.blue_soft_very, R.color.orange_bright, R.color.green_moderate, R.color.yellow_bright);
        mRefreshLayout.setOnRefreshListener(this);

        if (mShops != null) {
            mAdapter = new ShopsAdapter(getActivity(), mShops);
            mLoadingProgress.setVisibility(View.GONE);
        } else {
            mAdapter = new ShopsAdapter(getActivity(), new ArrayList<Shop>());
            mLoadingProgress.setVisibility(View.VISIBLE);
            refreshList();
        }

        mTripListView.setAdapter(mAdapter);
    }

    public void onDestroy() {
        super.onDestroy();

        if (getShopsTask != null) {
            getShopsTask.setOnTaskFinishedListener(null);
            getShopsTask.cancel(true);
        }
    }

    @Override
    public void onTaskFinished(String taskName, Object extraObject) {
        if (taskName.equals(MakePostRequestTask.class.getName())) {
            if (!MakePostRequestTask.ERROR_NO_PARAMS.equals(extraObject) &&
                !MakePostRequestTask.ERROR_SOMETHING_IN_QUERY.equals(extraObject) &&
                extraObject != null) {
                //TODO parsing. I can't this do because server is not responding
                String result = (String) extraObject;
                mShops = new ArrayList<Shop>();
                mAdapter.refreshItems(mShops);
            } else if (MakePostRequestTask.ERROR_SOMETHING_IN_QUERY.equals(extraObject)) {
                mShops = new ArrayList<Shop>();
                mAdapter.refreshItems(mShops);
            }

            mLoadingProgress.setVisibility(View.GONE);
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        refreshList();
    }

    private void refreshList() {
        getShopsTask = new MakePostRequestTask<GetShopsObject, JSONObject>();
        getShopsTask.setOnTaskFinishedListener(this);
        getShopsTask.execute(buildMakeShopRequest());
    }

    private MakePOSTRequest<GetShopsObject, JSONObject> buildMakeShopRequest() {
        Position position = new Position.Builder()
                .setLatitude(55.708337)
                .setLongitude(37.651938999999999)
                .build();

        Radius radius = new Radius.Builder()
                .setMinRadius(0)
                .setMaxRaduis(500)
                .build();

        List<Integer> providerFilter = new ArrayList<Integer>();
        providerFilter.add(0);
        providerFilter.add(1);

        List<Integer> typeFilter = new ArrayList<Integer>();
        typeFilter.add(2);

        GetShopsObject postObject = new GetShopsObject.Builder()
                .setType(2)
                .setPosition(position)
                .setOnlyIds(false)
                .setProviderFilter(providerFilter)
                .setSkip(0)
                .setTop(50)
                .setTypeFilter(typeFilter)
                .setRadius(radius)
                .build();

        MakePOSTRequest<GetShopsObject, JSONObject> request = new MakePOSTRequest<GetShopsObject, JSONObject>();
        request.setContentType(ContentType.APPLICATION_JSON.toString());
        request.setUrl(URL);
        request.setParser(new GetShopsObjectParserToJSON());
        request.setObject(postObject);

        return request;
    }
}
