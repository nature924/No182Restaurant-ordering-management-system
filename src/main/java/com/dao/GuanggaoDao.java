package com.dao;

import com.entity.GuanggaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuanggaoView;

/**
 * 广告 Dao 接口
 *
 * @author 
 */
public interface GuanggaoDao extends BaseMapper<GuanggaoEntity> {

   List<GuanggaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
