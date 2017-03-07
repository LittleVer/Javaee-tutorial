package com.college.dao;

import com.entity.OrderBook;
import com.entity.custom.AddedBookVo;
import com.entity.custom.ChangedItems;
import com.entity.custom.OrderBookReviewVo;
import com.entity.custom.ReviewedBookVo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-13.
 */
public interface OrderBookDao {
    public void add(OrderBook orderBook);

    public List<AddedBookVo> findAddedBookInfoList(String staffId);

    public int usedByOtherSec(@Param("bookTitle") String bookTitle, @Param("isbn") String isbn, @Param("secId") int secId);

    public void delete(@Param("secId") int secId, @Param("bookTitle") String bookTitle, @Param("isbn") String isbn);

    public List<OrderBookReviewVo> findAllNotReviewedBook();

    public void audit(@Param("secId") int secId, @Param("bookTitle") String bookTitle, @Param("isbn") String isbn);

    public List<ReviewedBookVo> findAllReviewedBook();
}
