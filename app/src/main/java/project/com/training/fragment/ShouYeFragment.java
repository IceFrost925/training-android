package project.com.training.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import project.com.training.R;
import project.com.training.adapter.HomePagerAdapter;
import project.com.training.adapter.ShouYeAdapter;


public class ShouYeFragment extends Fragment {
    private ListView listView;



    Unbinder unbinder;

    //private OnFragmentInteractionListener mListener;

    public ShouYeFragment() {
        // Required empty public constructor
    }

    public static ShouYeFragment newInstance(String param1, String param2) {
        ShouYeFragment fragment = new ShouYeFragment();
        return fragment;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye, container, false);

        List<Map<String, Object>> list = getData();
        listView=view.findViewById(R.id.list_item);
        listView.setAdapter(new ShouYeAdapter(getActivity(), list));
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.rainbow02);
            map.put("bookname", "书名："+i);
            map.put("price", "价格：" + i);

            list.add(map);
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
