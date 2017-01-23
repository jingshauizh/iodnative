package com.mvp.jingshuai.data.idal;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.mvp.jingshuai.data.model.InfoObjectModel$Table;
import com.mvp.jingshuai.data.util.ValidationUtil;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by eqruvvz on 1/19/2017.
 */
public class InfoIdal extends BaseIdal {

    public InfoIdal( SQLiteDatabase database) {
        super( database);
    }



    public synchronized void save(InfoObjectModel infoObjectModel) {
        ValidationUtil.validate(infoObjectModel);
        saveValid(infoObjectModel);
    }

    public synchronized void saveAll(final List<InfoObjectModel> infoObjectModels) {
        ValidationUtil.pruneInvalid(infoObjectModels);
        if (infoObjectModels.isEmpty()) {
            return;
        }
        TransactionManager.transact(mSQLiteDatabase, new Runnable() {
            @Override
            public void run() {
                for (InfoObjectModel infoObjectModel : infoObjectModels) {
                    saveValid(infoObjectModel);
                }
            }
        });
    }

    private void saveValid(InfoObjectModel infoObjectModel) {
        InfoObjectModel existing = loadByInfoId(infoObjectModel.getInfoId());
        if (existing == null) {
            infoObjectModel.save();
        } else {
            infoObjectModel.setInfoId(existing.getInfoId());
            infoObjectModel.update();
        }
    }

    @Nullable
    public synchronized InfoObjectModel loadByInfoId(String infoId) {
        if (StringUtils.isEmpty(infoId)) {
            return null;
        }
        return new Select().from(InfoObjectModel.class)
                .where(Condition.column(InfoObjectModel$Table.INFOID).eq(infoId))
                .querySingle();
    }
}
