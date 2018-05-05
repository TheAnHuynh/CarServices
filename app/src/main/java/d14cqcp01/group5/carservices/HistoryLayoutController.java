package d14cqcp01.group5.carservices;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryLayoutController extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    String ticketLink = "TicketList";
    ArrayList<TicketOfHistoryLayoutModel> arrTick1 = new ArrayList<TicketOfHistoryLayoutModel>();
    ArrayList<TicketOfHistoryLayoutModel> arrTick2 = new ArrayList<TicketOfHistoryLayoutModel>();
    ArrayList<TicketOfHistoryLayoutModel> arrTick3 = new ArrayList<TicketOfHistoryLayoutModel>();
    ListView lvTick = null;
    ArrayListItemAdapterModel adapter1 = null;
    ArrayListItemAdapterModel adapter2 = null;
    ArrayListItemAdapterModel adapter3 = null;
    User user;
    String username = "U0";
    NestedScrollView scrollView;
    public DatabaseReference mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lich_su_dat_ve);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        scrollView = (NestedScrollView) findViewById(R.id.nestscrollview);
        scrollView.setFillViewport(true);
        mData = FirebaseDatabase.getInstance().getReference();

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrTick1.clear();
                arrTick2.clear();
                arrTick3.clear();
                DataSnapshot S = (dataSnapshot.child("UserList/"+ username +"/ticketList"));
                for (DataSnapshot item: S.getChildren()) {
                    TicketListModel T = dataSnapshot.child("TicketList/" + item.getValue(String.class)).getValue(TicketListModel.class);
                    CoachListModel C = dataSnapshot.child("CoachList/" + T.getIdCoach()).getValue(CoachListModel.class);
                    if (T.getStatus().equals("Chờ thanh toán")){
                        arrTick1.add(new TicketOfHistoryLayoutModel(C,T));
                    }else if (T.getStatus().equals("Đã thanh toán")){
                        arrTick2.add(new TicketOfHistoryLayoutModel(C,T));
                    }else if (T.getStatus().equals("Đã hoàn thành")){
                        arrTick3.add(new TicketOfHistoryLayoutModel(C,T));
                    }
                }
                onDataChange1();
                viewPager.refreshDrawableState();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    private void onDataChange1(){
        adapter1 = new ArrayListItemAdapterModel
                (this, R.layout.layout_tabpage_lich_su_dat_ve_item, arrTick1,
                        new ArrayListItemAdapterModel.OnListener(){

                            @Override
                            public void onCancel(int position, Activity context) {
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(context);
                                final int p = position;
                                builder.setTitle("Hủy chuyến xe")
                                        .setMessage("Bạn có chắc chắn muốn hủy chuyến xe này?")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                mData.child(ticketLink + "/" + arrTick1.get(p).Ticket.getId() + "/status").setValue("Đã Hủy");
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }

                            @Override
                            public void onPay(int position, Activity context) {
                                // Next Activity
                                Intent intent = new Intent(getBaseContext(), PaymentController.class);
                                String linkPayment = ticketLink+"/" + arrTick1.get(position).Ticket.getId() + "/status";
                                intent.putExtra("EXTRA_SESSION_ID",linkPayment);
                                startActivity(intent);
                            }

                            @Override
                            public void onRating(int position, Activity context) {

                            }

                        });
        adapter2 = new ArrayListItemAdapterModel
                (this, R.layout.layout_tabpage_lich_su_dat_ve_item, arrTick2,
                        null);

        adapter3 = new ArrayListItemAdapterModel
                (this, R.layout.layout_tabpage_lich_su_dat_ve_item, arrTick3,
                        new ArrayListItemAdapterModel.OnListener() {


                            @Override
                            public void onCancel(int position, Activity context) {

                            }

                            @Override
                            public void onPay(int position, Activity context) {

                            }

                            @Override
                            public void onRating(int position, Activity context) {
                                //Next view Rating

                            }
                        });

        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        setupTabIcons();
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PageChoXuLiOfHistoryLayoutModel Fragment1 = new PageChoXuLiOfHistoryLayoutModel();
        PageChoXuLiOfHistoryLayoutModel Fragment2 = new PageChoXuLiOfHistoryLayoutModel();
        PageChoXuLiOfHistoryLayoutModel Fragment3 = new PageChoXuLiOfHistoryLayoutModel();
        Fragment1.setAdapter(adapter1);
        Fragment2.setAdapter(adapter2);
        Fragment3.setAdapter(adapter3);
        pagerAdapter.addFragment(Fragment1, "Chờ xử lí");
        pagerAdapter.addFragment(Fragment2, "Đã thanh toán");
        pagerAdapter.addFragment(Fragment3, "Đã thành công");
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);
//        pagerAdapter.getItem(0).getActivity().getTitle()
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