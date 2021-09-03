package com.example.appbookroom.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appbookroom.R;
import com.example.appbookroom.adapters.PeopleAdapter;
import com.example.appbookroom.databinding.DialogInsertPeopleBinding;
import com.example.appbookroom.databinding.DialogLoginBinding;
import com.example.appbookroom.databinding.DialogTimepickerBinding;
import com.example.appbookroom.entity.Employee;
import com.example.appbookroom.view.CustomTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;


public class BlurAlertDialogFragment extends SupportBlurDialogFragment {
    private String nameDialog;
    private OnBlurAlertDialogListener mListener;
    private PeopleAdapter peopleAdapter;
    ArrayList<Employee> employeeArrayList;


    public BlurAlertDialogFragment() {
    }

    public BlurAlertDialogFragment(String nameDialog, OnBlurAlertDialogListener listener) {
        this.nameDialog = nameDialog;
        this.mListener = listener;
    }

    private int mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (nameDialog) {
            case "showDialogChoseTime":
                showDialogChoseTime();
                break;
            case "showDialogStatusSuccess":
                showDialogStatusSuccess();
                break;
            case "showDialogInsertUser":
                showDialogInsertUser();
                break;
            case "showDialogStatusFall":
                showDialogStatusFall();
                break;
        }
    }

    private void showDialogChoseTime() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        DialogTimepickerBinding dialogTimepickerBinding = DialogTimepickerBinding.inflate(layoutInflater);
        builder.setView(dialogTimepickerBinding.getRoot());
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        dialogTimepickerBinding.edFrom.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            final Calendar datetime = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);

            CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(getContext(), (view, hourOfDay, minute) -> {

                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                datetime.set(Calendar.MINUTE, minute);

                if (datetime.getTimeInMillis() >= calendar.getTimeInMillis()) {

                    int hour = hourOfDay % 12;
                    dialogTimepickerBinding.edFrom.setText(String.format("%02d:%02d", hourOfDay, minute));

                } else {
                    Toast.makeText(getContext(), "Vui lòng chọn thời gian phù hợp", Toast.LENGTH_LONG).show();

                }
            }, mHour, mMinute, true);

            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        });
        dialogTimepickerBinding.edTo.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            final Calendar datetime = Calendar.getInstance();

            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);

            CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                datetime.set(Calendar.MINUTE, minute);
                if (datetime.getTimeInMillis() >= calendar.getTimeInMillis()) {
                    int hour = hourOfDay % 12;
                    dialogTimepickerBinding.edTo.setText(String.format("%02d:%02d", hourOfDay, minute));

                } else {
                    Toast.makeText(getContext(), "Vui lòng chọn thời gian phù hợp", Toast.LENGTH_LONG).show();
                }
            }, mHour, mMinute, true);
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        });
        dialogTimepickerBinding.btnOk.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onUpdated(dialogTimepickerBinding.edFrom.getText().toString(), dialogTimepickerBinding.edTo.getText().toString());
            }
            dismiss();
            alertDialog.dismiss();
        });
        dialogTimepickerBinding.imgClose.setOnClickListener(v -> {
            dismiss();
            alertDialog.dismiss();
        });
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        alertDialog.show();
    }

    private void showDialogStatusSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        DialogLoginBinding dialogLoginBinding = DialogLoginBinding.inflate(layoutInflater);
        builder.setView(dialogLoginBinding.getRoot());
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        dialogLoginBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });
        dialogLoginBinding.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });

        dialogLoginBinding.imgFall.setImageResource(R.drawable.success2);
        dialogLoginBinding.tv.setText(R.string.status_bookroom_success);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        alertDialog.show();
    }

    public void showDialogStatusFall() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        DialogLoginBinding dialogLoginBinding = DialogLoginBinding.inflate(layoutInflater);
        builder.setView(dialogLoginBinding.getRoot());
        AlertDialog alertDialog = builder.create();
        dialogLoginBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });
        dialogLoginBinding.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });
        dialogLoginBinding.tv.setText(R.string.status_bookroom_fall);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        alertDialog.show();
    }

    private void showDialogInsertUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        DialogInsertPeopleBinding dialogInsertPeopleBinding = DialogInsertPeopleBinding.inflate(layoutInflater);

        employeeArrayList = new ArrayList<>();
        employeeArrayList.add(new Employee(1,"Long","0389127389","tlong4856@gmail.com","260900",1,"AAA","Null","User"));
        employeeArrayList.add(new Employee(2,"Thảo","0586365567","nttt91212@gmail.com","090100",1,"BBB","Null","User"));
        employeeArrayList.add(new Employee(3,"Điền","0123456789","dein12365@gmail.com","123456",1,"CCC","Null","User"));
        employeeArrayList.add(new Employee(1,"Long","0389127389","tlong4856@gmail.com","260900",1,"AAA","Null","User"));
        employeeArrayList.add(new Employee(2,"Thảo","0586365567","nttt91212@gmail.com","090100",1,"BBB","Null","User"));
        employeeArrayList.add(new Employee(3,"Điền","0123456789","dein12365@gmail.com","123456",1,"CCC","Null","User"));
        employeeArrayList.add(new Employee(1,"Long","0389127389","tlong4856@gmail.com","260900",1,"AAA","Null","User"));
        employeeArrayList.add(new Employee(2,"Thảo","0586365567","nttt91212@gmail.com","090100",1,"BBB","Null","User"));
        employeeArrayList.add(new Employee(3,"Điền","0123456789","dein12365@gmail.com","123456",1,"CCC","Null","User"));
        peopleAdapter = new PeopleAdapter(employeeArrayList, getContext());

        builder.setView(dialogInsertPeopleBinding.getRoot());
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        dialogInsertPeopleBinding.recyclerView.setLayoutManager(linearLayoutManager);
        dialogInsertPeopleBinding.recyclerView.setAdapter(peopleAdapter);


        dialogInsertPeopleBinding.btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });
        dialogInsertPeopleBinding.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alertDialog.show();
    }

    @Override
    protected int getBlurRadius() {
        // Allow to customize the blur radius factor.
        return 1;
    }

    public interface OnBlurAlertDialogListener {
        void onUpdated(String from, String to);
    }
}