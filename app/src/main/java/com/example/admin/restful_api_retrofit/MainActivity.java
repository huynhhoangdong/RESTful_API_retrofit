package com.example.admin.restful_api_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.restful_api_retrofit.Adapter.AnswersAdapter;
import com.example.admin.restful_api_retrofit.data.model.Item;
import com.example.admin.restful_api_retrofit.data.model.SOAnswersRespone;
import com.example.admin.restful_api_retrofit.data.remote.ApiUtils;
import com.example.admin.restful_api_retrofit.data.remote.SOService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SOService mSOSoService;
    private AnswersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSOSoService = ApiUtils.getSOSerive();
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new AnswersAdapter(new ArrayList<Item>(0), this, new AnswersAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();

    }

    private void loadAnswers() {
        mSOSoService.getAnswers().enqueue(new Callback<List<SOAnswersRespone>>() {
            @Override
            public void onResponse(Call<List<SOAnswersRespone>> call, Response<List<SOAnswersRespone>> response) {
                if(response.isSuccessful()) {
                    for(int i = 0; i < response.body().size(); i++ ) {
                        //mAdapter.updateAnswers(response.body().get(i).getItems());
                        Log.d("MainActivity", "posts loaded from API");
                    }
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<SOAnswersRespone>> call, Throwable t) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}
