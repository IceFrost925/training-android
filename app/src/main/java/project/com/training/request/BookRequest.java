package project.com.training.request;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import project.com.training.model.User;

public class BookRequest {
    private Context context;
    //private Now now;
    //private List<Futures> list;
    public BookRequest(Context context) {
        this.context = context;
        //this.now = new Now();
        //this.list = new ArrayList<>();
    }
    public User Login(String url, String userParames, String userParames2)throws JSONException {
        OkHttpClient client = new OkHttpClient();//创建请求,Builder是辅助类，辅助进行网络请求的
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.addEncoded("username", userParames);
        formBody.addEncoded("password", userParames2);
        Request request = new Request.Builder().url(url).post(formBody.build()).build();//创建请求队列,将请求放进去
        Call call = client.newCall(request);

        User user = new User();
        try {
            Response response = call.execute();
            String result = response.body().string();
            JSONObject jsonObject = new JSONObject(result);
            Gson gson = new Gson();
            String gg = jsonObject.getJSONObject("data").toString();
            user = gson.fromJson(gg, User.class);
            Log.d("jj",user.toString());
            Log.d("S",gg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("SSS",user.toString());

        return user;
    }
//
    /*public void getNow(String url, String cityParames) throws JSONException {
        OkHttpClient client = new OkHttpClient();//创建请求,Builder是辅助类，辅助进行网络请求的
        //请求当天的天气情况

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.addEncoded("location", cityParames);

        Request request = new Request.Builder().url(url).post(formBody.build()).build();//创建请求队列,将请求放进去
        Log.d("SYS", formBody.toString());
        Call call = client.newCall(request);

        //请求当天PM2.5
        Request request2 = new Request.Builder().url("https://free-api.heweather.com/s6/air/now").post(formBody.build()).build();//创建请求队列,将请求放进去
        Call call2 = client.newCall(request2);
        try {
            Response response = call.execute();
            Response response2 = call2.execute();
            String result = response.body().string();
            String result2 = response2.body().string();
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject2 = new JSONObject(result2);
            Gson gson = new Gson();
            String time = jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONObject("update").getString("loc");
            String tmp_min = jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONArray("daily_forecast").getJSONObject(0).getString("tmp_min");
            String tmp_max = jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONArray("daily_forecast").getJSONObject(0).getString("tmp_max");
            String cond_status ="status" + jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONObject("now").getString("cond_code");
            String air_day_pm25 = jsonObject2.getJSONArray("HeWeather6").getJSONObject(0).getJSONObject("air_now_city").getString("pm25");
            String weather_day = jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONObject("now")
                    .put("updateTime", time)
                    .put("tmp_min", tmp_min)
                    .put("tmp_max", tmp_max)
                    .put("cond_status", cond_status)
                    .put("PM25", air_day_pm25)
                    .toString();
            Log.d("SSS",weather_day);
           // now = gson.fromJson(weather_day, Now.class);
           // Log.d("SSSSS",gson.fromJson(weather_day, Now.class).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("SSSSS",now.toString());

    }

    /*public List<Futures> getFuuresWeather(String url, String cityParames){

        OkHttpClient client = new OkHttpClient();//创建请求,Builder是辅助类，辅助进行网络请求的
        //请求当天的天气情况
        String key = "ffdba3eb98cc4f89ad6eec2a9b7b5a0a";
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.addEncoded("location", cityParames);
        formBody.add("key", key);
        Request request = new Request.Builder().url(url).post(formBody.build()).build();//创建请求队列,将请求放进去
        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String result = response.body().string();
            JSONObject jsonObject = new JSONObject(result);
            Gson gson = new Gson();
            String weather_future = jsonObject.getJSONArray("HeWeather6").getJSONObject(0).getJSONArray("daily_forecast").toString();
            Type listType = new TypeToken<List<Futures>>(){}.getType();
        //    list = gson.fromJson(weather_future, listType);
            Log.d("XXXX",weather_future);
        /*    Type listType = new TypeToken<List>(){}.getType();
            List<Map> list = gson.fromJson(weather_future, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }*/

}
