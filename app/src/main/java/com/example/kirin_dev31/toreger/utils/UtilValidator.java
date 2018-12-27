package com.example.kirin_dev31.toreger.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UtilValidator {
    // バリデーションコード
    public static final String REQUIRED = "required";
    public static final String EMAIL = "email";
    public static final String HANKAKU = "hankaku";
    // 終了コード
    public static final String END = "end";
    // バリデーションコードに合うチェックロジックを実行
    private static final Map<String, Integer> validators= new HashMap<String, Integer>();
    static {
        validators.put(REQUIRED, 1);
        validators.put(EMAIL, 2);
        validators.put(HANKAKU, 3);
    }
    // Context
    private static Context context = null;
    // チェック対象名
    private static String colum = "";

    public UtilValidator(Context context){
        this.context = context;
    }

    /**
     *
     * @return
     */
    private String isNotNull() {
        if (colum == null | colum.isEmpty()) {
            return context.getString(R.string.valid_required);
        }
        return null;
    }

    /**
     * Emailのバリデーション
     * @return
     */
    private String isEmailValid() {
        if (!colum.contains("@")) {
            return context.getString(R.string.valid_email);
        }
        return null;
    }

    /**
     * 半角チェック
     * @return
     */
    private String isHankaku() {
        if (colum == null || colum.equals("")) {
            return null;
        }
        String regText = "[ -~｡-ﾟ]+";
        Pattern pattern = Pattern.compile(regText);
        if (!pattern.matcher(colum).matches()) {
            return context.getString(R.string.valid_hankaku);
        }
        return null;
    }

    /**
     * バリデーションチェック実行
     * @param validates
     * @return
     */
    public Map<TextView, String> validate(Map<TextView, String[]> validates) {
        Map<TextView, String> result = new HashMap<TextView, String>();

        for(TextView key : validates.keySet()) {
            String value = validates.get(key)[0];
            String rule = validates.get(key)[1];
            colum = value;
            for(String r : rule.split("/")) {
                if (validators.containsKey(r)) {
                    String message = validateExe(validators.get(r));
                    if (r == END) {
                        break;
                    }
                    if (message != null) {
                        result.put(key, message);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * バリデーション実行
     * @param validCode
     * @return
     */
    private String validateExe(int validCode) {
        switch (validCode) {
            case 1:
                return isNotNull();
            case 2:
                return isEmailValid();
            case 3:
                return isHankaku();
            default:
        }
        return null;
    }

    /**
     * バリデーションルール整形
     * @param args
     * @return
     */
    public static String createRule(String...args) {
        String result = "";
        for (String param : args) {
            result += param + "|";
        }
        return result;
    }
}
