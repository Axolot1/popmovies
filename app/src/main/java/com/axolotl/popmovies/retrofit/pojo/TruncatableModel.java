package com.axolotl.popmovies.retrofit.pojo;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;

/**
 * Created by axolotl on 16/3/28.
 */
public class TruncatableModel extends Model {
    public static void truncate(Class<? extends Model> type){
        TableInfo tableInfo = Cache.getTableInfo(type);
        ActiveAndroid.execSQL(
                String.format("DELETE FROM %s;",
                        tableInfo.getTableName()));
        ActiveAndroid.execSQL(
                String.format("DELETE FROM sqlite_sequence WHERE name='%s';",
                        tableInfo.getTableName()));
    }
}
