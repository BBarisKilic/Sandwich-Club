package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    private static final String Name = "name";
    private static final String mainName = "mainName";
    private static final String alsoKnownAs = "alsoKnownAs";
    private static final String placeOfOrigin = "placeOfOrigin";
    private static final String description = "description";
    private static final String image = "image";
    private static final String ingredients = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        String main_name="";
        List<String> also_known_as= null;
        String place_Of_Origin="";
        String desc="";
        String img="";
        List<String> ingredient = null;

        try{

            JSONObject yeniObje = new JSONObject(json);

            if (yeniObje.has(Name)){
                JSONObject name= yeniObje.getJSONObject(Name);
                main_name=name.optString(mainName,null);

                if (name.has(alsoKnownAs) && !name.isNull(alsoKnownAs)){
                    JSONArray jArray = name.getJSONArray(alsoKnownAs);
                    also_known_as= getJsonArray(jArray);
                }
            }

            if (yeniObje.has(placeOfOrigin)){
                place_Of_Origin=yeniObje.optString(placeOfOrigin,null);
            }

            if (yeniObje.has(description)){
                desc=yeniObje.optString(description,null);
            }

            if (yeniObje.has(image)){
                img=yeniObje.optString(image,null);
            }

            if (yeniObje.has(ingredients) && !yeniObje.isNull(ingredients)){
                JSONArray jArr = yeniObje.getJSONArray(ingredients);
                ingredient = getJsonArray(jArr);
            }
        }

        catch (JSONException e){
            Log.w(TAG, "parseSandwichJson: ERROR" + e.getMessage() );
            return null;
        }

        return new Sandwich(main_name, also_known_as, place_Of_Origin, desc, img, ingredient);
    }

    private static List<String> getJsonArray(JSONArray jArray) throws JSONException {

        List<String> jsonArray = new ArrayList<>();
        if (jsonArray!=null) {
            for (int array_lim = 0; array_lim < jArray.length(); array_lim++) {
                jsonArray.add(jArray.optString(array_lim, null));
            }
        }
        return jsonArray;
    }
}



