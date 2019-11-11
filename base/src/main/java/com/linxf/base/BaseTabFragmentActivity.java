//package com.linxf.base;
//
//import android.support.v4.app.FragmentTransaction;
//
//import com.linxf.base.controller.PageRefreshController;
//import com.linxf.base.model.FragmentTab;
//import com.linxf.base.utils.StringUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 类说明
// * Created by Linxf on 2019/3/27.
// */
//
//public abstract class BaseTabFragmentActivity extends BaseActivity {
//    public List<FragmentTab> fragmentTabList = new ArrayList<>();
//    private BaseFragment[] fragments = null;
//    private int contentId;
//    public void initFragment(int contentId){
//
//        if(StringUtil.isEmpty(fragmentTabList))
//            return;
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        fragments = new BaseFragment[fragmentTabList.size()];
//        this.contentId = contentId;
//
//    }
//
//    /**
//     * 设置tab选中
//     * @param pos
//     */
//    public void setTabSelecion(int pos){
//        if(pos<0|| pos>=fragments.length)
//            return;
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideFragments(transaction);
//        if(fragments!=null && pos<fragments.length){
//            if(fragments[pos] == null){
//                BaseFragment fragment = createFragment(pos);
//                if(fragment != null){
//                    transaction.add(contentId,fragment);
//                    fragments[pos] =fragment;
//                }
//
//            } else {
//                transaction.show(fragments[pos]);
//            }
//
//        }
//        transaction.commit();
//    }
//
//    public int getPosByMenuId(int menuId){
//        if(StringUtil.isEmpty(fragmentTabList) || menuId <= 0)
//            return -1;
//        for(int i=0;i<fragmentTabList.size();i++){
//            if(fragmentTabList.get(i).getMenuId() == menuId){
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * 隐藏tab选中
//     * @param transaction
//     */
//    private void hideFragments(FragmentTransaction transaction){
//       if(fragments == null || fragments.length == 0)
//        return;
//        for(BaseFragment fragment:fragments){
//            if(fragment != null)
//            transaction.hide(fragment);
//        }
//
//
//    }
//
//    private BaseFragment createFragment(int pos){
//        BaseFragment fragment = null;
//        try {
//            fragment = (BaseFragment) fragmentTabList.get(pos).getClazz().newInstance();
//        } catch (Exception e){
//
//        }
//        return fragment;
//    }
//
//
////    for (FragmentTab tab : fragmentTabList) {
////        try {
////            BaseFragment fragment = null;
////
////            List<Fragment> fs =  getSupportFragmentManager().getFragments();
////            if (fs != null) {
////                for (Fragment f : fs) {
////                    if (f.getClass() == tab.getClazz()) {
////                        fragment = (BaseFragment) f;
////                        break;
////                    }
////                }
////            }
////
////            if (fragment == null) {
////                fragment = (BaseFragment) tab.getClazz().newInstance();
////                transaction.add(contentId,fragment);
////            }
////
////            fragments[tab.getTabIndex()] = fragment;
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
////    }
//}
