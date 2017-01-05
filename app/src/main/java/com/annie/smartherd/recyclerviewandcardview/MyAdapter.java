package com.annie.smartherd.recyclerviewandcardview;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

	private List<NatureModel> objectList;
	private LayoutInflater inflater;

	public MyAdapter(Context context, List<NatureModel> objectList) {
		inflater = LayoutInflater.from(context);
		this.objectList = objectList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public int getItemCount() {
		return objectList.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		NatureModel current = objectList.get(position);
		holder.setData(current, position);
		holder.setListeners();
	}

	class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private TextView title;
		private ImageView imgThumb, imgDelete, imgCopy;
		private int position;
		private NatureModel currentObject;

		public MyViewHolder(View itemView) {
			super(itemView);
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_thumb);
			imgDelete   = (ImageView) itemView.findViewById(R.id.img_delete);
			imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);
		}

		public void setData(NatureModel currentObject, int position) {
			this.title.setText(currentObject.getTitle());
			this.imgThumb.setImageResource(currentObject.getImageID());
			this.position = position;
			this.currentObject = currentObject;
		}

		public void setListeners() {
			imgDelete.setOnClickListener(MyViewHolder.this);
			imgCopy.setOnClickListener(MyViewHolder.this);
			imgThumb.setOnClickListener(MyViewHolder.this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.img_delete:
					removeItem(position);
					break;

				case R.id.img_copy:
					addItem(position, currentObject);
					break;
			}
		}
	}

	public void removeItem(int position) {
		objectList.remove(position);
		notifyItemRemoved(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}

	public void addItem(int position, NatureModel currentObject) {
		objectList.add(position, currentObject);
		notifyItemInserted(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}
}