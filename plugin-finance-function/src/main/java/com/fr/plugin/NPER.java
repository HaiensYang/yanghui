package com.fr.plugin;

import com.fr.general.GeneralUtils;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.plugin.context.PluginContexts;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.script.AbstractFunction;
import com.fr.stable.StringUtils;
import com.fr.stable.exception.FormulaException;
import com.fr.stable.fun.Authorize;
import com.fr.third.v2.org.apache.poi.ss.formula.functions.FinanceLib;

import java.math.BigDecimal;

@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class NPER extends AbstractFunction {
    @Override
    @Focus(id = "com.fr.plugin.function.finance", text = "Plugin-Test_Function_Finance", source = Original.PLUGIN)
    public Object run(Object[] objects) throws FormulaException {
        if (PluginContexts.currentContext().isAvailable()) {
            return cal(objects);
        } else {
            return "插件未激活，请购买使用";
        }
    }

    private Object cal(Object[] objects) {
        //某一期间的贴现率
        double rate = trans(objects[0]).doubleValue();

        //每期的付款金额
        double pmt= trans(objects[1]).doubleValue();
        //pv 可选
        double pv = trans(objects[2]).doubleValue();
        //fv 可选
        double fv = 0D;
        if(objects.length == 4) {
            fv = trans(objects[3]).doubleValue();
        }
        //type 可选
        boolean type=false;
        if(objects.length == 5) {
            fv = trans(objects[3]).doubleValue();
            type = trans(objects[4]).doubleValue()==1;
        }
        return FinanceLib.nper(rate,pmt,pv,fv,type);
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
