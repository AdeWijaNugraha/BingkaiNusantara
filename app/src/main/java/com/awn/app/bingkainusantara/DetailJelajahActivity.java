package com.awn.app.bingkainusantara;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.awn.app.bingkainusantara.data.KulinerItems;
import com.awn.app.bingkainusantara.data.SeniItems;
import com.awn.app.bingkainusantara.data.WisataItems;
import com.bumptech.glide.Glide;

public class DetailJelajahActivity extends AppCompatActivity {
    public static String TYPE = "type";
    public static String KEY = "item";
    ImageView ivDetailPict;
    TextView tvDetailTitle, tvDetailDesc, tvDate, tvAuthor;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsing_toolbar;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jelajah);

        ivDetailPict = (ImageView) findViewById(R.id.iv_detail_pict);
        tvDetailTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvDetailDesc = (TextView) findViewById(R.id.tv_detail_desc);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        type = getIntent().getStringExtra(TYPE);

        if (type.equals("kuliner")){

            KulinerItems item = (KulinerItems) getIntent().getSerializableExtra(KEY);
            getSupportActionBar().setTitle(item.getTitle());
            Glide.with(DetailJelajahActivity.this)
                    .load(item.getPicture())
                    .placeholder(new ColorDrawable(Color.GRAY))
                    .error(new ColorDrawable(Color.RED))
                    .fallback(new ColorDrawable(Color.BLUE))
                    .centerCrop()
                    .override(350, 500)
                    .into(ivDetailPict);

            tvDetailTitle.setText(item.getTitle());
            tvDetailDesc.setText(item.getDescription());
            tvDate.setText(item.getDate());
            tvAuthor.setText(item.getAuthor());

        } else if(type.equals("seni")){
            SeniItems item = (SeniItems) getIntent().getSerializableExtra(KEY);
            getSupportActionBar().setTitle(item.getTitle());
            Glide.with(DetailJelajahActivity.this)
                    .load(item.getPicture())
                    .placeholder(new ColorDrawable(Color.GRAY))
                    .error(new ColorDrawable(Color.RED))
                    .fallback(new ColorDrawable(Color.BLUE))
                    .centerCrop()
                    .override(350, 500)
                    .into(ivDetailPict);

            tvDetailTitle.setText(item.getTitle());
            tvDetailDesc.setText(item.getDescription());
            tvDate.setText(item.getDate());
            tvAuthor.setText(item.getAuthor());

        } else if (type.equals("wisata")){
            WisataItems item = (WisataItems) getIntent().getSerializableExtra(KEY);
            getSupportActionBar().setTitle(item.getTitle());
            Glide.with(DetailJelajahActivity.this)
                    .load(item.getPicture())
                    .placeholder(new ColorDrawable(Color.GRAY))
                    .error(new ColorDrawable(Color.RED))
                    .fallback(new ColorDrawable(Color.BLUE))
                    .centerCrop()
                    .override(350, 500)
                    .into(ivDetailPict);

            tvDetailTitle.setText(item.getTitle());
            tvDetailDesc.setText(item.getDescription());
            tvDate.setText(item.getDate());
            tvAuthor.setText(item.getAuthor());
        }
    }
}
