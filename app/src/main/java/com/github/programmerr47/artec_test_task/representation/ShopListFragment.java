package com.github.programmerr47.artec_test_task.representation;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.programmerr47.artec_test_task.R;
import com.github.programmerr47.artec_test_task.api.MakePOSTRequest;
import com.github.programmerr47.artec_test_task.api.objects.GetShopsObject;
import com.github.programmerr47.artec_test_task.api.objects.Location;
import com.github.programmerr47.artec_test_task.api.objects.Position;
import com.github.programmerr47.artec_test_task.api.objects.Radius;
import com.github.programmerr47.artec_test_task.api.objects.ShopsResult;
import com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.from.ShopsResultParserFromJSON;
import com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.to.GetShopsObjectParserToJSON;
import com.github.programmerr47.artec_test_task.api.utils.ContentType;
import com.github.programmerr47.artec_test_task.representation.adapters.ShopsAdapter;
import com.github.programmerr47.artec_test_task.representation.tasks.AsyncTaskWithListener;
import com.github.programmerr47.artec_test_task.representation.tasks.MakePostRequestTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public class ShopListFragment extends Fragment implements AsyncTaskWithListener.OnTaskFinishedListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String URL = "https://qbank.ru:443/MobileBankServer/AdvertisementService.svc/Query";

    private MakePostRequestTask<GetShopsObject, JSONObject, JSONArray, ShopsResult> getShopsTask;

    private SwipeRefreshLayout mRefreshLayout;
    private ListView mTripListView;
    private ProgressBar mLoadingProgress;

    private List<Location> mShops;
    private Position mUserPosition;
    private ShopsAdapter mAdapter;
    private LocationManager mLocationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mUserPosition = getDefaultUserPosition();
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

        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            LocationListener locListener = new UserLocationListener();
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 100, locListener);
        }

        mRefreshLayout.setColorScheme(R.color.blue_soft_very, R.color.orange_bright, R.color.green_moderate, R.color.yellow_bright);
        mRefreshLayout.setOnRefreshListener(this);

        if (mShops != null) {
            mAdapter = new ShopsAdapter(getActivity(), mShops, mUserPosition);
            mLoadingProgress.setVisibility(View.GONE);
        } else {
            mAdapter = new ShopsAdapter(getActivity(), new ArrayList<Location>(), mUserPosition);
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
        Log.v("TEST", String.valueOf(extraObject));

        if (taskName.equals(MakePostRequestTask.class.getName())) {
            if (extraObject != null) {
                //TODO parsing. I can't this do because server is not responding
                ShopsResult result = (ShopsResult) extraObject;
                mShops = result.getLocations();
                mAdapter.refreshItems(mShops);
            } else {
                mShops = new ArrayList<Location>();
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
        if (getShopsTask != null) {
            getShopsTask.cancel(true);
        }

        getShopsTask = new MakePostRequestTask<GetShopsObject, JSONObject, JSONArray, ShopsResult>(new ShopsResultParserFromJSON());
        getShopsTask.setOnTaskFinishedListener(this);
        getShopsTask.execute(buildMakeShopRequest());
    }

    private MakePOSTRequest<GetShopsObject, JSONObject, JSONArray> buildMakeShopRequest() {
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
                .setPosition(mUserPosition)
                .setOnlyIds(false)
                .setProviderFilter(providerFilter)
                .setSkip(0)
                .setTop(50)
                .setTypeFilter(typeFilter)
                .setRadius(radius)
                .build();

        MakePOSTRequest<GetShopsObject, JSONObject, JSONArray> request = new MakePOSTRequest<GetShopsObject, JSONObject, JSONArray>();
        request.setContentType(ContentType.APPLICATION_JSON.toString());
        request.setUrl(URL);
        request.setParser(new GetShopsObjectParserToJSON());
        request.setObject(postObject);

        return request;
    }

    private Position getDefaultUserPosition() {
        return new Position.Builder()
                .setLatitude(55.708337)
                .setLongitude(37.651938999999999)
                .build();
    }

    /* Class My Location Listener */
    public class UserLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(android.location.Location loc)
        {
            Position userPosition = new Position.Builder()
                    .setLatitude(loc.getLatitude())
                    .setLongitude(loc.getLongitude())
                    .build();
            mUserPosition = userPosition;
            mAdapter.updateUserPosition(userPosition);

            mLoadingProgress.setVisibility(View.GONE);
            mRefreshLayout.setRefreshing(true);
            refreshList();
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            if (getActivity() != null) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.GPS_DISABLED), Toast.LENGTH_SHORT ).show();
            }
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            if (getActivity() != null) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.GPS_ENABLED), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
    }
}
