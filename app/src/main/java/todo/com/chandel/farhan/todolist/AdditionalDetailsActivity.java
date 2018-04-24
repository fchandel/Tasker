package todo.com.chandel.farhan.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdditionalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_details);
    }

    public static Intent getIntentForLaunch(Context context){
        Intent intent = new Intent(context, AdditionalDetailsActivity.class);
        return intent;
    }
}
