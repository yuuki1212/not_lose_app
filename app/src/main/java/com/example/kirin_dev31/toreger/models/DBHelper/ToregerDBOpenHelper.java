package com.example.kirin_dev31.toreger.models.DBHelper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kirin_dev31.toreger.views.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ToregerDBOpenHelper extends SQLiteOpenHelper {

    public Context m_context;

    public static final String TAG = "ToregerDBOpenHelper";
    public static final String DB_NAME = "toreger_db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_DB_CALL_SQL_DIR = "sql/create";

    public ToregerDBOpenHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.m_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            execFileSQL(db, CREATE_DB_CALL_SQL_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * ディレクトリ配下のSQLファイルを実行
     * @param database
     * @param filedir
     */
    private void execFileSQL(SQLiteDatabase database, String filedir) throws IOException {
        AssetManager as = m_context.getResources().getAssets();
        // ディレクトリは以下のファイル名を取得
        String files[] = as.list(filedir);

        for(String filename : files) {
            String str = readFile(as.open(filedir + Constants.FILE.SEPARATION + filename));
            for (String sql : str.split(Constants.FILE.SEPARATION)) {
                database.execSQL(sql);
            }
        }
    }

    /**
     * ファイルから文字列を読み込みます。
     * @param is
     * @return ファイルの文字列
     * @throws IOException
     */
    private String readFile(InputStream is) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(is, Constants.FILE.UTF_8));
            StringBuilder sql = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sql.append(str + Constants.FILE.NEW_LINE_CODE);
            }
            return sql.toString();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
