package com.example.juday;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

//    Connection conn = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tv = (TextView) inf.findViewById(R.id.static_Username_Label);

        EditText etUser = (EditText) inf.findViewById(R.id.textfield_Username);
        EditText etPass = (EditText) inf.findViewById(R.id.textfield_Password);

        tv.setText("Username");
        Button btn = (Button) inf.findViewById(R.id.button_Login);

        btn.setOnClickListener(new View.OnClickListener() {
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
                        String username = String.valueOf(etUser.getText());
                        String password = String.valueOf(etPass.getText());

                        if (username.isEmpty()) {
                            Snackbar.make(view, "Username cannot be empty", Snackbar.LENGTH_LONG)
                                    .setAnchorView(R.id.button_Login)
                                    .setAction("Action", null).show();
                            return;
                        }

                        String clearanceLvl = "";
                        String query = "SELECT * FROM credentials WHERE username=?";

                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, username);

                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            String dbUsername = rs.getString("username");
                            String dbPassword = rs.getString("password");
                            boolean checker = dbPassword.equals(password);

                            if (checker) {
                                clearanceLvl = rs.getString("clearnace_lvl");

                                Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                                intent.putExtra("clearance_lvl", clearanceLvl);
                                getActivity().startActivity(intent);
                            }
                            else {
                                Snackbar.make(view, "Wrong Password", Snackbar.LENGTH_LONG)
                                        .setAnchorView(R.id.button_Login)
                                        .setAction("Action", null).show();
                            }
                        }
                        con.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return inf;
    }
}