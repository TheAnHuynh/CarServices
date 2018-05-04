package d14cqcp01.group5.carservices;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class historyLayout_Controller extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    User user;
    String username = "U0";
    ArrayList<ticket_OfHistoryLayout_Model> arrTick1 = new ArrayList<ticket_OfHistoryLayout_Model>();
    ArrayList<ticket_OfHistoryLayout_Model> arrTick2 = new ArrayList<ticket_OfHistoryLayout_Model>();
    ArrayList<ticket_OfHistoryLayout_Model> arrTick3 = new ArrayList<ticket_OfHistoryLayout_Model>();
    ListView lvTick = null;
    arrayListItemAdapter_Model adapter1 = null;
    arrayListItemAdapter_Model adapter2 = null;
    arrayListItemAdapter_Model adapter3 = null;
    NestedScrollView scrollView;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        scrollView = (NestedScrollView) findViewById(R.id.nestscrollview);
        scrollView.setFillViewport(true);

        adapter1 = new arrayListItemAdapter_Model(this, R.layout.item_listofhistory_layout, arrTick1);
        adapter2 = new arrayListItemAdapter_Model(this, R.layout.item_listofhistory_layout, arrTick2);
        adapter3 = new arrayListItemAdapter_Model(this, R.layout.item_listofhistory_layout, arrTick3);
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.child("UserList/" + username).getValue(User.class);
                DataSnapshot S = dataSnapshot.child("UserList/"+ username + "/ticketList");
                for (DataSnapshot item: S.getChildren()) {
                    TicketListModel T = dataSnapshot.child("TicketList/" + item.getValue(String.class)).getValue(TicketListModel.class);
                    CoachListModel C = dataSnapshot.child("CoachList/" + T.getIdCoach()).getValue(CoachListModel.class);
                    if (T.getStatus() == "Chờ thanh toán"){
                        arrTick1.add(new ticket_OfHistoryLayout_Model(C,T));
                    }else if(T.getStatus() == "Đã thanh toán"){
                        arrTick2.add(new ticket_OfHistoryLayout_Model(C,T));
                    }else if(T.getStatus() == "Đã hoàn thành"){
                        arrTick3.add(new ticket_OfHistoryLayout_Model(C,T));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagechoxuli_OfHistoryLayout_Model Fragment1 = new pagechoxuli_OfHistoryLayout_Model();
        pagechoxuli_OfHistoryLayout_Model Fragment2 = new pagechoxuli_OfHistoryLayout_Model();
        pagechoxuli_OfHistoryLayout_Model Fragment3 = new pagechoxuli_OfHistoryLayout_Model();
        Fragment1.setAdapter(adapter1);
        Fragment1.setAdapter(adapter2);
        Fragment1.setAdapter(adapter3);
        pagerAdapter.addFragment(Fragment1, "Chờ xử lí");
        pagerAdapter.addFragment(Fragment2, "Đang sử dụng");
        pagerAdapter.addFragment(Fragment3, "Đã thành công");
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);
    }

    private void setupTabIcons() {
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}