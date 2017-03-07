package com.supplier.service.impl;

import com.college.dao.OrderBookDao;
import com.college.dao.TakesDao;
import com.entity.custom.OrderBookReviewVo;
import com.entity.custom.OrderBookVo;
import com.entity.custom.ReviewedBookVo;
import com.supplier.service.SupplierBiz;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Service
public class SupplierBizImpl implements SupplierBiz {

    @Resource
    private OrderBookDao orderBookDao;


    //TODO 这个代码偷懒了！！！！！要多烂有多烂直接拷贝的。。
    public List<ReviewedBookVo> findAllReviewedBook() {
        List<ReviewedBookVo> reviewedBookVoList = orderBookDao.findAllReviewedBook();
        return reviewedBookVoList;
    }

}
