package testtribehired.titinkurniat.com.testtribehired;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Titin Kurniati on 22-May-16.
 */
public class ListDoctorActivity extends Activity {
    private ListView listDoctor;
    private ImageButton imgButton;
    private TextView page;
    private DoctorInfo doctorInfo;
    private List<InfoModel> infoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_doktor);
        listDoctor = (ListView) findViewById(R.id.list);

        init();
    }

    public void init(){
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY_KEDUA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        infoModel = gson.fromJson(response.toString(),
                                new TypeToken<List<InfoModel>>() {
                                }.getType());

                        doctorInfo = new DoctorInfo(ListDoctorActivity.this,infoModel);
                        DoctorInfo.onItemClick listener = new DoctorInfo.onItemClick() {
                            @Override
                            public void itemClick(int position,String id) {
                                Intent intent = new Intent(ListDoctorActivity.this, DetailActivity.class);
                                intent.putExtra(Const.ID,id);
                                startActivity(intent);

                            }
                        };
                                doctorInfo.setClickListener(listener);
                                listDoctor.setAdapter(doctorInfo);

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(req);
    }

}
