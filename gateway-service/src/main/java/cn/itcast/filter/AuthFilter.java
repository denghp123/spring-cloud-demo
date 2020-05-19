package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;//静态导包

/**
 * @Author dhp
 * @Date 2020/5/17 17:54
 * @Version 1.0
 */
@Component
public class AuthFilter extends ZuulFilter {
    /**
     * 过滤器的类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;

    }

    /**
     * 过滤器的顺序 值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER+1;

    }

    /**
     * 过滤器是否执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行过滤逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取上下文（每一次请求都会产生一个上下文容器，在整个反向代理过程中共享）
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = ctx.getRequest();
        //获取请求参数中的值
        String role = request.getParameter("role");
        //判断角色是否是admin
        if (!StringUtils.equals("admin",role)){
            //否 拦截
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        //是 放行

         return null;
    }
}
