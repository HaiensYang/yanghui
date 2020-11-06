package com.fr.plugin;

import com.fr.stable.fun.impl.AbstractFunctionDefContainer;
import com.fr.stable.script.FunctionDef;

public class FinanceGroup extends AbstractFunctionDefContainer {
    @Override
    public FunctionDef[] getFunctionDefs() {
        return new FunctionDef[]{
                new FunctionDef("IPMT","基于固定利率及等额分期付款方式，返回给定期数内对投资的利息偿还额函数IPMT：参数1为每期期利率，参数2为用于计算其利息数额的期数，参数3为总期数，参数4现值pv，参数5未来值fv，参数6付款时间是在期初还是期末，前四个参数必填，后面两个选填。",IPMT.class.getName()),
                new FunctionDef("PPMT","PPMT函数返回根据定期固定付款和固定利率而定的投资在已知期间内的本金偿付额：参数1为每期利率，参数2为期数，参数3为总期数，参数4现值pv，参数5为未来值fv,参数6付款时间是在期初还是期末，前四个参数必填，后面两个选填。",PPMT.class.getName()),
                new FunctionDef("PMT","PMT函数根据固定付款额和固定利率计算贷款的付款额：参数1为每期利率，参数2为贷款总期数，参数3现值pv即本金，参数4为未来值fv，参数5为付款时间是在期初还是期末，前三个参数必填，后面两个选填。",PMT.class.getName()),
                new FunctionDef("PV","投资的现值PV：参数1为每期利率，参数2为总期数，参数3为每期付款金额，参数4未来值fv，参数5付款时间是在期初还是期末，前三个参数必填，后面两个选填。",PV.class.getName()),
                new FunctionDef("FV","计算投资价值的函数FV：参数1为利率，参数2为总期数，参数3为每期付款金额，参数4现值pv，参数5每期付款时间是在期初还是期末，前三个参数必填，后面两个选填。",FV.class.getName()),
                new FunctionDef("NPV","使用贴现率和一系列未来支出（负值）和收益（正值）来计算一项投资的净现值NPV：参数1为利率，可变参数-现金流，按正确的顺序输入支出值和收益值。",NPV.class.getName()),
                new FunctionDef("NPER","基于固定利率及等额分期付款方式，返回某项投资的总期数NPER：参数1为各期利率，参数2为每期付款金额，参数3为现值pv，参数4未来值fv，参数5付款时间是在期初还是期末，前三个参数必填，后面两个选填。",NPER.class.getName()),
                new FunctionDef("IRR","IRR函数返回由值中的数字表示的一系列现金流的内部收益率：参数1为现金流数组，参数2为估计值，参数2为选填，如果省略guess，则假定它为0.1。",IRR.class.getName()),
                new FunctionDef("XIRR","XIRR函数返回一组不一定定期发生的现金流的内部收益率：参数1为现金流数组，参数2为对应还款日期列表，参数3估计值为选填。",XIRR.class.getName()),
                new FunctionDef("Rate","RATE函数返回年金每期的利率：参数1为贷款总期数，参数2为每期付款金额，参数3为付款金额的总和，参数4为未来值fv，参数5为type付款时间是在期初还是期末，参数6为预期利率，省略的话默认为0.1，前三个参数必填，后三个选填。",Rate.class.getName()),
                new FunctionDef("MIRR","MIRR函数返回一系列定期现金流的修改后内部收益率，同时考虑投资的成本和现金再投资的收益率：参数1为现金流数组，参数2为资金支付利率，参数3为再投资收益率。",MIRR.class.getName())

        };
    }

    @Override
    public String getGroupName() {
        return "财务函数";
    }
}
