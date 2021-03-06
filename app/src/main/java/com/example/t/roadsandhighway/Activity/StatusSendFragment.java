//package com.example.t.roadsandhighway.Activity;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.t.roadsandhighway.ComWithServer;
//import com.example.t.roadsandhighway.GeoCode;
//import com.example.t.roadsandhighway.GooglePlacesAutocompleteAdapter;
//import com.example.t.roadsandhighway.R;
//import com.example.t.roadsandhighway.SingleShotLocationProvider;
//import com.google.android.gms.maps.model.LatLng;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class StatusSendFragment extends Fragment implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {
//
//    private EditText etNotes;
//    private AutoCompleteTextView etMyLocation;
//    private Spinner spnrTrafficLevel;
//    private Button btnSubmit;
//    private LatLng myLocation;
//    private GeoCode geoCode;
//    private static String TAG = "statusSend";
//    final List<String> categories = new ArrayList<String>();
//    ComWithServer comWithServer;
//    final Map<String, Object> values = new HashMap<String, Object>();
//
//
//    @Nullable
//    @Override
//    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v=inflater.inflate(R.layout.activity_status_send,container,false);
//
//        etMyLocation= (AutoCompleteTextView)v.findViewById(R.id.myLocation);
//        etNotes = (EditText) v.findViewById(R.id.etNotes);
//        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
//        spnrTrafficLevel = (Spinner) v.findViewById(R.id.spnrTrafficLevel);
//        etMyLocation.setAdapter(new GooglePlacesAutocompleteAdapter(getContext(),R.layout.list_item));
//        etMyLocation.setOnItemClickListener(this);
//        geoCode=new GeoCode(getContext());
//
//
//
//        init();
//
//
//        etMyLocation.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
//                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (event.getRawX() >= (etMyLocation.getRight() - etMyLocation.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        SingleShotLocationProvider.requestSingleUpdate(getContext(), new SingleShotLocationProvider.LocationCallback() {
//                            @Override
//                            public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
//                                Log.d("Location", location.toString());
//                                myLocation=new LatLng(location.getLat(),location.getLang());
//                                etMyLocation.setText("Your location");
//
//
//                            }
//                        });
//
//                        return true;
//                    }
//                }
//
//                return false;
//            }
//        });
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Delivered", Toast.LENGTH_SHORT).show();
//
//
//                if (comWithServer.isConnected()) {
//                    values.put("averageSpeed", null);
//                    values.put("trafficVolume", null);
//                    values.put("note", etNotes.getText().toString());
//                    values.put("filePath", null);
//
//                    values.put("latitude", myLocation.latitude);
//                    values.put("longitude",myLocation.longitude);
//
//                    values.put("address", "BUET");
//                    Log.d(TAG,values.toString());
//
//
//                    Object[] queryParams = {values};
//
//                    comWithServer.callFucntion("statuses.insertWithLatLong", queryParams);
//
//                }
//            }
//        });
//        return v;
//    }
//
//
//
//
//    public void onItemClick(AdapterView adapterView, View view, int position, long id) {
//
//        String str = (String) adapterView.getItemAtPosition(position);
//        geoCode.setPlace(str);
//        Log.d("check", geoCode.getURL());
////                Log.d("check", autoCompViewD.getText().toString());
//
//        geoCode.jsonReq(geoCode.getURL(), new GeoCode.CallBack() {
//            @Override
//            public void onSuccess(Double lat, Double lng) {
//                Log.d("Hello","world"+lat.toString()+ lng.toString());
//                myLocation=new LatLng(lat,lng);
//
//
//            }
//
//            @Override
//            public void onFail(String msg) {
//
//            }
//        });
//        //Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//        values.put("level", categories.get(position));
//        Log.v(TAG, "" + position);
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
//
//    private void init() {
//
//        comWithServer = new ComWithServer(getContext());
//
//
//
//        // Spinner Drop down elements
//        categories.add("TJ-1");
//        categories.add("TJ-2");
//        categories.add("TJ-3");
//        categories.add("TJ-4");
//        categories.add("TJ-5");
//        categories.add("TJ-6");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spnrTrafficLevel.setAdapter(dataAdapter);
//
//
//
//        spnrTrafficLevel.setOnItemSelectedListener(this);
//
//    }
//
//
//}
