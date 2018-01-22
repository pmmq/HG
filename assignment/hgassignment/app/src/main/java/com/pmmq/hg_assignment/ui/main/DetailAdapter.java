package com.pmmq.hg_assignment.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.pmmq.hg_assignment.R;
import com.pmmq.hg_assignment.databinding.ItemHgDetailBinding;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {
	
	MainViewModel mMainViewModel;
	LayoutInflater inflater;
	private int lastPosition = -1;
	
	public DetailAdapter(final MainViewModel mainViewModel) {
		mMainViewModel = mainViewModel;
	}
	
	@Override
	public DetailHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		if (inflater == null) {
			inflater = LayoutInflater.from(parent.getContext());
		}
		ItemHgDetailBinding holderBinding = DataBindingUtil.inflate(inflater, viewType, parent, false);
		return new DetailHolder(holderBinding);
	}
	
	@Override
	public void onBindViewHolder(final DetailHolder holder, final int position) {
		DetailItemViewModel item = new DetailItemViewModel(holder.itemView.getContext(), mMainViewModel.getDetails()
				.getValue().get(position));
		holder.mViewBinding.setVmDetail(item);
		holder.mViewBinding.setPosition(position);
		setAnimation(holder.itemView, position);
	}
	
	@Override
	public int getItemCount() {
		return mMainViewModel.getDetails().getValue().size();
	}
	
	@Override
	public int getItemViewType(final int position) {
		return R.layout.item_hg_detail;
	}
	
	private void setAnimation(View viewToAnimate, int position) {
		if (position > lastPosition) {
			Animation animation = AnimationUtils
					.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
			viewToAnimate.startAnimation(animation);
			lastPosition = position;
		}
	}
	
	public class DetailHolder extends RecyclerView.ViewHolder {
		
		ItemHgDetailBinding mViewBinding;
		
		public DetailHolder(final ItemHgDetailBinding viewBinding) {
			super(viewBinding.getRoot());
			mViewBinding = viewBinding;
		}
	}
}
