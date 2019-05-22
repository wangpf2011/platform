package com.wf.example.behavior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wf.example.R;

public class ScrollingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_behavior_scrolling);

        recyclerView = (RecyclerView) getWindow().findViewById(R.id.rv);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.demo_behavior_item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.text.setText("RecyclerView Item " + (position + 1));
                vh.text2.setText("CoordinatorLayout and Behavior realize moving up/down...");
            }

            @Override
            public int getItemCount() {
                return 20;
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                TextView text;
                TextView text2;

                public ViewHolder(View itemView) {
                    super(itemView);

                    text = (TextView) itemView.findViewById(R.id.text);
                    text2 = (TextView) itemView.findViewById(R.id.text2);
                }

            }
        });
    }
}
