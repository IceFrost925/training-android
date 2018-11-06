package project.com.training.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.com.training.R;
import project.com.training.adapter.ShouYeAdapter;


public class ShouYeFragment extends Fragment {

    private ListView listView;
    public static ShouYeFragment newInstance(String param1, String param2) {
        ShouYeFragment fragment = new ShouYeFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.shouye , container, false);
        listView = view.findViewById(R.id.list_item);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new ShouYeAdapter(getActivity(), list));
        return view;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.ico_user);
            map.put("bookname", "标题"+i);
            map.put("info", "这是一个详细信息" + i);
           // map.put("time","昨天");
            list.add(map);
        }
        return list;
    }
}
