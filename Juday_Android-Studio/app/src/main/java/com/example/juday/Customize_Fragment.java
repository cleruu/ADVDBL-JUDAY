package com.example.juday;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Customize_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Customize_Fragment extends Fragment {
    private static String paintColor = "";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int[][] carModelTime = new int[][] { {8, 5000}, {9, 10000}, {11, 8000}};
    private static final int[][] range = new int[][] { {4, 100}, {4, 200}, {4, 300} };
    private static final int[][] dTrainTime = new int[][] { {5, 1000}, {7, 500}};
    private static final int[][] paintTime = new int[][] { {4, 50}, {4, 50}, {4, 80}};;
    private static final int[][] wSizeTime = new int[][] { {2, 200}, {2, 300}, {2, 400}};;
    private static final int[][] chargingTime = new int[][] { {1, 500}, {1, 200}};;

    public static String carModelID = "";
    public static String rangeID = "";
    public static String dTrainID = "";
    public static String paintID = "";
    public static String wSizeID = "";
    public static String chargingID = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Customize_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Customize_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Customize_Fragment newInstance(String param1, String param2) {
        Customize_Fragment fragment = new Customize_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_customize_, container, false);

        Spinner popCombSpin = inf.findViewById(R.id.custom_spinner_POPCOMBO);
        Button profBtn = (Button) inf.findViewById(R.id.customize_profile_button);
        profBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        popCombSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);

                if (item != null && !item.isEmpty()) {
                    if (item.equals("Speed")) {

                        Spinner dtrainSpin = inf.findViewById(R.id.custom_spinner_DRIVETRAIN);
                        dtrainSpin.setSelection(0);

                        Spinner rangeSpin = inf.findViewById(R.id.custom_spinner_RANGE);
                        rangeSpin.setSelection(0);

                        Spinner wSizeSpin = inf.findViewById(R.id.custom_spinner_WHEELSIZE);
                        rangeSpin.setSelection(0);
                    }
                    else if (item.equals("Comfort")) {

                        Spinner dtrainSpin = inf.findViewById(R.id.custom_spinner_DRIVETRAIN);
                        dtrainSpin.setSelection(1);

                        Spinner rangeSpin = inf.findViewById(R.id.custom_spinner_RANGE);
                        rangeSpin.setSelection(1);

                        Spinner wSizeSpin = inf.findViewById(R.id.custom_spinner_WHEELSIZE);
                        rangeSpin.setSelection(1);
                    }
                    else if (item.equals("Reliable")) {

                        Spinner dtrainSpin = inf.findViewById(R.id.custom_spinner_DRIVETRAIN);
                        dtrainSpin.setSelection(0);

                        Spinner rangeSpin = inf.findViewById(R.id.custom_spinner_RANGE);
                        rangeSpin.setSelection(2);

                        Spinner wSizeSpin = inf.findViewById(R.id.custom_spinner_WHEELSIZE);
                        rangeSpin.setSelection(2);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String popularCombo = popCombSpin.getSelectedItem().toString();

        Spinner carModelSpin = inf.findViewById(R.id.custom_spinner_CarModel);
        String carModel = carModelSpin.getSelectedItem().toString();

        Spinner dtrainSpin = inf.findViewById(R.id.custom_spinner_DRIVETRAIN);
        String driveTrain = dtrainSpin.getSelectedItem().toString();

        Spinner rangeSpin = inf.findViewById(R.id.custom_spinner_RANGE);
        String range = rangeSpin.getSelectedItem().toString();

        Spinner wSizeSpin = inf.findViewById(R.id.custom_spinner_WHEELSIZE);
        String wheelSize = wSizeSpin.getSelectedItem().toString();

        Spinner chargingSpin = inf.findViewById(R.id.custom_spinner_CHARGING);
        String charging = chargingSpin.getSelectedItem().toString();

        Button btn = inf.findViewById(R.id.custom_button_Calcualate);

        Chip c1 = inf.findViewById(R.id.custom_chip);
        Chip c2 = inf.findViewById(R.id.custom_chip2);
        Chip c3 = inf.findViewById(R.id.custom_chip3);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintColor = "white";
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintColor = "black";
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintColor = "red";
            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paintColor.isEmpty() || carModel.isEmpty() || driveTrain.isEmpty() ||
                        range.isEmpty() || wheelSize.isEmpty() || charging.isEmpty()) {
                    Snackbar.make(view, carModel + " " + driveTrain + " " + range + " " +wheelSize + " " + charging + " " + paintColor, Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.custom_button_Calcualate)
                            .setAction("Action", null).show();
                    return;
                }
                String[] orders = new String[] { carModel, range, charging, driveTrain,  wheelSize, paintColor};
                ConfirmOrderFragment nextFrag = new ConfirmOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("orders", orders);
                bundle.putIntArray("Car Model", carModelCalc(carModel));
                bundle.putIntArray("Range", dTrainCalc(driveTrain));
                bundle.putIntArray("Drivetrain", rangeCalc(range));
                bundle.putIntArray("paint", paintCalc(paintColor));
                bundle.putIntArray("Wheel Size", wSizeCalc(wheelSize));
                bundle.putIntArray("Charging", chargingCalc(charging));
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "customize_fragment")
                        .addToBackStack(null)
                        .commit();
//                    String queryBuild = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
//                            "`end_time`) VALUES (?,?,?,?,?)";

            }
        });




        return inf;
    }

    private int[] carModelCalc(String s) {
        if (s.equals("X")) {
            carModelID = "cmodel-0";
            return carModelTime[0];
        }
        else if (s.equals("Y")) {
            carModelID = "cmodel-1";
            return carModelTime[1];
        }
        else{
            carModelID = "cmodel-2";
            return carModelTime[2];
        }
    }

    private int[] dTrainCalc(String s) {
        if (s.equals("All-wheel")) {
            dTrainID = "dtrain-0";
            return carModelTime[0];
        }
        else{
            dTrainID = "dtrain-1";
            return carModelTime[1];
        }
    }

    private int[] paintCalc(String s) {
        if (s.equals("white")) {
            paintID = "paint-0";
            return carModelTime[0];
        }
        else if (s.equals("black")) {
            paintID = "paint-1";
            return carModelTime[1];
        }
        else{
            paintID = "paint-2";
            return carModelTime[2];
        }
    }

    private int[] wSizeCalc(String s) {
        if (s.equals("18~")) {
            wSizeID = "wsize-0";
            return carModelTime[0];
        }
        else if (s.equals("19~")) {
            wSizeID = "wsize-1";
            return carModelTime[1];
        }
        else{
            wSizeID = "wsize-2";
            return carModelTime[2];
        }
    }

    private int[] rangeCalc(String s) {
        if (s.equals("300mi")) {
            rangeID = "range-0";
            return carModelTime[0];
        }
        else if (s.equals("400mi")) {
            rangeID = "range-1";
            return carModelTime[1];
        }
        else{
            rangeID = "range-2";
            return carModelTime[2];
        }
    }

    private int[] chargingCalc(String s) {
        if (s.equals("Wall")) {
            chargingID = "charge-0";
            return carModelTime[0];
        }
        else{
            chargingID = "charge-1";
            return carModelTime[1];
        }
    }

//    private void replaceFragment(Fragment fragment) {
//        NextFragment nextFrag= new NextFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.Layout_container, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit();
//    }
}