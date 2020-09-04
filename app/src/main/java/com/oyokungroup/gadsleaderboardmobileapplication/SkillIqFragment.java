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

import com.oyokungroup.gadsleaderboardmobileapplication.adapter.SkillsAdapter;
import com.oyokungroup.gadsleaderboardmobileapplication.api.Client;
import com.oyokungroup.gadsleaderboardmobileapplication.api.Service;
import com.oyokungroup.gadsleaderboardmobileapplication.model.SkillResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqFragment extends Fragment {

    RecyclerView recyclerView;
    SkillsAdapter adapter;
    List<SkillResponse>list;
    ProgressDialog pd;
    LinearLayoutManager layoutManager;

    public static final String LOG_TAG = SkillsAdapter.class.getName();

    public SkillIqFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.skill_iq_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.skillIqRecyclerView);
        initViews();

        recyclerView =  v.findViewById(R.id.skillIqRecyclerView);
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
        adapter = new SkillsAdapter(getContext(),list);

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
            Call<List<SkillResponse>> call = apiService.getSkillIqs();
            call.enqueue(new Callback<List<SkillResponse>>() {
                @Override
                public void onResponse(Call<List<SkillResponse>> call, Response<List<SkillResponse>> response) {
                    List<SkillResponse> lists = response.body();
                    recyclerView.setAdapter(new SkillsAdapter(getContext(), lists));
                    recyclerView.smoothScrollToPosition(0);
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<List<SkillResponse>> call, Throwable t) {
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
