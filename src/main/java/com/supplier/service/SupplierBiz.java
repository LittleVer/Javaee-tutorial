package com.supplier.service;

import com.entity.custom.ReviewedBookVo;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
public interface SupplierBiz {
    public List<ReviewedBookVo> findAllReviewedBook();
}
