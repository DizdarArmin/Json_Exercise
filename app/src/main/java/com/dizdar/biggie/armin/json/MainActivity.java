package com.dizdar.biggie.armin.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// Author : DizdarArmin

public class MainActivity extends AppCompatActivity {

    JSONObject colorObject;
    JSONArray colorArray;
    TextView toDisplay;
    final String CHECK = "255";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDisplay = (TextView) findViewById(R.id.toDisplay);
        colors();

    }


    public void colors() {
        String colorJsonDocument = "{" +
                "       \"colors\":" + "[" +
                "{" +
                " \"color\" :   \"black\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,255,255,1]," +
                " \"hex\" : \"#000\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" : \"white´\"," +
                " \"category\" : \"value\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,0,0,1]," +
                " \"hex\" : \"#FFF\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"red\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,0,0,1]," +
                " \"hex\" : \"#FF0\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"blue\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,0,255,1]," +
                " \"hex\" : \"#00F\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"yellow\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,255,0,1]," +
                " \"hex\" : \"#FF0\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"green\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"secondary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,255,0,1]," +
                " \"hex\" : \"#0F0\"" +
                "}" +
                "}" +
                "]" +
                "}";
        try {
            colorObject = (JSONObject) new JSONTokener(colorJsonDocument).nextValue();

            colorArray = colorObject.getJSONArray("colors");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void count(View view) throws JSONException {

        int count = 0;

        for (int i = 0; i < colorArray.length(); i++) {

            JSONObject color = colorArray.getJSONObject(i);
            JSONObject rgbaCode = color.getJSONObject("code");

            String rgba = rgbaCode.getString("rgba");

            String[] numbers = rgba.split(",");

            String green = numbers[1];

            if (green.equals(CHECK))
                count++;
        }

        String text =("Number of colors containing"+ "\n" +"green component is : " + count);
        toDisplay.setText(text);

    } //End of count method.




    public void list (View view) throws JSONException {
        String colors = "";

        for (int i = 0; i < colorArray.length(); i++) {
            JSONObject color = colorArray.getJSONObject(i);

            JSONObject rgbaCode = color.getJSONObject("code");

            String rgba = rgbaCode.getString("rgba");

            String[] numbers = rgba.split(",");

            String green = numbers[1];

            if (green.equals(CHECK)) {
                colors = colors + color.getString("color") + ", ";
            }
        }

        String text =("Colors that contain green component are :"+ "\n"  + colors );

        toDisplay.setText(text);

    } //End of list method.




    public void modify (View view) throws JSONException {

        JSONObject orange = new JSONObject();
        orange.put("color", "orange");
        orange.put("category", "hue");
        orange.put("type", "secondary");

        JSONObject code = new JSONObject();
        code.put("rgba", "[255,165,0,1]");
        code.put("hex", "#FA0");

        orange.put("code", code);


        if (!(colorArray.toString().contains(orange.toString()))) {
            colorArray.put(orange);
        }

        toDisplay.setText(colorArray.toString());
    } // End of modify method.




} // End of main activity.

