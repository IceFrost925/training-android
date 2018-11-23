package project.com.training.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.com.training.R;
import project.com.training.adapter.ShouCangAdapter;
import project.com.training.adapter.ShouCangCursorApdater;
import project.com.training.adapter.ShouYeAdapter;
import project.com.training.adapter.ShouYeCursorApdater;
import project.com.training.dao.BooksDao;
import project.com.training.dao.CollectDao;
import project.com.training.dao.ShoppingDao;
import project.com.training.model.Shopping;
import project.com.training.model.User;


public class ShouCangFragment extends Fragment implements ShouCangAdapter.MyClickListener {


    Unbinder unbinder;
    User user=new User();
    public ShouCangFragment(){}
    @SuppressLint("ValidFragment")
    public ShouCangFragment(User user) {
        this.user=user;
        Log.d("ss",user.toString());
        // Required empty public constructor
    }
   /* public User setUser(User user) {
       //user.setId(user.getId());
        this.user=user;
       return user;

    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_shou_cang, container, false);
        //List<Map<String, Object>> list = getData();

        View view = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            view = this.getLayoutInflater().inflate((R.layout.fragment_shou_cang), null);
        }
        ListView shoucangItem = view.findViewById(R.id.shoucang_item);

        CollectDao collectDao=new CollectDao(getActivity());
        List<Map<String,String>> list=collectDao.findShouCangList();
        // LayoutInflater layoutInflater = getLayoutInflater();
        if(list!=null){
            //listView.setAdapter(new ShouYeAdapter(view.getContext(),layoutInflater, list));
            shoucangItem.setAdapter(new ShouCangAdapter(getActivity(), list,ShouCangFragment.this));
        }

        //shoucangItem.setAdapter(new ShouCangAdapter(getActivity(), list));

        /*CollectDao collectDao=new CollectDao(getActivity());
        Cursor cursor=collectDao.findShouCang();
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            //adapter = new StudentCursorApdater(this, cursor);
            ShouCangCursorApdater shouCangCursorApdater=new ShouCangCursorApdater(getActivity(),cursor);
            shoucangItem.setAdapter(shouCangCursorApdater);
            Log.d("cc",cursor.toString());
        }*/

       /* if(user.getId()!=null){
            Log.d("iidd", String.valueOf(user.getId()));
            Cursor cursor=collectDao.findMyShouCang(user.getId().intValue());
            if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
                //adapter = new StudentCursorApdater(this, cursor);
                ShouCangCursorApdater shouCangCursorApdater=new ShouCangCursorApdater(getActivity(),cursor);
                shoucangItem.setAdapter(shouCangCursorApdater);
                Log.d("cc",cursor.toString());
            }
        }*/





        unbinder = ButterKnife.bind(this, view);
        setListViewHeightBasedOnChildren(shoucangItem);

        shoucangItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }






   /* public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
// 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
// listView.getDividerHeight()获取子项间分隔符占用的高度
// params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }*/




  /*  public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("picture", R.drawable.book);
            map.put("name", "书名：" + i);
            map.put("price", "价格：" + i);
            map.put("count", "库存" + i);
            map.put("jhi_number", "编号" + i);

            list.add(map);
        }
        return list;
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void clickListener(View v) {
        CollectDao collectDao=new CollectDao(getActivity());
        List<Map<String,String>> list=collectDao.findShouCangList();
        switch (v.getId()){
            case R.id.add_gw:
                Shopping shopping = new Shopping();
                ShoppingDao shoppingDao = new ShoppingDao(getActivity());
                List<Map<String,String>> list1=shoppingDao.findMyGouWuCheList();

                if(shoppingDao.ifShoppingExist(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")))){
                    shopping.setNumber(String.valueOf(Integer.parseInt(list1.get((Integer) v.getTag()).get("number"))+1));
                    //shopping.setNumber(String.valueOf(Integer.parseInt(list1.get((Integer) v.getTag()).get("number"))+1));
                    shopping.setBook_id_id(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")));
                    shoppingDao.updateMyShopping(shopping);
                    Toast.makeText(getActivity(),"该物品您已加入购物车!",Toast.LENGTH_SHORT).show();
                }else{
                    shopping.setBook_id_id(Integer.parseInt(list.get((Integer)v.getTag()).get("bookid")));
                    shopping.setSuser_id(2);
                    shopping.setNumber("1");

                    if(shoppingDao.insert(shopping)){
                        // shoppingDao.updateMyShopping(shopping);
                        Toast.makeText(getActivity(),"加入购物车成功！",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"加入购物车失败！",Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.del:
                collectDao.delete(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")));
                Toast.makeText(getActivity(),"移除成功！",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
