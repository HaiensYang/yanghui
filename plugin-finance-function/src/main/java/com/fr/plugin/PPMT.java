package com.fr.plugin;

import com.fr.general.GeneralUtils;
import com.fr.intelli.record.Focus;
import com.fr.intelli.record.Original;
import com.fr.record.analyzer.EnableMetrics;
import com.fr.script.AbstractFunction;
import com.fr.stable.StringUtils;
import com.fr.stable.exception.FormulaException;
import com.fr.stable.fun.Authorize;
import com.fr.third.v2.org.apache.poi.ss.formula.functions.Finance;

import java.math.BigDecimal;
@EnableMetrics
@Authorize(callSignKey = FinanceFunctionConstants.PLUGIN_ID)
public class PPMT extends AbstractFunction {
    @Override
    @Focus(id = "com.fr.plugin.function.finance", text = "Plugin-Test_Function_Finance", source = Original.PLUGIN)
    public Object run(Object[] objects) throws FormulaException {
        // 各期利率 必需
        double rate = trans(objects[0]).doubleValue();
        // 指定期数 per 必需
        int per = trans(objects[1]).intValue();
        //年金的付款总期数 必需
        int nper = trans(objects[2]).intValue();
        //pv 必需 现值即一系列未来付款当前值的总和
        double pv = trans(objects[3]).doubleValue();

        if (objects.length == 4) {
            return Finance.ppmt(rate,per,nper,pv);
        }else if (objects.length == 5) {
            //未来值
            double fv = trans(objects[4]).doubleValue();
            return Finance.ppmt(rate,per,nper,pv,fv);
        }else if (objects.length == 6) {
            double fv = trans(objects[4]).doubleValue();
            int type= trans(objects[5]).intValue();
            return Finance.ppmt(rate,per,nper,pv,fv,type);
        }else {
            throw new RuntimeException("输入参数有误");
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
}
