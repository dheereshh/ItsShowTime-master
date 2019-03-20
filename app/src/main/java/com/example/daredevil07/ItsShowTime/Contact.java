package com.example.daredevil07.ItsShowTime;


public class Contact {

    private String contact;
    private String route;
    private String adress;
    private String openingstijden;

    public Contact(String contact, String route, String adress, String openingstijden) {
        this.contact = contact;
        this.route = route;
        this.adress = adress;
        this.openingstijden = openingstijden;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAdres() {
        return adress;
    }

    public void setAdres(String adres) {
        this.adress = adres;
    }

    public String getOpeningstijden() {
        return openingstijden;
    }

    public void setOpeningstijden(String openingstijden) {
        this.openingstijden = openingstijden;
    }
}
