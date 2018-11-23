package project.com.training.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.com.training.R;
import project.com.training.adapter.ShouYeAdapter;
import project.com.training.adapter.ShouYeCursorApdater;
import project.com.training.adapter.WenXueAdapter;
import project.com.training.dao.BooksDao;
import project.com.training.dao.CollectDao;
import project.com.training.dao.ShoppingDao;
import project.com.training.model.Collect;
import project.com.training.model.Shopping;


public class WenXueFragment extends Fragment implements ShouYeAdapter.MyClickListener {
    private ListView listView;
    Unbinder unbinder;

    //private OnFragmentInteractionListener mListener;

    public WenXueFragment() {
        // Required empty public constructor
    }

    public static WenXueFragment newInstance(String param1, String param2) {
        WenXueFragment fragment = new WenXueFragment();
        return fragment;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wen_xue, container, false);
        listView=view.findViewById(R.id.list_item);
        //List<Map<String, Object>> list = getData();
       // listView.setAdapter(new WenXueAdapter(getActivity(), list));
        BooksDao booksDao=new BooksDao(getActivity());
        List<Map<String,String>> list=booksDao.findbooksByType("文学");
        //LayoutInflater layoutInflater = getLayoutInflater();
        if(list!=null){
           listView.setAdapter(new ShouYeAdapter(getActivity(), list,WenXueFragment.this));
            //listView.setAdapter(new ShouYeAdapter(view.getContext(),layoutInflater, list));

        }
       /* BooksDao booksDao=new BooksDao(getActivity());
        Cursor cursor=booksDao.findbooksByType("文学");
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            //adapter = new StudentCursorApdater(this, cursor);
            ShouYeCursorApdater shouYeCursorApdater=new ShouYeCursorApdater(getActivity(),cursor);
            listView.setAdapter(shouYeCursorApdater);
        }*/
        setListViewHeightBasedOnChildren(listView);
        unbinder = ButterKnife.bind(this, view);
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
    /*public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("picture", R.drawable.book);
            map.put("name", "书名："+i);
            map.put("price", "价格：" +i);

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
        BooksDao booksDao=new BooksDao(getActivity());
        List<Map<String,String>> list=booksDao.findAllbooksList();
        switch (v.getId()){
            case R.id.btn_shopCar:
                Shopping shopping = new Shopping();
                ShoppingDao shoppingDao = new ShoppingDao(getActivity());
                List<Map<String,String>> list1=shoppingDao.findMyGouWuCheList();

                if(shoppingDao.ifShoppingExist(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")))){
                    shopping.setNumber(String.valueOf(Integer.parseInt(list1.get((Integer) v.getTag()).get("number"))+1));
                    //shopping.setNumber(String.valueOf(Integer.parseInt(list1.get((Integer) v.getTag()).get("number"))+1));
                    shopping.setBook_id_id(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")));
                    shoppingDao.updateMyShopping(shopping);
                    Toast.makeText(getActivity(),"该物品已在购物车!",Toast.LENGTH_SHORT).show();
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
            case R.id.btn_recieve:

                Collect collect=new Collect();
                CollectDao collectDao=new CollectDao(getActivity());
                Log.d("hhhhhhhhhh", String.valueOf(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid"))));
                if(collectDao.ifCollectExist(Integer.parseInt(list.get((Integer) v.getTag()).get("bookid")))){
                    Toast.makeText(getActivity(),"该物品您已收藏!",Toast.LENGTH_SHORT).show();
                }else{
                    collect.setBook_id(Integer.parseInt(list.get((Integer)v.getTag()).get("bookid")));
                    collect.setSuser_id(2);
                    collectDao.insert(collect);
                    Toast.makeText(getActivity(),"收藏成功!",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


}
