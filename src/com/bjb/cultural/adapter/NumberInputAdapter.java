package com.bjb.cultural.adapter;

import java.util.List;

import com.bjb.cultural.R;
import com.tencent.smtt.sdk.c;

import android.R.integer;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NumberInputAdapter extends RecyclerView.Adapter<NumberInputAdapter.ViewHolder>{
	
	private List<String> mData;
	private LayoutInflater inflater;
	private Context context;
	
	public NumberInputAdapter(Context context,List<String> mData){
		this.context=context;
		this.mData=mData;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.btNum.setText(mData.get(position));
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				listener.onItemClick(mData.get(position),position);
			}
		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view=inflater.inflate(R.layout.item_keypad, arg0,false);
		return new ViewHolder(view);
	}
	public interface OnItemCLickListener{
		void onItemClick(String str,int position);
	}
	private OnItemCLickListener listener;
	
	public void setOnItemClickListener(OnItemCLickListener listener){
		this.listener=listener;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder{
		Button btNum;
		public ViewHolder(View itemView) {
			super(itemView);
			btNum=(Button) itemView.findViewById(R.id.item_keypad);
		}
		
	}

}
