package com.udacity.stockhawk.sync;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.udacity.stockhawk.ui.MainActivity;
import com.udacity.stockhawk.ui.StockWidgetProvider;

import timber.log.Timber;


public class QuoteIntentService extends IntentService {

    public QuoteIntentService() {
        super(QuoteIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timber.d("Intent handled");

        QuoteSyncJob.getQuotes(getApplicationContext());


    }
}