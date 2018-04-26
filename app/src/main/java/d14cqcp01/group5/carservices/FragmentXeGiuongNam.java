package d14cqcp01.group5.carservices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class FragmentXeGiuongNam extends Fragment {

    FragmentTabHost tabHost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_giuong_nam,container,false);


        return viewSoDoGheXe;
    }
}
