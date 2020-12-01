package com.fr.plugin;

import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.script.AbstractFunction;
import com.fr.stable.StringUtils;
import com.fr.stable.exception.FormulaException;
import com.fr.stable.fun.Authorize;

import java.math.BigDecimal;
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class MIRR extends AbstractFunction {
    @Override
    @Focus(id = "com.fr.plugin.function.finance", text = "Plugin-Test_Function_Finance", source = Original.PLUGIN)
    public Object run(Object[] objects) throws FormulaException {

        //资金支付利率
        double financeRate;
        double reinvestRate;
        //再投资收益率
        if (objects.length < 3) {
            throw new RuntimeException("输入参数个数有误");
        } else if (objects.length == 3) {
            FArray in = (FArray) objects[0];
            //现金流
            double[] cashIn = new double[in.length()];
            for(int i=0;i<in.length();i++){
                Object ele = in.elementAt(i);
                if(ele == null|| StringUtils.isBlank(ele.toString())){
                    continue;
                }
                cashIn[i] = trans(ele).doubleValue();
            }
            financeRate = trans(objects[2]).doubleValue();
            reinvestRate = trans(objects[1]).doubleValue();
            return mirr(cashIn, financeRate, reinvestRate);
        }else {
            double[] cashIn = new double[objects.length - 2];
            for (int i = 0; i < objects.length - 2; i++) {
                if (objects[i] == null || StringUtils.isBlank(objects[i].toString())) {
                    continue;
                }
                cashIn[i] = trans(objects[i]).doubleValue();
            }
            financeRate = trans(objects[objects.length - 1]).doubleValue();
            reinvestRate = trans(objects[objects.length - 2]).doubleValue();

            return mirr(cashIn, financeRate, reinvestRate);
        }
    }

    private static BigDecimal trans(Object ele){
        if (ele == null|| StringUtils.isBlank(ele.toString())) {
            return new BigDecimal("0");
        }
        try{
            String val = GeneralUtils.objectToString(ele);
            return new BigDecimal(val.trim());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private double mirr(double[] in, double financeRate, double reinvestRate) {
        double value = 0.0D;
        int numOfYears = in.length - 1;
        double pv = 0.0D;
        double fv = 0.0D;
        int indexN = 0;
        double[] var13 = in;
        int var14 = in.length;

        double anIn;
        int var15;
        for(var15 = 0; var15 < var14; ++var15) {
            anIn = var13[var15];
            if (anIn < 0.0D) {
                pv += anIn / Math.pow(1.0D + financeRate + reinvestRate, (double)(indexN++));
            }
        }

        var13 = in;
        var14 = in.length;

        for(var15 = 0; var15 < var14; ++var15) {
            anIn = var13[var15];
            if (anIn > 0.0D) {
                fv += anIn * Math.pow(1.0D + financeRate, (double)(numOfYears - indexN++));
            }
        }

        if (fv != 0.0D && pv != 0.0D) {
            value = Math.pow(-fv / pv, 1.0D / (double)numOfYears) - 1.0D;
        }

        return value;
    }

}
