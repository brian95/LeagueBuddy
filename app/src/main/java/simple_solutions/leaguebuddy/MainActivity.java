package simple_solutions.leaguebuddy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple_solutions.leaguebuddy.data.league.Entries;
import simple_solutions.leaguebuddy.data.league.League;
import simple_solutions.leaguebuddy.lolapi.tasks.LeagueApiRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    //Binding Views to Butterknife.
    @BindView(R.id.inputName)
    EditText inputName;
    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvRank)
    TextView tvRank;
    @BindView(R.id.tvWins)
    TextView tvWins;
    @BindView(R.id.tvLosses)
    TextView tvLosses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.searchBtn)
    public void search() {
        getLeague();
    }

    private void getLeague() {
        //Get the user input.
        String name = inputName.getText().toString();

        //Setup the ProgressDialog
        ProgressDialog dialog = setupDialog();

        //Check to see if the dialog is currently showing.
        if (!dialog.isShowing()) {
            dialog.show();
        }

        //Make the Api request.
        LeagueApiRequest leagueApiRequest = new LeagueApiRequest(name);
        leagueApiRequest.makeRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<League>() {
                    @Override
                    public void onCompleted() {
                        //Dismiss the dialog and log
                        dialog.dismiss();
                        Log.d(TAG, "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Dismiss the dialog and log
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Unable to find that summoner",
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(League league) {
                        if (league == null) {
                            Toast.makeText(
                                    MainActivity.this, "This summoner is not ranked yet",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String name = "Name: ";
                            String rank = "Rank: ";
                            String wins = "Wins: ";
                            String losses = "Losses: ";
                            rank = rank + league.getTier();
                            //Looping through the results
                            for (Entries entry : league.getEntries()) {
                                name = name + entry.getPlayerOrTeamName();
                                rank = rank + " " + entry.getDivision();
                                wins = wins + entry.getWins();
                                losses = losses + entry.getLosses();
                                //Display the data using TextViews
                                tvName.setText(name);
                                tvRank.setText(rank);
                                tvWins.setText(wins);
                                tvLosses.setText(losses);
                            }
                        }
                    }
                });
    }

    private ProgressDialog setupDialog() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        return dialog;
    }
}
