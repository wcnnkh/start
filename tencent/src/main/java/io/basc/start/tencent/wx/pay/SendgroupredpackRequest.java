package io.basc.start.tencent.wx.pay;

/**
 * <a href=
 * "https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_5&index=4">文档</a>
 * 
 * @author wcnnkh
 *
 */
public class SendgroupredpackRequest extends SendredpackRequest {
	private static final long serialVersionUID = 1L;
	private AmtType amt_type;

	public AmtType getAmt_type() {
		return amt_type;
	}

	public void setAmt_type(AmtType amt_type) {
		this.amt_type = amt_type;
	}
}
