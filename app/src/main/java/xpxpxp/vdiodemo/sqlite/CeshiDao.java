package xpxpxp.vdiodemo.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CeshiDao {
    private DBHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public CeshiDao(Context context) {// 定义构造函数
        helper = new DBHelper(context);// 初始化DBOpenHelper对象
    }

    /*插入*/
    public void insertDao(Ceshi ceshi) {
        db = helper.getWritableDatabase();//初始化SQLiteDatabase对象
        //执行插入操作
        db.execSQL("replace into ceshi (id,name,age,date) values (?,?,?,?)",
                new Object[]{
                        ceshi.getId(),
                        ceshi.getName(),
                        ceshi.getAge(),
                        ceshi.getDate()
                });
    }

    /*修改*/
    public void updateDao(Ceshi ceshi) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改收入信息操作
        db.execSQL(
                "update ceshi set name=?,age= ?,date=? where id= ?",
                new Object[]{
                        ceshi.getName(),
                        ceshi.getAge(),
                        ceshi.getDate(),
                        ceshi.getId()
                });
    }

    /*查询*/
    public Ceshi findDao(int id) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db
                .rawQuery(
                        "select id,name,age,date from ceshi where id= ?",
                        new String[]{String.valueOf(id)});// 根据编号查找，并存储到Cursor类中
        if (cursor.moveToNext())// 遍历查找到的收入信息
        {
            // 将遍历到的收入信息存储到Tb_inaccount类中
            return new Ceshi(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("age")),
                    cursor.getLong(cursor.getColumnIndex("date"))
            );
        }
        return null;
    }

    /*删除*/
    public void deleteDao(Integer... ids) {
        if (ids.length > 0) {//判断是否存在要删除的id
            StringBuffer sb = new StringBuffer();// 创建StringBuffer对象
            for (int i = 0; i < ids.length; i++)// 遍历要删除的id集合
            {
                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
            db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除收入信息操作
            db.execSQL("delete from ceshi where id in (" + sb + ")",
                    (Object[]) ids);
        }
    }

    /*获取所有数据*/
    public List<Ceshi> getAllData() {
        List<Ceshi> tb_ingoods = new ArrayList<Ceshi>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有收入信息
        Cursor cursor = db.rawQuery("select * from ceshi", null);
        while (cursor.moveToNext())// 遍历所有的收入信息
        {
            // 将遍历到的收入信息添加到集合中
            tb_ingoods.add(new Ceshi(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("age")),
                    cursor.getLong(cursor.getColumnIndex("date"))
            ));
        }
        return tb_ingoods;// 返回集合
    }

}
