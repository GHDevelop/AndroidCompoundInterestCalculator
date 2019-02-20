/**************************************************************************************************
                This class performs the actual calculations for the compound interest
 **************************************************************************************************/
package com.example.garrett.myapplication;

import android.annotation.TargetApi;
import android.icu.text.DecimalFormat;

/**
 * Created by Garrett on 2/19/2019.
 */

public class Calculator {
    private Double principal;
    private Double interest;
    private Integer compounds;
    private Double time;


    /***********************************************************************************************
                                            Getters and Setters
     **********************************************************************************************/
    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = RoundToTwoDecimalPlaces(principal);
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getCompounds() {
        return compounds;
    }

    public void setCompounds(Integer compounds) {
        this.compounds = compounds;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    /**
     * Special getter.
     * This getter calculates the compound interest and returns it
     * @return
     */
    public Double getAmount() {
        if (AbleToCalculateAmount())
        {
            return CalculateCompoundInterest();
        }
        else
        {
            return null;
        }
    }
    /***********************************************************************************************
                                    End of Getters and Setters
     **********************************************************************************************/

    /**
     * Determines if all necessary variables for calculation are initialized
     * @return
     */
    private boolean AbleToCalculateAmount(){
        return (getPrincipal() != null
                && getInterest() != null
                && getCompounds() != null
                && getTime() != null);
    }

    /**
     * Calculates the compound interest using the formula A = P(1+r/n)^nt
     * Note: intended to be used with getAmount, which calls AbleToCalculateAmount first
     * @return
     */
    private Double CalculateCompoundInterest(){
        Double compoundInterest = principal * Math.pow((1 + interest/compounds), (compounds * time));
        return compoundInterest;
    }

    /**
     * Rounds a double to 2 decimal places
     * Used primarily to limit Principal to actual money
     * @param input
     * @return
     */
    @TargetApi(24)
    private Double RoundToTwoDecimalPlaces(Double input) {
        if (input == null)
        {
            return null;
        }
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.valueOf(format.format(input));
    }
}
