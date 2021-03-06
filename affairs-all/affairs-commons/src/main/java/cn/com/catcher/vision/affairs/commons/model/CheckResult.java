package cn.com.catcher.vision.affairs.commons.model;

import io.jsonwebtoken.Claims;

/**
 * 验证结果模型
 * @author xuxile
 *
 */
public class CheckResult {

	private int errCode;
	
	private boolean success;
	
	private Claims claims;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}
	
}