package com.emrubik.springcloud.job;

import com.emrubik.springcloud.dao.entity.DaBasicFlowT;
import com.emrubik.springcloud.service.IDaBasicFlowTService;
import com.emrubik.springcloud.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DaStatJob implements CommandLineRunner {

    @Autowired
    private IDaBasicFlowTService iDaBasicFlowTService;

    private void makeFlowData(String cmCode, String startTime, int startRightAccNum, int startAgainstNum) throws ParseException {
        int totalNum = 2 * 24 * 7;
        int rightAccNum = startRightAccNum;
        int againstAccNum = startAgainstNum;
        for (int i = 0; i < totalNum; i++) {
            DaBasicFlowT flow = new DaBasicFlowT();
            flow.setCmCode(cmCode);

            //设置流量值
            flow.setInsNum(BigDecimal.valueOf(Util.getRandomValue(30)));
            rightAccNum += Util.getRandomValue(100);
            againstAccNum += Util.getRandomValue(10);
            if (againstAccNum > rightAccNum) {
                againstAccNum = rightAccNum;
            }
            flow.setRightAccNum(BigDecimal.valueOf(rightAccNum));
            flow.setAgainstAccNum(BigDecimal.valueOf(againstAccNum));

            //设置时间
            flow.setCreateTime(getNextTime(startTime, i));
            iDaBasicFlowTService.insert(flow);
        }

    }

    private Date getNextTime(String startTime, int index) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = sdf.parse(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MINUTE, 30 * index);
        return calendar.getTime();
    }

    @Override
    public void run(String... strings) throws Exception {
        makeFlowData("123", "2018-11-05 00:00:00", 0, 0);
        makeFlowData("456", "2018-11-05 00:00:00", 0, 0);
    }
}
