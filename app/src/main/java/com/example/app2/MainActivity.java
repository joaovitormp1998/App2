package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private TextView mTextViewResult;
    private TextView mTextViewResult2;
    private ImageView imageView2;
    ListView listView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bnv);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new GryffindorFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
//                mTextViewResult = findViewById(R.id.text_view_result);
//                mTextViewResult2 = findViewById(R.id.text_view_result2);
//                imageView2 = findViewById(R.id.imageView2);
                listView = findViewById(R.id.ListViewId);

                switch (item.getItemId()) {
                    case R.id.ic_gryffindor:
                        String casa = "gryffindor";
                        request_api(casa);
                        selectedFragment = new GryffindorFragment();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int t, long id) {
                                if (t == 0) {
                                    startActivity(new Intent(MainActivity.this, HarryActivity.class));
                                } else if (t == 1) {
                                    startActivity(new Intent(MainActivity.this, HermioneActivity.class));
                                } else {

                                }
                            }
                        });
                        break;
                    case R.id.ic_sonser:
                        casa = "slytherin";
                        request_api(casa);
                        selectedFragment = new SonserFragment();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int t, long id) {
                                if (t == 0) {
                                    startActivity(new Intent(MainActivity.this, DracoActivity.class));
                                } else if (t == 1) {
                                    startActivity(new Intent(MainActivity.this, SeveroActivity.class));
                                } else {

                                }
                            }
                        });
                        break;
                    case R.id.ic_lufalufa:
                        casa = "hufflepuff";
                        request_api(casa);
                        selectedFragment = new LufalufaFragment();
                        break;
                    case R.id.ic_ravenclow:
                        casa = "ravenclaw";
                        request_api(casa);
                        selectedFragment = new RavenclowFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
                return true;

            }
        });
    }

    private  void request_api(String casa) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://hp-api.herokuapp.com/api/characters/house/" + casa;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    String myResponse;
                    myResponse = response.body().string();
                    JSONArray teste = new JSONArray();
                    try {
                        teste = new JSONArray(myResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//finalTeste.length();

                    JSONArray pessoas = teste;
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            try {
                                String uri = null;
                                String name = null;
                                String species = null;
                                String gender = null;
                                String house = null;
                                String dateOfBirth = null;
                                String ancestry = null;
                                String patronus = null;
                                String actor = null;
                                ArrayList<String> lista = new ArrayList<>();
                                for (int i = 0; i < pessoas.length(); i++) {
                                    JSONObject pessoa = (JSONObject) pessoas.get(i);
                                    name = pessoa.getString("name");
                                    if (pessoa.getString("image").equals("")) {
                                        uri = "https://cdn-icons-png.flaticon.com/512/16/16480.png";
                                    } else {
                                        uri = pessoa.getString("image");
                                    }
                                    species = pessoa.getString("species");
                                    gender = pessoa.getString("gender");
                                    house = pessoa.getString("house");
                                    dateOfBirth = pessoa.getString("dateOfBirth");
                                    ancestry = pessoa.getString("ancestry");
                                    patronus = pessoa.getString("patronus");
                                    actor = pessoa.getString("actor");
                                    lista.add(name);
                                    arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list, lista);
                                    listView.setAdapter(arrayAdapter);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}