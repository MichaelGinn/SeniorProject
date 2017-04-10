package robinson.mapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Michael on 3/8/2017.
 */
public class Help extends MapsActivity implements View.OnClickListener {

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.helpwindow);

        btnClose = (Button) findViewById(R.id.close_button);
        btnClose.setOnClickListener(this);
        Toast.makeText(this, "button set", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.close_button:
                startActivity(new Intent(getApplicationContext(), TitleActivity.class));
            break;
        }
    }
}

