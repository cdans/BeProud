package com.example.exam;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.round;


public class MainActivity extends AppCompatActivity {


    public TextView dateView;
    public SimpleDateFormat startDate = new SimpleDateFormat("d-MM-yyyy");

    public CalendarView calendarView;

    public TextView daysSince;
    public TextView textBought;
    public TextView counter;
    public TextView textSaved;
    public TextView savedMoney;
    public TextView couldSavedMoney;

    public TextView smoked;
    public TextView counterCigarettes;
    public TextView insteadOf;
    public TextView cigarettes;

    public ImageView heartwhite;
    public ImageView heart;


    public Button minus;
    public Button plus;

    public Button minusCigarettes;
    public Button plusCigarettes;

    public EditText editAmount;
    public TextView thisDate;

    int bought = 2;
    int money;
    int todays;
    long ts = System.currentTimeMillis() / 1000;

    int smokedCigarettes = 51;

    public int calc(int days) {
        money = days * 4 - 7 * bought;
        return money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView) findViewById(R.id.dateView);
        couldSavedMoney = (TextView) findViewById(R.id.couldSavedMoney);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setMinDate((new Date().getTime() + 86400000));

        daysSince = (TextView) findViewById(R.id.daysSince);
        textBought = (TextView) findViewById(R.id.bought);
        counter = (TextView) findViewById(R.id.counter);
        textSaved = (TextView) findViewById(R.id.textSaved);
        savedMoney = (TextView) findViewById(R.id.savedMoney);

        heartwhite = (ImageView) findViewById(R.id.heartwhite);
        heart = (ImageView) findViewById(R.id.heart);


        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);


        smoked = (TextView) findViewById(R.id.smoked);
        counterCigarettes = (TextView) findViewById(R.id.counterCigarettes);
        insteadOf = (TextView) findViewById(R.id.insteadOf);
        cigarettes = (TextView) findViewById(R.id.cigarettes);

        minusCigarettes = (Button) findViewById(R.id.minusCigarettes);
        plusCigarettes = (Button) findViewById(R.id.plusCigarettes);


        editAmount = (EditText) findViewById(R.id.editAmount);

        thisDate = (TextView) findViewById(R.id.thisDate);

        long diff = ts - 1573171200;
        todays = (int) diff / (60 * 60 * 24);

        double dimensionInPixelD = 245 - 0.8148148148 * todays;
        int dimensionInPixel = (int) dimensionInPixelD;
        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
        heartwhite.getLayoutParams().height = dimensionInDp;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.DAY_OF_MONTH, 8);
        Date dateRepresentation = cal.getTime();

        String dateString = startDate.format(dateRepresentation);

        daysSince.setText(todays + " days since " + dateString);

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dateRepresentation = cal.getTime();
        dateString = startDate.format(dateRepresentation);

        dateView.setText("On the " + dateString + " you could save:");

        money = calc(todays);
        savedMoney.setText(money + "€");
        couldSavedMoney.setText(money + 4 + "€");

        cigarettes.setText(todays * 12 + " cigarettes");

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bought > 0) {
                    bought--;
                    counter.setText(Integer.toString(bought));
                    money = calc(todays);
                    savedMoney.setText(money + "€");

                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bought++;
                counter.setText(Integer.toString(bought));
                money = calc(todays);
                savedMoney.setText(money + "€");

            }
        });

        minusCigarettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (smokedCigarettes > 0) {
                    smokedCigarettes--;
                    counterCigarettes.setText(Integer.toString(smokedCigarettes));
                }
            }
        });
        plusCigarettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smokedCigarettes++;
                counterCigarettes.setText(Integer.toString(smokedCigarettes));

            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                Calendar cal = Calendar.getInstance();


                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Date dateRepresentation = cal.getTime();

                String dateString = dateFormat.format(dateRepresentation);
                long calTime = cal.getTimeInMillis() / 1000;

                calTime = calTime - 1573171200;
                int futureDays = (int) calTime / (60 * 60 * 24);
                dateView.setText("On the " + dateString + " you could save:");
                int money = calc(futureDays);
                couldSavedMoney.setText(money + "€");

            }
        });

        editAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthEditAmount = editAmount.getText().toString().trim().length();
                if (editAmount.hasFocus() && lengthEditAmount != 0) {

                    int amount = Integer.parseInt(s.toString()) + bought * 7;
                    while (amount % 4 != 0) {
                        amount++;
                    }
                    int days = amount / 4;

                    if (days > todays) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, 2019);
                        cal.set(Calendar.MONTH, 10);
                        cal.set(Calendar.DAY_OF_MONTH, 8);
                        cal.add(Calendar.DAY_OF_YEAR, days);
                        Date dateRepresentation = cal.getTime();

                        String dateString = startDate.format(dateRepresentation);

                        dateString = startDate.format(dateRepresentation);

                        thisDate.setText(dateString);
                    }
                    else{
                        thisDate.setText("...");
                    }
                } else if (editAmount.hasFocus() && lengthEditAmount == 0) {
                    thisDate.setText("...");
                }
            }
        });

    }


}
