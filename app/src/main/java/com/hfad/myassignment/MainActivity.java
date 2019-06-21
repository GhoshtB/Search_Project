package com.hfad.myassignment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.hfad.myassignment.adapter.CardAdapter;
import com.hfad.myassignment.db.DataRepository;
import com.hfad.myassignment.db.DataViewModel;
import com.hfad.myassignment.db.Datas;
import com.hfad.myassignment.model.Data;
import com.hfad.myassignment.model.LoanData;
import com.hfad.myassignment.model.UserData;
import com.hfad.myassignment.service.ApiClient;
import com.hfad.myassignment.service.ApiService;
//import android.arch.lifecycle.ViewModelProviders;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText serarchedit;
    RecyclerView loan_list;
    ImageView search;
    DataViewModel viewModel;
    DataRepository repository;
    List<Data> dataList=new ArrayList<>();
    boolean aBoolean=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serarchedit=findViewById(R.id.serarchedit);
        loan_list=findViewById(R.id.loan_list);
        search=findViewById(R.id.search);
        loan_list.setLayoutManager(new LinearLayoutManager(this));
repository=new DataRepository(getApplication());

        viewModel= ViewModelProviders.of(this).get(DataViewModel.class);
       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               calleditDetails(serarchedit.getText().toString());
           }
       });

        serarchedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
if (s.length()>=2){
    calleditDetails(s);
    calldatas(s);
}
            }

            @Override
            public void afterTextChanged(Editable s) {
                calleditDetails(s.toString());
                calldatas(s.toString());
            }
        });
    }

    private void calldatas(CharSequence toString) {
        if (aBoolean=false){

            viewModel.getAllDatas(toString.toString()).observe(MainActivity.this, new Observer<List<Datas>>() {
                @Override
                public void onChanged(@Nullable List<Datas> datas) {
                    List<Data> list=new ArrayList<>();
                    for (Datas datas1:datas){
                        list.add(new Data(datas1.getFundname(),datas1.getMinsipamount(),datas1.getMinsipmultiple()));
                    }
                    CardAdapter adapter=new CardAdapter(list,MainActivity.this);
                    loan_list.setAdapter(adapter);
                    adapter.setCardListener(new CardAdapter.CardListener() {
                        @Override
                        public void onCardClick(@NotNull Data data) {
                            showCfDialog(data);
                        }
                    });
                }
            });
        }
    }

    private void calleditDetails(CharSequence toString) {
        UserData userData=new UserData();
        userData.setKeyword(toString);
        ApiClient client=new  ApiService().getRetrofit().create(ApiClient.class);
        Call<LoanData> call= client.getApiDetails(userData,"GQCqJDU1hZ3e4aXOhLG905HbKoiBV1ZU6ipCB9Oc");

        call.enqueue(new Callback<LoanData>() {
            @Override
            public void onResponse(Call<LoanData> call, Response<LoanData> response) {
                CardAdapter adapter=new CardAdapter(response.body().getData(),MainActivity.this);

                loan_list.setAdapter(adapter);
                adapter.setCardListener(new CardAdapter.CardListener() {
                    @Override
                    public void onCardClick(@NotNull Data data) {
                        showCfDialog(data);
                    }
                });
dataList=response.body().getData();
                for (Data data:dataList){
                    viewModel.insert(new Datas(data.getFundname(),data.getMinsipamount(),data.getMinsipmultiple()));
                }
            }

            @Override
            public void onFailure(Call<LoanData> call, Throwable t) {
aBoolean=false;
            }
        });
    }

    public void showCfDialog(Data data){
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this)
                .setHeaderView(R.layout.layouts_header)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle("Your Fund Has Been Added To Your DashBoard")
                .setMessage(data.getFundname()+"Prudent Value Discovery Fund Growth has been successfully added to your dashboard.")
                .addButton("Got It", Color.BLUE, Color.WHITE, CFAlertDialog.CFAlertActionStyle.DEFAULT, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                    Toast.makeText(MainActivity.this, " tapped", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

// Show the alert
        builder.show();
    }
}
