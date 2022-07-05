package com.moodleeducation.commoncore.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageUtils<T extends Serializable> implements Serializable {
    private static final long serialVersionUUID = -5764853545343945831L;

    private List<T> list = null;

    private int totalCount=0;

    private int totalPage =0;

    private int pageCurrent =1;

    private int pageSize = 20;

    @Override
    public String toString() {
        return "Page [list=" + list + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", pageCurrent=" + pageCurrent + ", pageSize=" + pageSize + "]";
    }
}
