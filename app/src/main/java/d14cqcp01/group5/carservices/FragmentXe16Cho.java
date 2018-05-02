package d14cqcp01.group5.carservices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentXe16Cho extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSoDoGheXe = inflater.inflate(R.layout.layout_ghe_xe_16_cho,container,false);
        return viewSoDoGheXe;
    }

    public void xuLyChonGheNgoi(View v){

    }
}
