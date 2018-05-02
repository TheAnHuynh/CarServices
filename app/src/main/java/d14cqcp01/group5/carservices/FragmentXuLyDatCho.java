package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.Toast;

public class FragmentXuLyDatCho extends Fragment {

    private String carId;
    private String type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("myCar", Context.MODE_PRIVATE);
        carId = preferences.getString("ID", "");
        type = preferences.getString("TYPE","");
        View viewSoDoGheXe;
        switch(type){
            case XeKhach.XE_16_CHO:{
                viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_16_cho,container,false);
                break;
            }
            case XeKhach.XE_24_CHO:{
                viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_24_cho,container,false);
                break;
            }
            case XeKhach.XE_GIUONG_NAM:{
                viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_giuong_nam,container,false);
            }
        }
        return null;
    }

    public void xuLyChonGheNgoi(View v){
        Button seat = (Button) v;
        Toast.makeText(getContext(),seat.getText(),Toast.LENGTH_SHORT).show();
    }

}
