package com.example.a2139619.testsharedelement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	View notWorkingContainer;
	View notWorkingText;
	View image;

	View workingContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		notWorkingContainer = findViewById(R.id.main_frame);
		notWorkingContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startDetailActivity();
			}
		});

		notWorkingText = findViewById(R.id.main_text);
		image = findViewById(R.id.image_view);

		workingContainer = findViewById(R.id.second_frame);
		workingContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startDetailActivity();
			}
		});
	}

	public void startDetailActivity() {
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
				Pair.create(notWorkingContainer, notWorkingContainer.getTransitionName()),
				Pair.create(notWorkingText, notWorkingText.getTransitionName()),
				Pair.create(image, image.getTransitionName()),
				Pair.create(workingContainer, workingContainer.getTransitionName()));
		startActivity(new Intent(this, DetailActivity.class), options.toBundle());
	}
}
