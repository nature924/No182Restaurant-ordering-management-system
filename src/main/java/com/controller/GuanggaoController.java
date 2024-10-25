
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 广告
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guanggao")
public class GuanggaoController {
    private static final Logger logger = LoggerFactory.getLogger(GuanggaoController.class);

    private static final String TABLE_NAME = "guanggao";

    @Autowired
    private GuanggaoService guanggaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//菜品资讯
    @Autowired
    private ShangjiaService shangjiaService;//店家
    @Autowired
    private ShangpinService shangpinService;//商品
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//商品收藏
    @Autowired
    private ShangpinCommentbackService shangpinCommentbackService;//商品评价
    @Autowired
    private ShangpinOrderService shangpinOrderService;//商品订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("店家".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        params.put("guanggaoDeleteStart",1);params.put("guanggaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = guanggaoService.queryPage(params);

        //字典表数据转换
        List<GuanggaoView> list =(List<GuanggaoView>)page.getList();
        for(GuanggaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuanggaoEntity guanggao = guanggaoService.selectById(id);
        if(guanggao !=null){
            //entity转view
            GuanggaoView view = new GuanggaoView();
            BeanUtils.copyProperties( guanggao , view );//把实体数据重构到view中
            //级联表 店家
            //级联表
            ShangjiaEntity shangjia = shangjiaService.selectById(guanggao.getShangjiaId());
            if(shangjia != null){
            BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "shangjiaId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShangjiaId(shangjia.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GuanggaoEntity guanggao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guanggao:{}",this.getClass().getName(),guanggao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("店家".equals(role))
            guanggao.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<GuanggaoEntity> queryWrapper = new EntityWrapper<GuanggaoEntity>()
            .eq("shangjia_id", guanggao.getShangjiaId())
            .eq("guanggao_name", guanggao.getGuanggaoName())
            .eq("guanggao_types", guanggao.getGuanggaoTypes())
            .eq("guanggao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuanggaoEntity guanggaoEntity = guanggaoService.selectOne(queryWrapper);
        if(guanggaoEntity==null){
            guanggao.setGuanggaoClicknum(1);
            guanggao.setGuanggaoDelete(1);
            guanggao.setInsertTime(new Date());
            guanggao.setCreateTime(new Date());
            guanggaoService.insert(guanggao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuanggaoEntity guanggao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guanggao:{}",this.getClass().getName(),guanggao.toString());
        GuanggaoEntity oldGuanggaoEntity = guanggaoService.selectById(guanggao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("店家".equals(role))
//            guanggao.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(guanggao.getGuanggaoPhoto()) || "null".equals(guanggao.getGuanggaoPhoto())){
                guanggao.setGuanggaoPhoto(null);
        }

            guanggaoService.updateById(guanggao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuanggaoEntity> oldGuanggaoList =guanggaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<GuanggaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            GuanggaoEntity guanggaoEntity = new GuanggaoEntity();
            guanggaoEntity.setId(id);
            guanggaoEntity.setGuanggaoDelete(2);
            list.add(guanggaoEntity);
        }
        if(list != null && list.size() >0){
            guanggaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<GuanggaoEntity> guanggaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            GuanggaoEntity guanggaoEntity = new GuanggaoEntity();
//                            guanggaoEntity.setShangjiaId(Integer.valueOf(data.get(0)));   //店家 要改的
//                            guanggaoEntity.setGuanggaoName(data.get(0));                    //广告标题 要改的
//                            guanggaoEntity.setGuanggaoPhoto("");//详情和图片
//                            guanggaoEntity.setGuanggaoTypes(Integer.valueOf(data.get(0)));   //广告类型 要改的
//                            guanggaoEntity.setGuanggaoClicknum(Integer.valueOf(data.get(0)));   //广告热度 要改的
//                            guanggaoEntity.setGuanggaoContent("");//详情和图片
//                            guanggaoEntity.setGuanggaoDelete(1);//逻辑删除字段
//                            guanggaoEntity.setInsertTime(date);//时间
//                            guanggaoEntity.setCreateTime(date);//时间
                            guanggaoList.add(guanggaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        guanggaoService.insertBatch(guanggaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = guanggaoService.queryPage(params);

        //字典表数据转换
        List<GuanggaoView> list =(List<GuanggaoView>)page.getList();
        for(GuanggaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuanggaoEntity guanggao = guanggaoService.selectById(id);
            if(guanggao !=null){

                //点击数量加1
                guanggao.setGuanggaoClicknum(guanggao.getGuanggaoClicknum()+1);
                guanggaoService.updateById(guanggao);

                //entity转view
                GuanggaoView view = new GuanggaoView();
                BeanUtils.copyProperties( guanggao , view );//把实体数据重构到view中

                //级联表
                    ShangjiaEntity shangjia = shangjiaService.selectById(guanggao.getShangjiaId());
                if(shangjia != null){
                    BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangjiaId(shangjia.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody GuanggaoEntity guanggao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guanggao:{}",this.getClass().getName(),guanggao.toString());
        Wrapper<GuanggaoEntity> queryWrapper = new EntityWrapper<GuanggaoEntity>()
            .eq("shangjia_id", guanggao.getShangjiaId())
            .eq("guanggao_name", guanggao.getGuanggaoName())
            .eq("guanggao_types", guanggao.getGuanggaoTypes())
            .eq("guanggao_clicknum", guanggao.getGuanggaoClicknum())
            .eq("guanggao_delete", guanggao.getGuanggaoDelete())
//            .notIn("guanggao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuanggaoEntity guanggaoEntity = guanggaoService.selectOne(queryWrapper);
        if(guanggaoEntity==null){
            guanggao.setGuanggaoClicknum(1);
            guanggao.setGuanggaoDelete(1);
            guanggao.setInsertTime(new Date());
            guanggao.setCreateTime(new Date());
        guanggaoService.insert(guanggao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

