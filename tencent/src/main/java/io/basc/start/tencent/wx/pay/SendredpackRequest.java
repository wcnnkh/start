package io.basc.start.tencent.wx.pay;

import io.basc.framework.lang.Nullable;
import lombok.Data;

import java.io.Serializable;

/**
 * <a href=
 * "https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3">文档</a>
 * 
 * @author wcnnkh
 *
 */
@Data
public class SendredpackRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户订单号（每个订单号必须唯一。取值范围：0~9，a~z，A~Z）接口根据商户订单号支持重入，如出现超时可再调用。
	 */
	private String mch_billno;
	/**
	 * 微信分配的公众账号ID（企业号corpid即为此appId）。在微信开放平台（open.weixin.qq.com）申请的移动应用appid无法使用该接口。
	 */
	private String wxappid;
	/**
	 * 红包发送者名称 注意：敏感词会被转义成字符*
	 */
	private String send_name;
	/**
	 * openid为用户在wxappid下的唯一标识
	 */
	private String re_openid;
	/**
	 * 付款金额，单位分
	 */
	private int total_amount;
	/**
	 * 红包发放总人数
	 */
	private int total_num;
	/**
	 * 红包祝福语 注意：敏感词会被转义成字符*
	 */
	private String wishing;
	/**
	 * 调用接口的机器Ip地址
	 */
	private String client_ip;
	/**
	 * 活动名称 注意：敏感词会被转义成字符*
	 */
	private String act_name;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 发放红包使用场景，红包金额大于200或者小于1元时必传 PRODUCT_1:商品促销 PRODUCT_2:抽奖 PRODUCT_3:虚拟物品兑奖
	 * PRODUCT_4:企业内部福利 PRODUCT_5:渠道分润 PRODUCT_6:保险回馈 PRODUCT_7:彩票派奖 PRODUCT_8:税务刮奖
	 */
	@Nullable
	private String scene_id;
	/**
	 * 活动信息
	 * 
	 * posttime:用户操作的时间戳 mobile:业务系统账号的手机号，国家代码-手机号。不需要+号 deviceid :mac 地址或者设备唯一标识
	 * clientversion :用户操作的客户端版本把值为非空的信息用key=value进行拼接，再进行
	 * {@code urlencode(posttime=xx&mobile=xx&deviceid=xx)}
	 */
	@Nullable
	private String risk_info;
}
