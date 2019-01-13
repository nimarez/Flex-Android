package org.avinalabs.flex.participants;

import android.support.design.widget.TabItem;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.quemb.qmbform.CellViewFactory;
import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import org.avinalabs.flex.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PCreateProfileActivity extends AppCompatActivity {

    public static final String TAG = "PCPLog";
    private Spinner race_spinner;
    private Spinner gender_spinner;
    private Spinner sex_spinner;
    private Spinner ed_spinner;
    private Spinner int_spinner;
    private Spinner ath_spinner;
    private ArrayAdapter<CharSequence> race_adapter;
    private ArrayAdapter<CharSequence> gender_adapter;
    private ArrayAdapter<CharSequence> sex_adapter;
    private ArrayAdapter<CharSequence> ed_adapter;
    private ArrayAdapter<CharSequence> int_adapter;
    private ArrayAdapter<CharSequence> ath_adapter;


    private static ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcreate_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.submitBar);
        setSupportActionBar(myToolbar);

        race_spinner = (Spinner) findViewById(R.id.race_spinner);
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        sex_spinner = (Spinner) findViewById(R.id.sex_spinner);
        ed_spinner = (Spinner) findViewById(R.id.ed_spinner);
        int_spinner = (Spinner) findViewById(R.id.int_spinner);
        ath_spinner = (Spinner) findViewById(R.id.ath_spinner);

        makeAdapter(race_spinner, R.array.race_array, race_adapter);
        makeAdapter(gender_spinner, R.array.gender_array, race_adapter);
        makeAdapter(sex_spinner, R.array.sex_array, race_adapter);
        makeAdapter(ed_spinner, R.array.ed_array, race_adapter);
        makeAdapter(int_spinner, R.array.int_array, race_adapter);
        makeAdapter(ath_spinner, R.array.ath_array, race_adapter);

    }

    private void makeAdapter(Spinner spinner, int array_resource, ArrayAdapter<CharSequence> adapter) {
        adapter = ArrayAdapter.createFromResource(this,
                array_resource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
