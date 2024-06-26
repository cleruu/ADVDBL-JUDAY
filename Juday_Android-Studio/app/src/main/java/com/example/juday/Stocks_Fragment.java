package com.example.juday;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stocks_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stocks_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static ArrayList<Stock> stockList;
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Stocks_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stocks_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Stocks_Fragment newInstance(String param1, String param2) {
        Stocks_Fragment fragment = new Stocks_Fragment();
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
        View inf = inflater.inflate(R.layout.fragment_stocks_, container, false);
        Context ctx = getContext();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dbConnection cc = new dbConnection();
            Connection con = cc.conn();

            if (con == null) {
                System.out.println("Something is wrong");
            }
            else {
                String query = "SELECT * FROM `est_restock_time` INNER JOIN car_parts ON est_restock_time.part_ID=car_parts.part_ID";

                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                stockList = new ArrayList<>();
                while (rs.next()) {
                    String part_id = rs.getString("part_ID");
                    String partType = typeChecker(part_id);
                    String part = rs.getString("part_type");

                    int travelTime = rs.getInt("shipping_time");
                    stockList.add(new Stock(partType, "Estimated restock time: " + String.valueOf(travelTime), part));
                }
                recyclerView = inf.findViewById(R.id.stockRecycler);
                RecyclerAdapterStock adapter = new RecyclerAdapterStock(stockList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        return inf;
    }

    public String typeChecker(String s) {
        if (s.contains("charge")) {
            return "Charging";
        }
        else if (s.contains("cmodel")) {
            return "Car Model";
        }
        else if (s.contains("dtrain")) {
            return "Drivetrain";
        }
        else if (s.contains("paint")) {
            return "Paint";
        }
        else if (s.contains("range")) {
            return "Range";
        }
        else {
            return "Wheel Size";
        }
    }


}