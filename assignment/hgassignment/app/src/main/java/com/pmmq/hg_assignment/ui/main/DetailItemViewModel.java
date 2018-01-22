package com.pmmq.hg_assignment.ui.main;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import java.text.SimpleDateFormat;
import com.pmmq.hg_assignment.R;
import com.pmmq.hg_assignment.data.entity.Detail;

public class DetailItemViewModel extends ViewModel{
	
	private Context mContext;
	private Detail mDetail;
	SimpleDateFormat dateFormat;
	
	public DetailItemViewModel(Context context,final Detail detail) {
		mDetail = detail;
		mContext = context;
	}
	
	public String getBuyWithFormat(){
		return mContext.getResources().getString(R.string.hg_buy,mDetail.getBuy());
	}
	
	public String getSellWithFormat(){
		return mContext.getResources().getString(R.string.hg_sell,mDetail.getSell());
	}
	
	public String getDateWithFormat(){
		if(dateFormat == null){
			dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
		}
		return mContext.getResources().getString(R.string.hg_date,dateFormat.format(mDetail.getTimeStamp()));
	}
	
	
}
