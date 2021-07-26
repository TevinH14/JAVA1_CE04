package com.example.hamiltontevince04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "funday";

    private ListView mListView = null;
    private GridView mGridView = null;
    private ArrayList<Person> mPersonArrayList = new ArrayList<>();

   private ArrayList<Integer> mImageViewArrayList = new ArrayList<>();

    private String[] namesArray = new String[10];

    private int viewSelection = 0;
    private int adapterSelection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreatePerson();

        //add Refence to the list and grid view for later use
        mListView = findViewById(R.id.mainListView);
        mGridView = findViewById(R.id.mainGridView);

        // set up spinners
        SetUpSpinners();
        SetArrayAdapter();

        //add click Listeners
        mListView.setOnItemClickListener(selectionClick);
        mGridView.setOnItemClickListener(selectionClick);

        mImageViewArrayList.add(R.drawable.bumblebee);
    }
    protected void SetUpBaseAdapter(){
        PersonAdapter pa = new PersonAdapter(this,mPersonArrayList);
        if(viewSelection == 0){
            mListView.setAdapter(pa);
            mListView.setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.GONE);
        }
        else if(viewSelection == 1){
            mGridView.setAdapter(pa);
            mListView.setVisibility(View.GONE);
            mGridView.setVisibility(View.VISIBLE);
        }
    }

    protected void SetupSimpleAdapterView() {
        Log.i(TAG, "setupAdapterView()");


        final String keyName = "userName";
        final String keyBirthDate = "birthdate";

        ArrayList<HashMap<String,String>> dataCollection = new ArrayList<>();

        for (int i = 0; i < mPersonArrayList.size(); i++){

            HashMap<String, String> map = new HashMap<>();


            map.put(keyName,mPersonArrayList.get(i).getFirstName()+" "+ mPersonArrayList.get(i).getLastName());
            map.put(keyBirthDate,mPersonArrayList.get(i).getBirthday());

            dataCollection.add(map);
        }
        String[] keys = new String[]{
                keyName,keyBirthDate
        };

        int[] viewIDs = new int[] {
                R.id.view_nameSample,R.id.view_dateSample
        };
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataCollection,R.layout.view_sampleadapter, keys,viewIDs);

        if (viewSelection == 0) {

            mListView.setAdapter(simpleAdapter);
            mListView.setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.GONE);
        }
        else if (viewSelection == 1){

            mGridView.setAdapter(simpleAdapter);
            mListView.setVisibility(View.GONE);
            mGridView.setVisibility(View.VISIBLE);
        }
    }

    private void SetArrayAdapter() {

        for (int i = 0; i <mPersonArrayList.size() ; i++) {
            namesArray[i] = mPersonArrayList.get(i).getFirstName()+" "+mPersonArrayList.get(i).getLastName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.listview_arrayadapter, R.id.fNameLName_TextView, namesArray);

        if (viewSelection == 0) {

              mListView.setAdapter(arrayAdapter);
              mListView.setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.GONE);
        }
        else{

            mGridView.setAdapter(arrayAdapter);
            mListView.setVisibility(View.GONE);
            mGridView.setVisibility(View.VISIBLE);
        }
    }

    private void SetUpViews(){
        if(adapterSelection == 0){
            SetArrayAdapter();
        }
        else if(adapterSelection == 1){
           SetupSimpleAdapterView();
        }
        else if(adapterSelection == 2){
            SetUpBaseAdapter();
        }
    }


    private AdapterView.OnItemClickListener selectionClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(mPersonArrayList.get(position).getFirstName() + " "+mPersonArrayList.get(position).getLastName());
            builder.setMessage(mPersonArrayList.get(position).getBirthday());
            builder.setIcon(mPersonArrayList.get(position).getUserImage());
            builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
    };


    private void SetUpSpinners(){
        Spinner mViewSpinner = null;
        Spinner mAdapterSpinner = null;

        ArrayList<String> viewString = new ArrayList<>();
        ArrayList<String> adapterString = new ArrayList<>();

        mAdapterSpinner = findViewById(R.id.adapter_spinner);
        mViewSpinner = findViewById(R.id.view_spinner);

        mViewSpinner.setOnItemSelectedListener(this);
        mAdapterSpinner.setOnItemSelectedListener(this);

        viewString.add(getString(R.string.listView));
        viewString.add(getString(R.string.gridView));
        adapterString.add(getString(R.string.array_adapter));
        adapterString.add(getString(R.string.sample_Adapter));
        adapterString.add(getString(R.string.base_Adapter));

        ArrayAdapter<String> viewSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,viewString);
        ArrayAdapter<String> adapterSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,adapterString);

        viewSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mAdapterSpinner.setAdapter(adapterSpinnerAdapter);
        mViewSpinner.setAdapter(viewSpinnerAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.adapter_spinner){
            switch (pos){
                case 0:
                    adapterSelection = 0;
                    SetArrayAdapter();
                    break;
                case 1:
                    adapterSelection = 1;
                    SetupSimpleAdapterView();
                    break;
                case 2:
                    adapterSelection = 2;
                    SetUpBaseAdapter();
                    break;
            }

        }
        else if(parent.getId() == R.id.view_spinner){
            switch (pos){
                case 0:
                    viewSelection = 0;
                    SetUpViews();
                    break;
                case 1:
                    viewSelection = 1;
                    SetUpViews();
                    break;
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void CreatePerson(){
        mPersonArrayList.add(new Person("Sabiha","Franco","10/10/1995",R.drawable.bumblebee));
        mPersonArrayList.add(new Person("Piers","Thorpe","11/11/1997",R.drawable.bullet));
        mPersonArrayList.add(new Person("Jak","Lam","1/15/1998",R.drawable.ezio));
        mPersonArrayList.add(new Person("Marcus","Bloom","12/2/2005",R.drawable.ironman));
        mPersonArrayList.add(new Person("Humza","Stein","12/26/2007",R.drawable.killzone));
        mPersonArrayList.add(new Person("Sameera","Peck","12/10/2008",R.drawable.masterchief));
        mPersonArrayList.add(new Person("Aamir","Phan","9/2/2009",R.drawable.predator));
        mPersonArrayList.add(new Person("Nikodem","Kane","12/2/2015",R.drawable.stromtrooper));
        mPersonArrayList.add(new Person("Mandeep","Oconnor","12/5/2016",R.drawable.spiderman));
        mPersonArrayList.add(new Person("Keiren","Broughton","9/14/2018",R.drawable.vemon));

    }
}
