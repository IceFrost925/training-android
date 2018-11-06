package cn.edu.android_weixin.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.android_weixin.R;
import cn.edu.android_weixin.adapter.MsgAdapter;


public class MsgFragment extends Fragment {

    private ListView listView;
    public static MsgFragment newInstance(String param1, String param2) {
        MsgFragment fragment = new MsgFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_msg , container, false);
        listView = view.findViewById(R.id.lv_msg);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new MsgAdapter(getActivity(), list));
        return view;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.touxiang);
            map.put("title", "标题"+i);
            map.put("info", "这是一个详细信息" + i);
            map.put("time","昨天");
            list.add(map);
        }
        return list;
    }
}
