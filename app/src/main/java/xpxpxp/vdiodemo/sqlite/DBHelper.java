package xpxpxp.vdiodemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//定义数据库版本号
    private static final String DBNAME = "ceshi.db"; //定义数据库名

    public DBHelper(Context context) {//定义构造函数
        //参数 上下文 数据库名称 cosor工厂 版本号
        super(context, DBNAME, null, VERSION);//重写基类的构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建数据库
        //序号，姓名，年龄，登记日期
        db.execSQL("create table ceshi (id varchar(10) primary key, name varchar(200),age varchar(10),date varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//更新数据库
        //本方法主要用于更新数据库 通过对当前版本的判断 实现数据库的更新
        switch (oldVersion) {
            case 1:
                //创建新表，注意createTable()是静态方法
                // 加入新字段
                // db.execSQL("ALTER TABLE 'moments' ADD 'audio_path' TEXT;");
                // TODO
                break;
        }
    }
}
