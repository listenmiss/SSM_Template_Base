package cn.com.catcher.vision.affairs.commons.interceptor;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.catcher.vision.affairs.commons.constants.Constant;
import cn.com.catcher.vision.affairs.commons.model.CheckResult;
import cn.com.catcher.vision.affairs.commons.utils.JwtUtil;
import cn.com.catcher.vision.affairs.commons.utils.ResponseUtil;
import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;

/**
 * Created by ThinkPad on 2017/6/20.
 */
public class TokenValidateInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(TokenValidateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       
    	String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			PrintWriter printWriter = response.getWriter();
			printWriter.print(ResponseUtil.noLogin());
			printWriter.flush();
			printWriter.close();
			return false;
        }
		
		String tokenStr = authorizationHeader.substring("Bearer".length()).trim();
//    	String tokenStr = request.getParameter("token");
		if (tokenStr == null || tokenStr.equals("")) {
			PrintWriter printWriter = response.getWriter();
			printWriter.print(ResponseUtil.noLogin());
			printWriter.flush();
			printWriter.close();
			return false;
		}
		// 验证JWT的签名，返回CheckResult对象
		CheckResult checkResult = JwtUtil.validateJWT(tokenStr);
		if (checkResult.isSuccess()) {
			Claims claims = checkResult.getClaims();
			logger.info("token校检通过checkResult："+JSONObject.fromObject(checkResult).toString());
			logger.info("token校检通过user："+JSONObject.fromObject(claims.getSubject()).toString());
			return true;
		} else {
			switch (checkResult.getErrCode()) {
			// 签名过期，返回过期提示码
			case Constant.JWT_ERRCODE_EXPIRE:
				PrintWriter printWriter = response.getWriter();
				printWriter.print(ResponseUtil.loginExpire());
				printWriter.flush();
				printWriter.close();
				break;
			// 签名验证不通过
			case Constant.JWT_ERRCODE_FAIL:
				PrintWriter printWriter2 = response.getWriter();
				printWriter2.print(ResponseUtil.noAuth());
				printWriter2.flush();
				printWriter2.close();
				break;
			default:
				break;
			}
			return false;
		}
		
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}