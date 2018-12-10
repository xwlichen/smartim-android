package com.smart.im.main.mvp.model;

import android.support.v4.app.Fragment;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.smart.im.main.mvp.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:07
 * @email : 196003945@qq.com
 * @description :Main 页面的model提供，fragment
 */
public class MainModel extends BaseModel implements MainContract.Model {

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<Fragment> getFragments() {
        return null;
    }

}
