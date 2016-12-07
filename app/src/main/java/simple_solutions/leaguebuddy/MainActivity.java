package simple_solutions.leaguebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple_solutions.leaguebuddy.lolapi.data.Summoner;
import simple_solutions.leaguebuddy.lolapi.tasks.ApiMethod;
import simple_solutions.leaguebuddy.lolapi.tasks.ApiRequestFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
    }

    private void fetchData() {
        ApiRequestFactory.createRequest(ApiMethod.GET_BY_NAME, "bmote95").makeRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Summoner>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Task complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Summoner summoner) {
                        Log.d(TAG, summoner.toString());
                    }
                });
    }
}
