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

public class SQLiteActivity extends AppCompatActivity {
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
        initOnClick();
    }

    private void initView() {
        idEt = findViewById(R.id.id_et);
        nameEt = findViewById(R.id.name_et);
        ageEt = findViewById(R.id.age_et);
        dateEt = findViewById(R.id.date_et);
        mLv = findViewById(R.id.lv);
    }

    private void initOnClick() {
        //长按删除条目和数据库的数据
        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.item_id);
                //获取到对应item的id，用来删除数据库的数据
                int textId = Integer.parseInt(String.valueOf(textView.getText()));
                //从条目中移除
                mList.remove(position);
                //从数据库中删除
                dao.deleteDao(textId);
                //更新UI
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

    //查询所有数据
    private void allView() {
        mList = dao.getAllData();
        if (mList != null) {
            adapter = new CeshiAdapter(this, mList);
            mLv.setAdapter(adapter);
        }
    }

    //插入数据
    private void insertView() {
        //得到EditText数据需要toString然后trim去掉空格
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
        }
        if (id != 0 && name != null && age != 0) {
            Ceshi ceshi = new Ceshi(id, name, age, date);
            dao.insertDao(ceshi);
            if (mList != null) {
                /*这里本来是使用add的，但是在添加update功能的时候发现更改了同一id下的name数据的时候，
                发现insert添加数据库的数据和list的数据对不上，数据库里的数据我在insertDao()方法里使用
                的是replace，添加时，如果有存在的列会替换掉，但list是一直累加的，所以这里发现这个问题之后，
                我就使用先清空集合，在添加所有数据库数据的方法啦*/
//                mList.add(ceshi);
                mList.clear();
                mList.addAll(dao.getAllData());
                adapter.notifyDataSetChanged();
            } else {
                mList = dao.getAllData();
                adapter = new CeshiAdapter(this, mList);
                mLv.setAdapter(adapter);
            }
        }
    }

    //删除数据
    private void deleteView() {
        /*删除这部分目前是在ListView条目的长按监听里调用了*/
        Toast.makeText(this, "请长按列表条目删除~", Toast.LENGTH_SHORT).show();
    }

    //查询单个数据
    private void findView() {
        /*查询当单个数据的部分目前是使用了Toast去展示数据了*/
        if (!String.valueOf(idEt.getText().toString().trim()).equalsIgnoreCase("")) {
            id = Integer.parseInt(String.valueOf(idEt.getText().toString().trim()));
        }
        Ceshi ceshi = dao.findDao(id);
        if (id == 0) {
            Toast.makeText(this, "id不能为空或者为0哦", Toast.LENGTH_SHORT).show();
        } else {
            if (ceshi != null) {
                Toast.makeText(this, "查询结果为：" + ceshi.getId() + "," + ceshi.getName() + "," + ceshi.getAge() + "," + ceshi.getDate(), Toast.LENGTH_SHORT).show();
                id = 0;
            } else {
                Toast.makeText(this, "已经没有数据啦", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //修改数据
    private void updateView() {
        /*修改更新数据的部分，和insert差不多，看代码就可以了*/
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
        }
        if (id != 0 && name != null && age != 0) {
            Ceshi ceshi = new Ceshi(id, name, age, date);
            dao.updateDao(ceshi);
            mList.clear();
            mList.addAll(dao.getAllData());
            adapter.notifyDataSetChanged();
        }

    }

}

