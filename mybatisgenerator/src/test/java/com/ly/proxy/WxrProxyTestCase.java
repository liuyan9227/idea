package com.ly.proxy;

import com.ly.entity.Dell;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author liuyan
 * @date 2018/7/16
 */
public class WxrProxyTestCase {

    @Test
    public void testWxrProxy(){
        // 获取代理模版对象
        WxrProxy wxrProxy = new WxrProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dell.class);
        enhancer.setCallback(wxrProxy);

        Dell res = (Dell) enhancer.create();
        res.salePC("max");
    }
}
