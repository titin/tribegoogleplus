package testtribehired.titinkurniat.com.testtribehired;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonParse {

    public JsonParse() {
    }

    public List<AreaModel> getParseJsonWCF(String s) {
        List<AreaModel> ListData = new ArrayList<AreaModel>();
        try {
            URL js = new URL(Const.URL_JSON_ARRAY);
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            JSONArray jsonArray = new JSONArray(line);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject r = jsonArray.getJSONObject(i);
                ListData.add(new AreaModel(r.getString("area"), r.getString("city")));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return ListData;

    }

}
