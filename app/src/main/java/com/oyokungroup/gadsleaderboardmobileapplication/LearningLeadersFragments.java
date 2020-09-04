package com.oyokungroup.gadsleaderboardmobileapplication;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.oyokungroup.gadsleaderboardmobileapplication.adapter.LearnerAdapter;
import com.oyokungroup.gadsleaderboardmobileapplication.api.Client;
import com.oyokungroup.gadsleaderboardmobileapplication.api.Service;
import com.oyokungroup.gadsleaderboardmobileapplication.model.LearnerResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragments extends Fragment {
    RecyclerView recyclerView;
    LearnerAdapter adapter;
    List<LearnerResponse>list;
    ProgressDialog pd;
    LinearLayoutManager layoutManager;

    public static final String LOG_TAG = LearnerAdapter.class.getName();

    public LearningLeadersFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.learning_leaders_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.learningRecyclerView);
        initViews();

        recyclerView =  v.findViewById(R.id.learningRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(30);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

    private void initViews() {
        pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching data now...");
        pd.setCancelable(false);
        pd.show();
        list = new ArrayList<>();
        adapter = new LearnerAdapter(getContext(),list);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON();
    }

    private void loadJSON() {
        try {
            Client client = new Client();
            Service apiService = client.getClient().create(Service.class);
            Call<List<LearnerResponse>> call = apiService.getLearners();
            call.enqueue(new Callback<List<LearnerResponse>>() {
                @Override
                public void onResponse(Call<List<LearnerResponse>> call, Response<List<LearnerResponse>> response) {
                    List<LearnerResponse> lists = response.body();
                    recyclerView.setAdapter(new LearnerAdapter(getContext(), lists));
                    recyclerView.smoothScrollToPosition(0);
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<List<LearnerResponse>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}