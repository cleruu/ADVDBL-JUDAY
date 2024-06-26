package com.example.juday;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ConfirmOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmOrderFragment newInstance(String param1, String param2) {
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ConfirmOrderFragment() {
        // Required empty public constructor
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
        View inf = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        int[] carModel = getArguments().getIntArray("Car Model");
        int[] driveTrain = getArguments().getIntArray("Drivetrain");
        int[] range = getArguments().getIntArray("Range");
        int[] paint = getArguments().getIntArray("paint");
        int[] wSize = getArguments().getIntArray("Wheel Size");
        int[] charging = getArguments().getIntArray("Charging");
        String[] orders = getArguments().getStringArray("orders");


        int totalCost = carModel[1] + driveTrain[1] + range[1] + paint[1] + wSize[1] + charging[1];
        int totalTime = carModel[0] + driveTrain[0] + range[0] + paint[0] + wSize[0] + charging[0];


        TextView orderTView = (TextView)inf.findViewById(R.id.confirm_dynamic_OrderNum);
        TextView costTView = (TextView)inf.findViewById(R.id.confirm_dynamic_Stocks);
        TextView releaseTView = (TextView)inf.findViewById(R.id.confirm_dynamic_EstRelease);

        String modifiedDate = dateAddDays(totalTime);
        orderTView.setText(String.valueOf(Home_Fragment.orderNum));
        costTView.setText(String.valueOf("$" + totalCost));
        releaseTView.setText(modifiedDate);

        Button confirmBtn = (Button) inf.findViewById(R.id.confirm_button_ConfirmOrder);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbConnection cc = new dbConnection();
                    Connection con = cc.conn();

                    if (con == null) {
                        Snackbar.make(view, "empty", Snackbar.LENGTH_LONG)
                                .setAnchorView(R.id.button_Login)
                                .setAction("Action", null).show();
                    }
                    else {
                        String highestID = "SELECT MAX(build_id) FROM `build_time`";
                        PreparedStatement ps1 = con.prepareStatement(highestID);
                        ResultSet rs = ps1.executeQuery();
                        int highestNum = 0;
                        if (rs.next()) {
                            highestNum = rs.getInt("MAX(build_id)");
                        }

                        String queryBuild = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps2 = con.prepareStatement(queryBuild);
                        ps2.setInt(1, highestNum);
                        ps2.setString(2, Customize_Fragment.carModelID);
                        ps2.setInt(3, carModel[0]);

                        String a = dateAddDays(carModel[0]);

                        ps2.setDate(4, java.sql.Date.valueOf(a));
                        ps2.execute();

                        String queryBuild1 = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps3 = con.prepareStatement(queryBuild1);
                        ps3.setInt(1, highestNum);
                        ps3.setString(2, Customize_Fragment.dTrainID);
                        ps3.setInt(3, driveTrain[0]);

                        String a1 = dateAddDays(driveTrain[0]);

                        ps3.setDate(4, java.sql.Date.valueOf(a1));
                        ps3.execute();

                        String queryBuild2 = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps4 = con.prepareStatement(queryBuild2);
                        ps4.setInt(1, highestNum);
                        ps4.setString(2, Customize_Fragment.rangeID);
                        ps4.setInt(3, range[0]);

                        String a2 = dateAddDays(range[0]);

                        ps4.setDate(4, java.sql.Date.valueOf(a2));
                        ps4.execute();

                        String queryBuild3 = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps5 = con.prepareStatement(queryBuild3);
                        ps5.setInt(1, highestNum);
                        ps5.setString(2, Customize_Fragment.wSizeID);
                        ps5.setInt(3, wSize[0]);

                        String a3 = dateAddDays(wSize[0]);

                        ps5.setDate(4, java.sql.Date.valueOf(a3));
                        ps5.execute();

                        String queryBuild4 = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps6 = con.prepareStatement(queryBuild4);
                        ps6.setInt(1, highestNum);
                        ps6.setString(2, Customize_Fragment.paintID);
                        ps6.setInt(3, paint[0]);

                        String a4 = dateAddDays(paint[0]);

                        ps6.setDate(4, java.sql.Date.valueOf(a4));
                        ps6.execute();

                        String queryBuild5 = "INSERT INTO `build_time`(`build_id`, `part_ID`, `duration`, " +
                                "`end_time`) VALUES (?,?,?,?)";

                        highestNum+=1;
                        PreparedStatement ps7 = con.prepareStatement(queryBuild5);
                        ps7.setInt(1, highestNum);
                        ps7.setString(2, Customize_Fragment.chargingID);
                        ps7.setInt(3, charging[0]);

                        String a5 = dateAddDays(charging[0]);

                        ps7.setDate(4, java.sql.Date.valueOf(a5));
                        ps7.execute();

                        Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                        getActivity().startActivity(intent);

                        // FOR stocks
                        updateMe(Customize_Fragment.carModelID, con);
                        updateMe(Customize_Fragment.rangeID, con);
                        updateMe(Customize_Fragment.paintID, con);
                        updateMe(Customize_Fragment.wSizeID, con);
                        updateMe(Customize_Fragment.chargingID, con);
                        updateMe(Customize_Fragment.dTrainID, con);

                        String highestOrderID = "SELECT MAX(order_ID) FROM `car_order`";
                        PreparedStatement psOrder = con.prepareStatement(highestOrderID);
                        ResultSet highestOrderRs = psOrder.executeQuery();
                        int highestOrder = 0;
                        if (highestOrderRs.next()) {
                            highestOrder = highestOrderRs.getInt("MAX(order_ID)");
                        }
                        highestOrder += 1;
                        // For car_order
                        String carOrder = "INSERT INTO `car_order`(`order_ID`, `total_build_time`, `car_model`, `car_range`, `charging`, `drivetrain`, `wheel_size`, `paint`) " +
                                "VALUES (?,?,?,?,?,?,?,?)";
                        PreparedStatement orderPS = con.prepareStatement(carOrder);

                        orderPS.setInt(1, highestOrder);

                        orderPS.setDate(2, java.sql.Date.valueOf(modifiedDate));
                        for (int i = 3; i < 9; i++) {
                            orderPS.setString(i, orders[i-3]);
                        }
                        orderPS.execute();

                        String query = "SELECT * FROM `car_parts` WHERE stock = 0";
                        PreparedStatement stockPS = con.prepareStatement(query);
                        ResultSet stockRs = stockPS.executeQuery();

                        String highesStockID = "SELECT MAX(restock_ID) FROM `est_restock_time`";
                        PreparedStatement stockOrder = con.prepareStatement(highesStockID);
                        ResultSet highestStockRs = stockOrder.executeQuery();
                        int highestStock = 0;
                        if (highestStockRs.next()) {
                            highestStock = highestStockRs.getInt("MAX(restock_ID)");
                        }
                        highestStock += 1;

                        String input = "INSERT INTO `est_restock_time`(`restock_ID`, `part_ID`," +
                                "`shipping_time`, `delivery_time`) VALUES (?,?,?,?)";
                        PreparedStatement inputPS = con.prepareStatement(carOrder);

                        while (stockRs.next()) {
                            String partID = rs.getString("part_ID");
                            int type = typeChecker(partID);
                            inputPS.setInt(1, highestStock);
                            inputPS.setString(2, partID);

                            if (type == -1 ) {
                                type = carModelCalc(rs.getString("part_type"));
                                inputPS.setInt(3, type);
                            } else {
                                inputPS.setInt(3, type);
                            }

                            String deliveryTime = dateAddDays(type);
                            inputPS.setDate(4, java.sql.Date.valueOf(deliveryTime));

                            highestStock += 1;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button cancelBtn = (Button) inf.findViewById(R.id.confirm_button_CancelOrder);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                intent.putExtra("clearance_lvl", "user");
                getActivity().startActivity(intent);
            }
        });
        return inf;
    }

    public static String dateAddDays(int dayToAdd) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendarIns = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(dateFormat.format(calendarIns.getTime())+" <- Current Date");

        calendarIns.add(Calendar.DAY_OF_MONTH, dayToAdd);
        String modifiedDate = dateFormat.format(calendarIns.getTime());
        return modifiedDate;
    }

    public void updateMe(String s, Connection con) {
        try {
            String stock  = "UPDATE `car_parts` SET `stock` = stock - '1' WHERE `car_parts`.`part_ID` =?";
            PreparedStatement ps = con.prepareStatement(stock);
            ps.setString(1,s);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int carModelCalc(String s) {
        if (s.contains("X")) {
            return 12;
        }
        else if (s.contains("Y")) {
            return 15;
        }
        else{
            return 18;
        }
    }

    public int typeChecker(String s) {
        if (s.contains("charge")) {
            return 7;
        }
        else if (s.contains("cmodel")) {
            return -1;
        }
        else if (s.contains("dtrain")) {
            return 4;
        }
        else if (s.contains("paint")) {
            return 2;
        }
        else if (s.contains("range")) {
            return 7;
        }
        else {
            return 5;
        }
    }

}