package interview.alvarez.pedro.presentation.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import interview.alvarez.pedro.MyApp;
import interview.alvarez.pedro.R;
import interview.alvarez.pedro.presentation.base.BaseActivity;
import interview.alvarez.pedro.presentation.view.queuephotos.QueuePhotosFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        this.initializeInjector();
        if (savedInstanceState == null) {
//            addFragment(R.id.fragment_container, PlayerCharacterFragment.newInstance());
            addFragment(R.id.fragment_container, QueuePhotosFragment.newInstance());
        }
    }

    private void initializeInjector() {
        ((MyApp) getApplication()).getApplicationComponent().inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
