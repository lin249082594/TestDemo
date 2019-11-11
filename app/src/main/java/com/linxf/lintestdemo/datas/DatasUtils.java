package com.linxf.lintestdemo.datas;

import com.linxf.lintestdemo.entity.Goods;
import com.linxf.lintestdemo.entity.Image;
import com.linxf.lintestdemo.entity.Mitem;
import com.linxf.lintestdemo.entity.PicItem;
import com.linxf.lintestdemo.entity.SubItem;
import com.linxf.lintestdemo.entity.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public class DatasUtils {

    public static List<TestData> getTestData(int page,int pageSize,int totalCount){
        List<TestData> list = new ArrayList<>();
        if(page <= totalCount){
            for(int i=0;i<pageSize;i++){
                TestData data = new TestData();
                data.setName("测试数据:" + ((page - 1) * pageSize + i));
                list.add(data);
            }
        }

        return list;
    }

    public static List<Goods> getGoodsList(){
        List<Goods> list = new ArrayList<>();
        Goods item = new Goods();
        item.setLogo("http://www.yedujia.com/content/chanpinfengmian/7e193d0e-d30e-4638-93d0-a63c00fd66ac/r_7e193d0e-d30e-4638-93d0-a63c00fd66ac.jpg");
        item.setName("名称");
        list.add(item);
        item = new Goods();
        item.setLogo("http://www.yedujia.com/content/chanpinfengmian/953b0d8e-5384-41f5-828b-a75b00ae96f8/r_953b0d8e-5384-41f5-828b-a75b00ae96f8.jpg");
        item.setName("名称");
        list.add(item);
        item = new Goods();
        item.setLogo("http://www.yedujia.com/content/chanpinfengmian/6f1d5260-b190-4aa6-afb0-a6f501069795/r_6f1d5260-b190-4aa6-afb0-a6f501069795.jpg");
        item.setName("名称");
        list.add(item);

        return list;
    }


    public static List<Image> getImageList(){
        List<Image> list = new ArrayList<>();
        Image item = new Image();
        item.setImage("http://img1.imgtn.bdimg.com/it/u=1393987749,3422146058&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img1.imgtn.bdimg.com/it/u=3372907541,1722073626&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img0.imgtn.bdimg.com/it/u=1495453985,3007952771&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img1.imgtn.bdimg.com/it/u=2581979586,1734657215&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img0.imgtn.bdimg.com/it/u=3464499095,1074840881&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img2.imgtn.bdimg.com/it/u=3293662593,3404637171&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img5.imgtn.bdimg.com/it/u=1239464927,959034636&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img5.imgtn.bdimg.com/it/u=3354195504,2828842281&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img0.imgtn.bdimg.com/it/u=2292227202,3804720640&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img4.imgtn.bdimg.com/it/u=3088039924,1524301738&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img2.imgtn.bdimg.com/it/u=2260937709,2065328859&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img2.imgtn.bdimg.com/it/u=3255742723,2534281391&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img5.imgtn.bdimg.com/it/u=2499402649,1926657896&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img5.imgtn.bdimg.com/it/u=1207238386,2698747171&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img4.imgtn.bdimg.com/it/u=3298060438,2204114002&fm=26&gp=0.jpg");
        list.add(item);
        item = new Image();
        item.setImage("http://img4.imgtn.bdimg.com/it/u=3298060438,2204114002&fm=26&gp=0.jpg");
        list.add(item);

        return list;
    }

    public static List<Mitem> getItemList(){
        List<Mitem> mitemList = new ArrayList<>();
        Mitem mitem = new Mitem();
        mitem.setName("t1");
        List<SubItem> subItemList = new ArrayList<>();
        SubItem subItem = new SubItem();
        subItem.setI(5);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(9);
        subItemList.add(subItem);
        mitem.setSubList(subItemList);
        mitemList.add(mitem);

        mitem = new Mitem();
        mitem.setName("t2");
        subItemList = new ArrayList<>();
        subItem = new SubItem();
        subItem.setI(12);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(18);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(11);
        subItemList.add(subItem);
        mitem.setSubList(subItemList);
        mitemList.add(mitem);

        mitem = new Mitem();
        mitem.setName("t3");
        subItemList = new ArrayList<>();
        subItem = new SubItem();
        subItem.setI(24);
        subItemList.add(subItem);
        mitem.setSubList(subItemList);
        mitemList.add(mitem);


        mitem = new Mitem();
        mitem.setName("t4");
        subItemList = new ArrayList<>();
        subItem = new SubItem();
        subItem.setI(34);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(36);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(38);
        subItemList.add(subItem);
        subItem = new SubItem();
        subItem.setI(39);
        subItemList.add(subItem);
        mitem.setSubList(subItemList);
        mitemList.add(mitem);

        return  mitemList;
    }


    public static List<PicItem> getPicList(){
        List<PicItem> list = new ArrayList<>();
        PicItem item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/06/1565060548762448.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/06/1565060549494743.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010780111141.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010014316185.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010014822653.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010015013317.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010015489028.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565010015891896.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009755775980.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009755960175.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009756126565.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009757328467.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009757500760.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009455376541.jpg,http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009455610791.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565008900850077.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/05/1565009755775980.jpg");
        list.add(item);

        item = new PicItem();
        item.setPic("http://weimindbucket.oss-cn-shanghai.aliyuncs.com/wb/wemind/2019/08/02/1564737860501633.jpg");
        list.add(item);

        return list;
    }

}
