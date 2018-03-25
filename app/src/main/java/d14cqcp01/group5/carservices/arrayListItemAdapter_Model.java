package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by T420 on 3/15/2018.
 */

public class arrayListItemAdapter_Model extends ArrayAdapter<ticket_OfHistoryLayout_Model>
{
    Activity context=null;
    ArrayList<ticket_OfHistoryLayout_Model> myArray=null;
    int layoutId;
    public arrayListItemAdapter_Model(Activity context,
                                      int layoutId,
                                      ArrayList<ticket_OfHistoryLayout_Model>arr) {
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArray.size()>0 && position>=0)
        {
            final ImageView i=(ImageView)
                    convertView.findViewById(R.id.iconBrand);
            final TextView b = (TextView)
                    convertView.findViewById(R.id.brand);
            final TextView r = (TextView)
                    convertView.findViewById(R.id.rating);
            final TextView f = (TextView)
                    convertView.findViewById(R.id.from);
            final TextView t = (TextView)
                    convertView.findViewById(R.id.to);
            final TextView s = (TextView)
                    convertView.findViewById(R.id.start);
            final TextView e = (TextView)
                    convertView.findViewById(R.id.end);
            final TextView m1 = (TextView)
                    convertView.findViewById(R.id.moreinfo);
            final TextView m2 = (TextView)
                    convertView.findViewById(R.id.moreinfosupport);
            final TextView btn = (Button)
                    convertView.findViewById(R.id.button);

            final ticket_OfHistoryLayout_Model tick = myArray.get(position);

            b.setText(tick.getBrand());
            r.setText(Float.toString(tick.getRating()) + "/5.0");
            f.setText(tick.getFrom());
            t.setText(tick.getTo());
            s.setText(tick.getTimeStart());
            e.setText(tick.getTimeEnd());
            m1.setText(tick.getMoreinfo());
            m2.setText(tick.getMoreinfosupport());
            btn.setText(tick.getButtonInfo());
        }
        return convertView;
    }
}
