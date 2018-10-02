package com.example.layout_module;


public enum  Model
{
    Electricity(R.layout.bills1),
    Maintenance(R.layout.bills2),
    Water(R.layout.bills3),
    Rent(R.layout.bills4);


    private int mLayoutResId;

    Model(int layoutResId) {
        mLayoutResId = layoutResId;
    }


    public int getLayoutResId() {
        return mLayoutResId;
    }


}

