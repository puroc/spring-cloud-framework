package com.emrubik.springcloud.job;

import com.emrubik.springcloud.dao.entity.DaBasicFlowT;
import com.emrubik.springcloud.service.IDaBasicFlowTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DaStatJob implements CommandLineRunner {

    @Autowired
    private IDaBasicFlowTService iDaBasicFlowTService;

    private void makeFlowData(){
        DaBasicFlowT flow = new DaBasicFlowT();
        flow.setCmCode("123");
        flow.setInsNum(BigDecimal.valueOf(1));
        flow.setAgainstAccNum(BigDecimal.valueOf(1));
        flow.setRightAccNum(BigDecimal.valueOf(1));
        iDaBasicFlowTService.insert(flow);
    }

    @Override
    public void run(String... strings) throws Exception {
        makeFlowData();
    }
}
