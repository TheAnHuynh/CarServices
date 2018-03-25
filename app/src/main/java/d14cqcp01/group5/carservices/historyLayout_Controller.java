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

import java.util.ArrayList;
import java.util.List;

public class historyLayout_Controller extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<ticket_OfHistoryLayout_Model> arrTick = new ArrayList<ticket_OfHistoryLayout_Model>();
    ListView lvTick = null;
    arrayListItemAdapter_Model adapter = null;
    NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_Layout);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        scrollView = (NestedScrollView) findViewById(R.id.nestscrollview);
        scrollView.setFillViewport(true);
        ticket_OfHistoryLayout_Model tick = new ticket_OfHistoryLayout_Model("pt", "b", "Phương Trang", "Mạnh Hùng", 2,
                "Từ: Sài Gòn", "Đến: Hà Nội", "Giờ đi: 17:00:00", "Giờ đến: 17:00:00 18/02/2018",
                "Tổng chi phí: ", "đ 100.000", "Thanh Toán");
        arrTick.add(tick);
        arrTick.add(tick);
        adapter = new arrayListItemAdapter_Model(this, R.layout.item_TabPage_Layout, arrTick);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagechoxuli_OfHistoryLayout_Model Fragment = new pagechoxuli_OfHistoryLayout_Model();
        Fragment.setAdapter(adapter);
        pagerAdapter.addFragment(Fragment, "Chờ xử lí");
        pagerAdapter.addFragment(new pagechoxuli_OfHistoryLayout_Model(), "Đang sử dụng");
        pagerAdapter.addFragment(new pagechoxuli_OfHistoryLayout_Model(), "Đã thành công");
        pagerAdapter.addFragment(new pagechoxuli_OfHistoryLayout_Model(), "Đã hủy");
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