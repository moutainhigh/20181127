package com.yeepay.g3.core.ymf.service.impl.order;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.core.ymf.junit.common.SpringPrintTest;
import com.yeepay.g3.core.ymf.service.order.SyncOrderService;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
public class SyncOrderServiceImplTest extends AnnotationContextAwareTest {

    @Test
    public void countSyncOrder() throws Exception {
        List<String> customerNumbers = new ArrayList<String>();
        customerNumbers.add("10040011560");
        customerNumbers.add("10040007800");
        Date from = df.parse("2016-08-01 00:00:00");
        Date to = df.parse("2017-02-24 00:00:00");
        CountResponse response = syncOrderService.countSyncOrder(customerNumbers, from, to);
        deepPrint(response);
    }

    @Autowired
    SyncOrderService syncOrderService;

    @Test
    public void queryOrder() throws Exception {
        List<String> customerNumbers = new ArrayList<String>();
        customerNumbers.add("10040011560");
        customerNumbers.add("10040007800");
        Date from = df.parse("2016-08-01 00:00:00");
        Date to = df.parse("2017-02-24 00:00:00");
        List<SyncOrderDTO> orderDTOList = syncOrderService.queryOrder(customerNumbers, from, to);
        deepPrint(orderDTOList);
    }

    @Test
    public void queryOrder2() throws Exception {
        List<String> customerNumbers = new ArrayList<String>();
        customerNumbers.add("10040040183");
        customerNumbers.add("10040007800");
        Date from = df.parse("2017-03-06 00:00:00");
        Date to = df.parse("2017-03-30 00:00:00");
        List<SyncOrderDTO> orderDTOList = syncOrderService.queryOrder(customerNumbers, from, to);
        deepPrint(orderDTOList);
    }


}