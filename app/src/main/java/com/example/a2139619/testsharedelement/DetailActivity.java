package com.example.a2139619.testsharedelement;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

	public final String TAG = "DetailActivity:Transition";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		setEnterSharedElementCallback(new SharedElementCallback() {
			@Override
			public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
				super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
				View container = null;
				View text = null;
				View image = null;
				for (View v : sharedElements) {
					if (v.getTransitionName().equals("container_one")) {
						container = v;
					} else if (v.getTransitionName().equals("text_one")) {
						text = v;
					} else if (v.getTransitionName().equals("image_one")) {
						image = v;
					}
					Log.d(TAG, "onSharedElementStart: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
				if (container != null && text != null && image != null) {
					final float xDiff = image.getX() - text.getX();
					final float yDiff = image.getY() - text.getY();
					text.setX(container.getX());
					text.setY(container.getY());
					image.setX(container.getX() + xDiff);
					image.setY(container.getY() + yDiff);
				}
			}

			@Override
			public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
				super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
				View container = null;
				View text = null;
				View image = null;
				for (View v : sharedElements) {
					if (v.getTransitionName().equals("container_one")) {
						container = v;
					} else if (v.getTransitionName().equals("text_one")) {
						text = v;
					} else if (v.getTransitionName().equals("image_one")) {
						image = v;
					}
					Log.d(TAG, "onSharedElementEnd: " + v.getTransitionName() + ", x: " + v.getX() + ", y: " + v.getY());
				}
				if (container != null && text != null && image != null) {
					final float xDiff = image.getX() - text.getX();
					final float yDiff = image.getY() - text.getY();
					text.setX(container.getX());
					text.setY(container.getY());
					image.setX(container.getX() + xDiff);
					image.setY(container.getY() + yDiff);
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
				return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
			}
		});
	}
}
