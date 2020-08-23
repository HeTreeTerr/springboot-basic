package com.hss.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.hss.bean.User;
import com.hss.util.Msg;
import com.hss.util.RedisUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RedisSessionInterceptor implements HandlerInterceptor {

    /*@Autowired
    public static StringRedisTemplate redisTemplate;*/

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session = request.getSession();
        User userSignInfo = (User)session.getAttribute("userSession");
        if (userSignInfo != null && userSignInfo.getId() != null)
        {
            try
            {
                //验证当前请求的session是否是已登录的session
                String loginSessionId = RedisUtil.get("spring:session:loginUser:" + userSignInfo.getId());
                if (loginSessionId != null && loginSessionId.equals(session.getId())){
                    return true;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try{
            response.getWriter().print(JSON.toJSONString(Msg.notLogin()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{

    }


}
