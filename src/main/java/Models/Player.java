package Models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String d_name;
    Integer d_unallocatedArmies;
    List<Country> d_currentCountries;
    List<Continent> d_currentContinents;
    List<Orders> d_orders;

    public Player(String p_name){
        this.d_name = p_name;
        this.d_unallocatedArmies = 0;
        this.d_orders = new ArrayList<Orders>();
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String p_name) {
        this.d_name = p_name;
    }

    public Integer getD_unallocatedArmies() {
        return d_unallocatedArmies;
    }

    public void setD_unallocatedArmies(Integer p_unallocatedArmies) {
        this.d_unallocatedArmies = p_unallocatedArmies;
    }

    public List<Country> getD_currentCountries() {
        return d_currentCountries;
    }

    public void setD_currentCountries(List<Country> p_currentCountries) {
        this.d_currentCountries = p_currentCountries;
    }

    public List<Continent> getD_currentContinents() {
        return d_currentContinents;
    }

    public void setD_currentContinents(List<Continent> p_currentContinents) {
        this.d_currentContinents = p_currentContinents;
    }

    public List<Orders> getD_orders() {
        return d_orders;
    }

    public void setD_orders(List<Orders> p_orders) {
        this.d_orders = p_orders;
    }

}