package com.bjb.cultural.view;

import java.util.ArrayList;
import java.util.List;

import com.bjb.cultural.R;
import com.bjb.cultural.adapter.NumberInputAdapter;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class ExitDialogFragment extends DialogFragment implements View.OnClickListener{
	
	private RecyclerView recyclerView;
	private Button btCancel,btConfirm;
	private PasswordInputView passwordView;
	private NumberInputAdapter adapter;
	private StringBuilder builder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		 View view=inflater.inflate(R.layout.dialog_exit, container);
		 initView(view);
		 initData();
		 initEvent();
		return view;
	}

	private void initEvent() {
		adapter.setOnItemClickListener(new NumberInputAdapter.OnItemCLickListener() {
			
			@Override
			public void onItemClick(String str,int position) {
				if (position==9) {
					builder.delete(0, builder.length());
					passwordView.setText("");
					return;
				}
				if (position==11) {
					if (builder.length()>0) {
						builder.deleteCharAt(builder.length()-1);
						passwordView.setText(builder.toString());
					}
					return;
				}
				builder.append(str);
				passwordView.setText(builder.toString());
			}
		});
	}

	private void initData() {
		builder=new StringBuilder();
		adapter=new NumberInputAdapter(getActivity(), getList());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
		
	}
	public List<String> getList(){
		List<String> strings=new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i==9) {
				strings.add("清空");
				continue;
			}
			if (i==10) {
				strings.add("0");
				continue;
			}
			if (i==11) {
				strings.add("X");
				continue;
			}
			strings.add((i+1)+"");
			
		}
		return strings;
	}

	private void initView(View view) {
		recyclerView=(RecyclerView) view.findViewById(R.id.dialog_exit_rv);
		btCancel=(Button) view.findViewById(R.id.dialog_exit_cancel);
		btConfirm=(Button) view.findViewById(R.id.dialog_exit_confirm);
		passwordView=(PasswordInputView) view.findViewById(R.id.dialog_exit_pass);
		btCancel.setOnClickListener(this);
		btConfirm.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_exit_cancel:
			dismiss();
			break;
		case R.id.dialog_exit_confirm:
			String password=passwordView.getText().toString();
			listener.onConfirm(password);
			dismiss();
			break;
		default:
			break;
		}
	}
	public interface OnConfirmClickListener{
		void onConfirm(String pass);
	}
	private OnConfirmClickListener listener;
	
	public void setOnConfirmClickListener(OnConfirmClickListener listener){
		this.listener=listener;
	}

	
	

}
