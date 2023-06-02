package com.example.sharp_salon;

public class dataholder {
    String phone,radio,total;

    public dataholder( String phone, String radio, String total) {

        this.phone = phone;
        this.radio = radio;
        this.total = total;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
