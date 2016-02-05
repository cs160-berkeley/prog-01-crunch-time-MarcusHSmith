package com.example.marcus.theathleticandroid;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public View curActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText rawWeight = (EditText)findViewById(R.id.editTextWeight);
        rawWeight.setText("150");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onWorkoutButtonClick(View v){
        Log.d("NO", "NAH");
        EditText rawCount = (EditText)findViewById(R.id.editTextCount);
        EditText rawWeight = (EditText)findViewById(R.id.editTextWeight);
        TextView activity = (TextView)findViewById(R.id.textViewActivity);
        EditText result = (EditText)findViewById(R.id.editTextCalories);
        TextView min_rep_text = (TextView)findViewById(R.id.textViewRepsMin);
        ImageView activity_image = (ImageView)findViewById(R.id.imageViewActivity);

        int caloriesBurned = 0;
        String repsOrMin = "";

        // If no count, then exit
        if (rawCount.getText().toString().matches("")) {
            return;
        }
        if (v.getId() == R.id.editTextCount || v.getId() == R.id.editTextWeight){
            v = curActivity;
        } else {
            curActivity = (View)findViewById(v.getId());
        }

        switch (v.getId()){
            case R.id.buttonCycling:
                activity.setText("Cycling");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 12);
                activity_image.setImageResource(R.drawable.cycling);
                break;
            case R.id.buttonJogging:
                activity.setText("Jogging");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 12);
                activity_image.setImageResource(R.drawable.jogging);
                break;
            case R.id.buttonJumpingJacks:
                activity.setText("Jumping Jacks");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 10);
                activity_image.setImageResource(R.drawable.jumping);
                break;
            case R.id.buttonLegLift:
                activity.setText("Leg-Lifts");
                repsOrMin = "Reps";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 25);
                activity_image.setImageResource(R.drawable.leg);
                break;
            case R.id.buttonPlank:
                activity.setText("Planks");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 25);
                activity_image.setImageResource(R.drawable.plank);
                break;
            case R.id.buttonPullup:
                activity.setText("Pullups");
                repsOrMin = "Reps";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 100);
                activity_image.setImageResource(R.drawable.pullup);
                break;
            case R.id.buttonPushup:
                activity.setText("Pushups");
                repsOrMin = "reps";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 350);
                activity_image.setImageResource(R.drawable.pushup);
                break;
            case R.id.buttonSitup:
                activity.setText("Situps");
                repsOrMin = "Reps";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 200);
                activity_image.setImageResource(R.drawable.situp);
                break;
            case R.id.buttonSquat:
                activity.setText("Squats");
                repsOrMin = "Reps";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 225);
                activity_image.setImageResource(R.drawable.squat);
                break;
            case R.id.buttonStairClimbing:
                activity.setText("Stair-Climbing");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 15);
                activity_image.setImageResource(R.drawable.stair);
                break;
            case R.id.buttonSwimming:
                activity.setText("Swimming");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 13);
                activity_image.setImageResource(R.drawable.swimming);
                break;
            case R.id.buttonWalking:
                activity.setText("Walking");
                repsOrMin = "Min";
                caloriesBurned = (int) Math.round((double)Integer.parseInt(rawCount.getText().toString()) * 100 / 20);
                activity_image.setImageResource(R.drawable.walking);
                break;
        }

        min_rep_text.setText(repsOrMin);

        // TODO : UPDATE EACH CELL WITH THEIR EQUIVELENT

        updateButtons(caloriesBurned);

        // TODO : USE WEIGHT TO CALCULATE CORRECT CALORIES
        /* Using Sharecare.com info
            100lbs 15min walking = 100 calories
            150lbs 15min walking = 150 calories
            200lbs 15min walking = 200 calories
            ... [assuptions] ...
            0lbs   15min walking = 0  calories
            50lbs  15min walking = 50 calories
            250lbs 15min walking = 250 calories
            300lbs 15min walking = 300 calories

            Formula
            Calories/150 * pounds = calories burned

        */
        if (!rawWeight.getText().toString().matches("")) {
            caloriesBurned = (int) Math.round((double) caloriesBurned * (double) Integer.parseInt(rawWeight.getText().toString()) / 150);
        }
        result.setText(Integer.toString(caloriesBurned));
    }

    public void onCaloriesEditTextUpdate(View v){
        EditText rawCalories = (EditText)findViewById(R.id.editTextCalories);
        EditText rawWeight = (EditText)findViewById(R.id.editTextWeight);
        EditText rawRepsMin = (EditText)findViewById(R.id.editTextCount);
        int calories = 0;
        if (!rawCalories.getText().toString().matches("")) {
            calories = Integer.parseInt(rawCalories.getText().toString());
            int count = (int) Math.round((double) calories * (double)150 / (double) Integer.parseInt(rawWeight.getText().toString()));
            rawRepsMin.setText(Integer.toString(count));
            updateButtons(calories);
        }

    }

    // HELPER FUNCTIONS

    public void updateButtons(int count){
        ((Button)findViewById(R.id.buttonCycling)).setText("\n" + count*12/100 + " min" + "\nCycling");
        ((Button)findViewById(R.id.buttonJogging)).setText("\n" + count*12/100 + " min" + "\nJogging");
        ((Button)findViewById(R.id.buttonJumpingJacks)).setText("\n" + count*10/100 + " min" + "\nJumping Jacks");
        ((Button)findViewById(R.id.buttonLegLift)).setText("\n" + count*25/100 + " reps" + "\nLeg-Lift");
        ((Button)findViewById(R.id.buttonPlank)).setText("\n" + count*25/100 + " min" + "\nPlank");
        ((Button)findViewById(R.id.buttonPullup)).setText("\n" + count*100/100 + " reps" + "\nPullup");
        ((Button)findViewById(R.id.buttonPushup)).setText("\n" + count*350/100 + " reps" + "\nPushup");
        ((Button)findViewById(R.id.buttonSitup)).setText("\n" + count*200/100 + " reps" + "\nSitup");
        ((Button)findViewById(R.id.buttonSquat)).setText("\n" + count*225/100 + " reps" + "\nSquat");
        ((Button)findViewById(R.id.buttonStairClimbing)).setText("\n" + count*15/100 + " min" + "\nStair-Climbing");
        ((Button)findViewById(R.id.buttonSwimming)).setText("\n" + count*13/100 + " min" + "\nSwimming");
        ((Button)findViewById(R.id.buttonWalking)).setText("\n" + count*20/100 + " min" + "\nWalking");
    }
}
