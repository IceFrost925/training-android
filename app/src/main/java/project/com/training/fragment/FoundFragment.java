package project.com.training.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.android_weixin.R;

public class FoundFragment extends Fragment {


    public FoundFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FoundFragment newInstance(String param1, String param2) {
        FoundFragment fragment = new FoundFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.found_fragment, container, false);
    }





}
