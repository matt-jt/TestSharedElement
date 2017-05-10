package com.example.a2139619.testsharedelement;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	public final String TAG = "MainActivity:Transition";

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

		setExitSharedElementCallback(new SharedElementCallback() {
			@Override
			public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
				super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
				for (View v : sharedElements) {
					Log.d(TAG, "onSharedElementStart: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
			}

			@Override
			public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
				super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
				for (View v : sharedElements) {
					Log.d(TAG, "onSharedElementEnd: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
			}

			@Override
			public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
				super.onMapSharedElements(names, sharedElements);
				for (View v : sharedElements.values()) {
					Log.d(TAG, "onMapSharedElements: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
			}

			@Override
			public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
				super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
				for (View v : sharedElements) {
					Log.d(TAG, "onSharedElementsArrived: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
			}

			@Override
			public void onRejectSharedElements(List<View> rejectedSharedElements) {
				for (View v : rejectedSharedElements) {
					Log.d(TAG, "onRejectSharedElements: " + v.getTransitionName());
				}
				super.onRejectSharedElements(rejectedSharedElements);
			}

			@Override
			public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
				Log.d(TAG, "onCaptureSharedElementSnapshot: " + sharedElement.getTransitionName() + ", x: " + sharedElement.getX() + ", y: " + sharedElement.getY());
				Log.d(TAG, "onCaptureSharedElementSnapshot: " + sharedElement.getTransitionName() + ", matrix: " + viewToGlobalMatrix + ", bounds: " + screenBounds);
				return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
			}

			@Override
			public View onCreateSnapshotView(Context context, Parcelable snapshot) {
				return super.onCreateSnapshotView(context, snapshot);
			}
		});
	}

	public void startDetailActivity() {
		Log.d(TAG, "onCreate: " + notWorkingText.getTransitionName() + ": x: " + notWorkingText.getX() + ", y: " + notWorkingText.getY());
		Log.d(TAG, "onCreate: " + image.getTransitionName() + ": x: " + image.getX() + ", y: " + image.getY());
		Log.d(TAG, "onCreate: " + notWorkingContainer.getTransitionName() + ": x: " + notWorkingContainer.getX() + ", y: " + notWorkingContainer.getY());
		Log.d(TAG, "onCreate: " + workingContainer.getTransitionName() + ": x: " + workingContainer.getX() + ", y: " + workingContainer.getY());

		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
				Pair.create(notWorkingContainer, notWorkingContainer.getTransitionName()),
				Pair.create(notWorkingText, notWorkingText.getTransitionName()),
				Pair.create(image, image.getTransitionName()),
				Pair.create(workingContainer, workingContainer.getTransitionName()));
		startActivity(new Intent(this, DetailActivity.class), options.toBundle());
	}
}
