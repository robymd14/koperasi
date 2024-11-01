package com.bemiroby.koperasi.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bemiroby.koperasi.database.DatabaseClient;
import com.bemiroby.koperasi.database.DatabaseModel;
import com.bemiroby.koperasi.database.dao.DatabaseDao;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryViewModel extends AndroidViewModel {

    LiveData<List<DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public HistoryViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        modelDatabase = databaseDao.getAllOrder();
    }

    //untuk menampilkan data dari database ke recyclerview
    public LiveData<List<DatabaseModel>> getDataList() {
        return modelDatabase;
    }

    //untuk menghapus data berdasarkan Id secara realtime
    public void deleteDataById(final int uid) {
        Completable.fromAction(() -> databaseDao.deleteSingleData(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
