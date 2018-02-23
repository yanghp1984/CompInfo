package common.interceptor;

import common.constant.GlobalConstant;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import sysmgt.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户登录拦截器
 *
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 配置不需要登录验证的URI列表
     */
    private List<String> excludeLoginUriList;

    private PathMatcher pathMatcher = new AntPathMatcher();

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preHandler 调用 controller 具体方法之前

        // 用户请求的URI
        String uri = urlPathHelper.getLookupPathForRequest(request);

        // 判断当前请求是否需要拦截
        if (!CollectionUtils.isEmpty(excludeLoginUriList)) {
            for (String excludeUri : excludeLoginUriList) {
                if (pathMatcher.match(excludeUri, uri)) {
                    return true;
                }
            }
        }

        // 用户登录验证
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute(GlobalConstant.LOGIN_USER_KEY);
        if (userEntity == null) {
            request.setAttribute(GlobalConstant.ERROR_MSG_KEY, "用户未登录");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // postHandle完成具体方法之后调用
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // afterCompletion完成对页面的render以后调用，至此整个页面渲染完成
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 获取不需要登录验证的URI列表
     *
     * @return 不需要登录验证的URI列表
     */
    public List<String> getExcludeLoginUriList() {
        return excludeLoginUriList;
    }

    /**
     * 设置不需要登录验证的URI列表
     *
     * @param excludeLoginUriList 不需要登录验证的URI列表
     */
    public void setExcludeLoginUriList(List<String> excludeLoginUriList) {
        this.excludeLoginUriList = excludeLoginUriList;
    }
}
