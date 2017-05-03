package id.buruhsd.nicepay;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import id.buruhsd.nicepay.Model.NicePay;


public class MainActivity extends AppCompatActivity {

    public static final String iMid = "BMRITEST01";
    public static final String encodeKey = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A==";
    public static String url = "https://www.nicepay.co.id/nicepay/api/onePassToken.do";
    EditText editText, editText2, editText3, editText4;
    Button button2;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    WebView bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                input();
                string();
            }
        });

    }

//    public void input() {
//        String name = editText.getText().toString();
//        String card = editText2.getText().toString();
//        String cvv = editText3.getText().toString();
//        String yymm = editText4.getText().toString();
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
////        Map<String, String> jsonParams = new HashMap<String, String>();
////        jsonParams.put("merchantToken", encodeKey);
////        jsonParams.put("cardExpYymm",yymm);
////        jsonParams.put("cardNo",card);
////        jsonParams.put("amt", "10");
////        jsonParams.put("referenceNo","20170429092009");
////        jsonParams.put("iMID", iMid);
//
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST, url + "?jsonData={\"iMid\":\"BMRITEST01\",\"referenceNo\":\""+card+"\",\"amt\":\""+ , null, new Response.Listener<JSONObject>() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onResponse(JSONObject response) {
//
////                Log.d("coba", String.valueOf(response));
//
//                try {
//                    JSONArray json = new JSONArray(response);
////                    Log.d("save", json.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Tag", "Error" + error.getMessage());
//                Toast.makeText(MainActivity.this, "Failed connect to server", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                return headers;
//            }
//
////            @Override
////            public String getBodyContentType() {
////                return "application/json";
////            }
//        };
//        queue.add(jsonObjectRequest);
//    }


    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public void string() {

        String name = editText.getText().toString();
        String card = editText2.getText().toString();
        String cvv = editText3.getText().toString();
        String yymm = editText4.getText().toString();
        String referenceNo = "12345678";
        String amt = "10000";

        String merchantToken =iMid+referenceNo+amt+encodeKey;
        MessageDigest digest=null;
        String hash;
        hash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(merchantToken.getBytes());

            hash = bytesToHexString(digest.digest());

            Log.i("Eamorr", "result is " + hash);
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

//        String sementara = "?jsonData={\"iMid\":\""+iMid+"\",\"referenceNo\":\""+referenceNo+"\",\"amt\":\""+amt+"\",\"cardNo\":\""+card+"\",\"cardExpYymm\":\""+yymm+"\",\"merchantToken\":\""+hash+"}";

//        Log.d("Sementara", sementara);


//        String merchantToken = sha256();

        RequestQueue queue = Volley.newRequestQueue(this);
//
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("merchantToken", encodeKey);
        jsonParams.put("cardExpYymm",yymm);
        jsonParams.put("cardNo",card);
        jsonParams.put("amt", "10");
        jsonParams.put("referenceNo","20170429092009");
        jsonParams.put("iMID", iMid);

        StringRequest URL = new StringRequest(Request.Method.GET, url + "?jsonData={\"iMid\":\""+iMid+"\",\"referenceNo\":\""+referenceNo+"\",\"amt\":\""+amt+"\",\"cardNo\":\""+card+"\",\"cardExpYymm\":\""+yymm+"\",\"merchantToken\":\""+hash+"\"}", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("URL", url);
//
                Toast.makeText(MainActivity.this, response,Toast.LENGTH_LONG).show();

                String url = response.replace("(", "");
                String hasil = url.replace(")", "");
                Log.d("AAA", hasil);

                try{
                    JSONObject obj = new JSONObject(hasil);
                    NicePay json = new NicePay();
                    json.setCardToken(obj.getString("cardToken"));
                    Log.d("cardToken", obj.getString("cardToken"));
                    DialogForm(obj.getString("cardToken"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestHandler.getInstance(this).addToRequestQueue(URL);
    }

    private void DialogForm(String token){
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.webview,null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
//        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("BAYAR");
        bayar = (WebView) dialogView.findViewById(R.id.secure);
        WebSettings sembarang = bayar.getSettings();
        sembarang.setJavaScriptEnabled(true);
        sembarang.setAllowFileAccess(true);
        sembarang.setAppCacheEnabled(true);
        bayar.loadUrl("https://www.nicepay.co.id/nicepay/api/secureVeRequest.do?country=360&callbackUrl=http://192.168.1.89/nicepay/PHP_Nicepay_Direct/3dsecure.php&onePassToken="+token);
//        bayar.loadUrl("facebook.com");


        dialog.show();
    }
}