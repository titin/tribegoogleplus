package testtribehired.titinkurniat.com.testtribehired;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Titin Kurniati on 22-May-16.
 */
public class DoctorInfo extends BaseAdapter {
    private List<InfoModel> infoModelList;
    private Context kontek;
    public onItemClick clickListener;

    public DoctorInfo(Context context, List<InfoModel> listData) {
        this.kontek = context;
        this.infoModelList = listData;
    }

    public void setClickListener(onItemClick clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return infoModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) kontek.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            ViewHolder viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.doctor_info, null);

            viewHolder.imgPerson = (ImageView) view.findViewById(R.id.img_person);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.address = (TextView) view.findViewById(R.id.alamat);
            viewHolder.speciality = (TextView) view.findViewById(R.id.spesial);
            viewHolder.rate = (TextView) view.findViewById(R.id.rate);

            Glide.with(kontek).load(infoModelList.get(position).getPhoto())
                    .placeholder(R.drawable.orang)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.orang).into(viewHolder.imgPerson);

            viewHolder.name.setText(infoModelList.get(position).getName());
            viewHolder.address.setText(infoModelList.get(position).getArea());
            viewHolder.speciality.setText(infoModelList.get(position).getSpeciality());
            int s = infoModelList.get(position).getRate();
            String t = String.valueOf(s);
            viewHolder.rate.setText(infoModelList.get(position).getCurrency() + " " + Util.getFormat(t));
            Log.wtf("", "id ===  " + infoModelList.get(position).getId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String i = String.valueOf(infoModelList.get(position).getId());
                    clickListener.itemClick(position, i);
                }
            });


        } else {
            view = convertView;
        }
        return view;

    }

    static class ViewHolder {
        ImageView imgPerson;
        TextView name;
        TextView address;
        TextView speciality;
        TextView rate;
    }

    public interface onItemClick {
        public void itemClick(int position, String id);
    }

}
