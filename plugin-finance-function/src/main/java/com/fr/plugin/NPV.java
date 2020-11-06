package com.fr.plugin;

import com.fr.general.GeneralUtils;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.script.AbstractFunction;
import com.fr.stable.StringUtils;
import com.fr.stable.exception.FormulaException;
import com.fr.stable.fun.Authorize;
import com.fr.third.v2.org.apache.poi.ss.formula.functions.FinanceLib;

import java.math.BigDecimal;
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class NPV extends AbstractFunction {
    @Override
    @Focus(id = "com.fr.plugin.function.finance", text = "Plugin-Test_Function_Finance", source = Original.PLUGIN)
    public Object run(Object[] objects) throws FormulaException {
        //某一期间的贴现率
        double rate = trans(objects[0]).doubleValue();

        double[] doubles = new double[objects.length];
        for (int i = 1; i <objects.length ; i++) {
            doubles[i-1]=(trans(objects[i]).doubleValue());
        }

        return FinanceLib.npv(rate,doubles);
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
}
