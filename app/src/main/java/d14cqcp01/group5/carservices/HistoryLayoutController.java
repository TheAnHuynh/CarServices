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

public class HistoryLayoutController extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<TicketOfHistoryLayoutModel> arrTick = new ArrayList<TicketOfHistoryLayoutModel>();
    ListView lvTick = null;
    ArrayListItemAdapterModel adapter = null;
    NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        scrollView = (NestedScrollView) findViewById(R.id.nestscrollview);
        scrollView.setFillViewport(true);
        TicketOfHistoryLayoutModel tick = new TicketOfHistoryLayoutModel("pt", "b", "Phương Trang", "Mạnh Hùng", 2,
                "Từ: Sài Gòn", "Đến: Hà Nội", "Giờ đi: 17:00:00", "Giờ đến: 17:00:00 18/02/2018",
                "Tổng chi phí: ", "đ 100.000", "Thanh Toán");
        arrTick.add(tick);
        arrTick.add(tick);
        adapter = new ArrayListItemAdapterModel(this, R.layout.item_tabpage_layout, arrTick);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PageChoXuLiOfHistoryLayoutModel Fragment = new PageChoXuLiOfHistoryLayoutModel();
        Fragment.setAdapter(adapter);
        pagerAdapter.addFragment(Fragment, "Chờ xử lí");
        pagerAdapter.addFragment(new PageChoXuLiOfHistoryLayoutModel(), "Đang sử dụng");
        pagerAdapter.addFragment(new PageChoXuLiOfHistoryLayoutModel(), "Đã thành công");
        pagerAdapter.addFragment(new PageChoXuLiOfHistoryLayoutModel(), "Đã hủy");
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