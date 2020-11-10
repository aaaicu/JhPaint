package com.jaehyun.jhpaint;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PaintFragment extends Fragment {
    static PaintFragment paintFragment = null;

    private Context mContext = null;
    // 그리기버튼 ( 종료시 closeDrawFragment 의 파라미터로 넘기기위함)
    private int mButtonViewId = -1;

    private Button closeButton = null;
    public PaintFragment() {
        // Required empty public constructor
    }

    public static PaintFragment newInstance() {
        if (paintFragment == null) {
            paintFragment = new PaintFragment();
        }
        return paintFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mButtonViewId = getArguments().getInt("buttonId",-1);
        View view = inflater.inflate(R.layout.fragment_paint, container, false);

        // 그리기 닫기 버튼 클릭 구현
        closeButton =view.findViewById(R.id.buttonClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonViewId > 0 )
                    ((BaseActivity)mContext).closeDrawFragment(((BaseActivity) mContext).findViewById(mButtonViewId));
            }
        });
        return view;
    }
}
