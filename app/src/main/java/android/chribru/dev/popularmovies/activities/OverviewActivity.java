package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.adapters.OverviewAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.chribru.dev.popularmovies.R;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        RecyclerView rvOverview = findViewById(R.id.rv_overview);
        rvOverview.setLayoutManager(new GridLayoutManager(this, 3));
        OverviewAdapter adapter = new OverviewAdapter();
        rvOverview.setAdapter(adapter);
    }

}
