package com.fnm.ecodata.map;


import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.fnm.ecodata.R;
import com.fnm.ecodata.Utils.API;
import com.fnm.ecodata.Utils.SpinnerPopulatorAdapter;

/**
 * Created by awais on 18/11/2016.
 */

// This class will be used show popup dialog to user to apply search filters
public class SearchDialogFragment extends DialogFragment {
    Button back_button;
    TextView submit_button;
    EditText description;
    EditText remarks;
    String status_result = "";
    private ProgressDialog progressDialog;
    View rootView;
    private ViewSwitcher switcher;
    Bundle bundle;
    String snack_msg;
    private CheckBox checkBox;
    MapDialogActivityNotifier mapDialogActivityNotifier;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map_search_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        final Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        progressDialog = new ProgressDialog(getActivity());
        switcher = (ViewSwitcher) rootView.findViewById(R.id.viewSwitcher);
        firstView();
        return rootView;
    }

    public void setOnSubmitClickListener(MapDialogActivityNotifier listener) {
        mapDialogActivityNotifier = listener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void firstView() {
        TextView header = (TextView) rootView.findViewById(R.id.header);
        description = (EditText) rootView.findViewById(R.id.addline1);
        remarks = (EditText) rootView.findViewById(R.id.addline2);
        checkBox  = (CheckBox) rootView.findViewById(R.id.checkbox);
        final Spinner stateSP = (Spinner) rootView.findViewById(R.id.spinner_states);
        final String fragTag = getTag();
        if (fragTag.equals("SearchText")) {
            header.setText("Search by Text");
            description.setHint("Text...");
            remarks.setVisibility(View.GONE);
            final String[] arrayReferee = getResources().getStringArray(R.array.category_array);
            SpinnerPopulatorAdapter refereeSpinner = new SpinnerPopulatorAdapter(getActivity(), arrayReferee);
            stateSP.setAdapter(refereeSpinner);
            stateSP.setVisibility(View.GONE);
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        stateSP.setVisibility(View.VISIBLE);
                    } else {
                        stateSP.setVisibility(View.GONE);
                    }
                }
            });
        } else if (fragTag.equals("SearchStates")) {
            header.setText("Search by States & Territories");
            description.setHint("State");
            description.setVisibility(View.GONE);
            remarks.setVisibility(View.GONE);
            remarks.setHint("Territory");
            final String[] arrayReferee = getResources().getStringArray(R.array.states_array);
            SpinnerPopulatorAdapter refereeSpinner = new SpinnerPopulatorAdapter(getActivity(), arrayReferee);
            stateSP.setAdapter(refereeSpinner);
            stateSP.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
        }

        back_button = (Button) rootView.findViewById(R.id.backbutton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        submit_button = (TextView) rootView.findViewById(R.id.nextbutton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (fragTag.equals("SearchText")) {
                        if (!description.getText().toString().isEmpty()) {
                            if(!checkBox.isChecked()) {
                                dismiss();
                                mapDialogActivityNotifier.searchByText(description.getText().toString());
                            } else if(checkBox.isChecked()) {
                                int idx = stateSP.getSelectedItemPosition();
                                if(idx > 0) {
                                    dismiss();
                                    String query = (String) stateSP.getSelectedItem();
                                    String preURL = getPreAdvanceURL(query);
                                    String url = API.advanceSearch(preURL, description.getText().toString());
                                    mapDialogActivityNotifier.searchByTextAdvance(url);
                                }
                            }
                        }
                    } else if (fragTag.equals("SearchStates")) {
                        int idx = stateSP.getSelectedItemPosition();
                        if(idx > 0) {
                            dismiss();
                            String query = (String) stateSP.getSelectedItem();
                            mapDialogActivityNotifier.searchByState(query);
                        }

                    }
            }
        });
    }

    private String getPreAdvanceURL(String category) {
        String url = "";
        if(category.equalsIgnoreCase(getString(R.string.cat_plant_animal))) {
            url = API.getPlantAndAnimalMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_vegitation))) {
            url = API.getVegetationMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_t_eco_system))) {
            url = API.getTerrestrialMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_eco_logical))) {

        } else if(category.equalsIgnoreCase(getString(R.string.cat_fresh_water))) {
            url = API.getFreshWaterMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_soil))) {
            url = API.getLandsurfaceAndSoilMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_agriculture))) {
            url = API.getAgricultureMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_ocean))) {
            url = API.getOceanCostMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_climate))) {
            url = API.getClimateMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_human_interaction))) {
            url = API.getHumanNatureMarkerInfo();
        } else if(category.equalsIgnoreCase(getString(R.string.cat_energy))) {
            url = API.getEnergyWaterGasMarkerInfo();
        }

        return url;
    }

    interface MapDialogActivityNotifier {
        public void searchByText(String query);
        public void searchByTextAdvance(String query);
        public void searchByState(String query);
    }

}
