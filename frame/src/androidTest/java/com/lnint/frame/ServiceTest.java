package com.lnint.frame;

import android.test.ServiceTestCase;

import com.lnint.frame.service.SystemService;

/**
 * ServiceTestCase，ProviderTestCase2。
 *
 * Created by wangpf on 2017/4/23.
 */

public class ServiceTest extends ServiceTestCase<SystemService> {
    public ServiceTest() {
        super(SystemService.class);
    }
}
