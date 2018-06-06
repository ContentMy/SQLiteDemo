package xpxpxp.vdiodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xpxpxp.vdiodemo.sqlite.Ceshi;
import xpxpxp.vdiodemo.sqlite.CeshiDao;

public class NextActivity extends AppCompatActivity {
    private static final String TAG = "sayay";
    private EditText idEt;
    private EditText nameEt;
    private EditText ageEt;
    private EditText dateEt;
    private ListView mLv;
    private int id = 0;
    private String name;
    private int age = 0;
    private long date = 0;
    private CeshiDao dao;
    private List<Ceshi> mList;
    private CeshiAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        dao = new CeshiDao(this);
        initView();
        //得到EditText数据需要toString然后trim去掉空格

    }

    private void initView() {
        idEt = findViewById(R.id.id_et);
        nameEt = findViewById(R.id.name_et);
        ageEt = findViewById(R.id.age_et);
        dateEt = findViewById(R.id.date_et);
        mLv = findViewById(R.id.lv);


        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.item_id);
                int textId = Integer.parseInt(String.valueOf(textView.getText()));
                mList.remove(position);
                dao.deleteDao(textId);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertView();
            }
        });
        findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView();
            }
        });
        findViewById(R.id.find_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findView();
            }
        });
        findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteView();
            }


        });
        findViewById(R.id.all_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allView();
            }
        });
    }

    private void allView() {
        mList = dao.getAllData();
        if (mList != null) {
            adapter = new CeshiAdapter(this, mList);
            mLv.setAdapter(adapter);
        }
    }

    private void insertView() {
        if (!String.valueOf(idEt.getText().toString().trim()).equalsIgnoreCase("")) {
            id = Integer.parseInt(String.valueOf(idEt.getText().toString().trim()));
        }
        if (!String.valueOf(nameEt.getText().toString().trim()).equalsIgnoreCase("")) {
            name = String.valueOf(nameEt.getText().toString().trim());
        }

        if (!String.valueOf(ageEt.getText().toString().trim()).equalsIgnoreCase("")) {
            age = Integer.parseInt(String.valueOf(ageEt.getText().toString().trim()));
        }

        if (!String.valueOf(dateEt.getText().toString().trim()).equalsIgnoreCase("")) {
            date = Long.parseLong(String.valueOf(dateEt.getText().toString().trim()));
            Log.e(TAG, "44444-------》" + date);
        }
        if (id != 0 && name != null && age != 0) {
            Ceshi ceshi = new Ceshi(id, name, age, date);
            dao.insertDao(ceshi);
        }
        mList = dao.getAllData();
    }


    private void deleteView() {
    }

    private void findView() {
    }

    private void updateView() {
    }

}

