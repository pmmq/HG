package com.pmmq.hg_assignment.data;

import android.content.SharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmmq.hg_assignment.data.entity.Detail;

public class ApplicationData {
	
	public final String DETAILS_KEY = "DETAILS_KEY";
	private SharedPreferences mDetailsPref;
	private List<Detail> mDetails;
	private Type detailListType;
	public ApplicationData(SharedPreferences sharedPreferences) {
		mDetailsPref = sharedPreferences;
		if(mDetailsPref != null){
			Type detailListType = new TypeToken<ArrayList<Detail>>() {}.getType();
			mDetails = new Gson().fromJson(mDetailsPref.getString(DETAILS_KEY, ""), detailListType);
		}
	}
	
	public List<Detail> getDetails() {
		if (mDetails == null) {
			mDetails = new LinkedList<>();
		}
		return mDetails;
	}
	
	public void setDetails(final List<Detail> details) {
		if (mDetailsPref != null) {
			mDetailsPref.edit().putString(DETAILS_KEY, new Gson().toJson(details)).apply();
		}
		mDetails = details;
	}
	
	public void addDetail(Detail detail){
		getDetails().add(detail);
		setDetails(mDetails);
	}
	
}
