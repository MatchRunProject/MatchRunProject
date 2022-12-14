package com.ygh547.matchrun.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.ygh547.matchrun.R;
import com.ygh547.matchrun.api.NetworkClient;
import com.ygh547.matchrun.api.UserApi;
import com.ygh547.matchrun.model.User2;
import com.ygh547.matchrun.model.UserRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private boolean validate=false;

    String ID_checker = "";
    private InputMethodManager imm;

    EditText editemail;
    EditText editpassword;
    EditText editrepassword;
    EditText editquestionAn;
    EditText editname;
    EditText editNickname;
    EditText editaddress;
    EditText editreaddress;
    EditText editmyinfor;

    Spinner spincheck;

    Button btnCheck1;
    Button btnCheck2;
    Button btnregister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editemail = findViewById(R.id.editemaill);
        editpassword = findViewById(R.id.editpassword);
        editrepassword = findViewById(R.id.editrepassword);
        editquestionAn = findViewById(R.id.editquestionAn);
        editname = findViewById(R.id.editname);
        editNickname = findViewById(R.id.editNickname);
        editaddress = findViewById(R.id.editsubemail);
        editreaddress = findViewById(R.id.editreaddress);
        editmyinfor = findViewById(R.id.editmyinfor);

        spincheck = findViewById(R.id.spincheck);

        btnCheck1 = findViewById(R.id.btncheck1);
        btnCheck2 = findViewById(R.id.btncheck2);
        btnregister = findViewById(R.id.btnregister);

        btnCheck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserEmail = editemail.getText().toString();
                if (validate) {
                    return; //?????? ??????
                }

                if (UserEmail.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("???????????? ???????????????.").setPositiveButton("??????", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("????????? ??? ?????? ??????????????????.").setPositiveButton("??????", null).create();
                                dialog.show();
                                editemail.setEnabled(false); //???????????? ??????
                                validate = true; //?????? ??????
                                btnCheck1.setBackgroundColor(getResources().getColor(R.color.black));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("?????? ???????????? ??????????????????.").setNegativeButton("??????", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(UserEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });




        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editemail.getText().toString();
                String userPass = editpassword.getText().toString();
                String userrePass = editrepassword.getText().toString();
                String userQnA = editquestionAn.getText().toString();
                String userName = editname.getText().toString();
                String usetNiname = editNickname.getText().toString();
                String useraddress = editaddress.getText().toString();
                String userreaddress = editreaddress.getText().toString();
                String userinfor = editmyinfor.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {//volley
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);//Register2 php??? response
                            boolean success = jasonObject.getBoolean("success");//Register2 php??? sucess
                            if (userPass.equals(userrePass)) {
                                if (success) {//???????????? ????????? ??????
                                    Toast.makeText(getApplicationContext(), "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            } else {//???????????? ????????? ??????
                                Toast.makeText(getApplicationContext(), "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //????????? volley??? ???????????? ????????? ???
                RegisterRequest registerRequest=new RegisterRequest(userID,userPass, userrePass, userQnA,userName,usetNiname,useraddress,userinfor,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

        // ????????? ????????? ???????????? ??????????????? ???????????? ????????? ????????? ?????? ?????????
//        protected String[] loginIdCheck(final String _id) throws IOException, InterruptedException {
//            final String[] result = new String[1];
//            new Thread() {
//                public void run() {
//                    try {
//                        Api.Client client = new Api.Client();
//                        client.IdCheck(_id);
//                        result[0] = client.getResponse_data();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//
//            sleep(1000);
//            return result;
//        }
    }
//    void showProgress(String message){
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage(message);
//        dialog.show();
//    }

    void dismissProgress(){
        dialog.dismiss();
    }
}