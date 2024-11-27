import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSON;

import java.util.List;

public class YourClass { // Replace YourClass with your actual class name

    // ... (other fields and methods from your class) ...

    public List<DayworkMemberSettleVo> selectDayworkMemberSettleForSupList(DayworkMemberSettle dayworkMemberSettle) {
        List<DayworkMemberSettleVo> settleList = dayworkMemberSettleMapper.selectDayworkMemberSettleList(dayworkMemberSettle);

        for (DayworkMemberSettleVo dayworkMemberSettleVo : settleList) {
            // Convert pay and brokerage to yuan (if not null)
            dayworkMemberSettleVo.setPay(convertFenToYuan(dayworkMemberSettleVo.getPay()));
            dayworkMemberSettleVo.setBrokerage(convertFenToYuan(dayworkMemberSettleVo.getBrokerage()));

            // Retrieve supplier info using remoteSysService
            RespResult respResult = remoteSysService.getInfoByuserIdBack(String.valueOf(dayworkMemberSettleVo.getSupplierUserId()),
                    String.valueOf(AccessContext.getTenantId()),
                    AccessControlConstant.INNER);
            if (respResult != null && respResult.get(RespResult.DATA_TAG) != null) {
                ObjectMapper mapper = new ObjectMapper();
                String data;
                try {
                    data = mapper.writeValueAsString(respResult.get(RespResult.DATA_TAG));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                SysSupplierInfoRo sysSupplierInfoRo = JSON.parseObject(data, SysSupplierInfoRo.class);
                dayworkMemberSettleVo.setApplyRecUser(sysSupplierInfoRo.getName());

                // Ensure pay and brokerage values are not null (default to 0.0 if null)
                dayworkMemberSettleVo.setPay(dayworkMemberSettleVo.getPay() != null ? dayworkMemberSettleVo.getPay() : 0.0);
                dayworkMemberSettleVo.setBrokerage(dayworkMemberSettleVo.getBrokerage() != null ? dayworkMemberSettleVo.getBrokerage() : 0.0);
            }

            // Find the clocking status
            DayworkMemberClockin dayworkMemberClockin = new DayworkMemberClockin();
            dayworkMemberClockin.setUserIdEnd(dayworkMemberSettleVo.getUserIdEnd());
            dayworkMemberClockin.setProjectId(dayworkMemberSettleVo.getProjectId());

            // ... (Further processing for clocking status, assuming your code continues from here)

        }
        return settleList;
    }

    private Double convertFenToYuan(Double fenAmount) {
        if (fenAmount != null) {
            String yuanStr = MoneyUtil.fenToyuan(String.valueOf(fenAmount));
            return Double.parseDouble(yuanStr);
        }
        return null;
    }

    // ... (rest of your class methods) ...
}