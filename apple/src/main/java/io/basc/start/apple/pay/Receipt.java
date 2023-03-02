package io.basc.start.apple.pay;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;
import io.basc.framework.util.CollectionUtils;

import java.util.List;

/**
 * <a href=
 * "https://developer.apple.com/documentation/appstorereceipts/responsebody/receipt">文档</a>
 * 
 * @author wcnnkh
 *
 */
public class Receipt extends JsonObjectWrapper {

	public Receipt(JsonObject target) {
		super(target);
	}

	/**
	 * 请参阅。app_item_id
	 * 
	 * @return app_item_id
	 */
	public long getAdamId() {
		return getAsLong("adam_id");
	}

	/**
	 * 由App Store Connect生成，并由App Store用于唯一标识购买的应用。仅在生产中为应用程序分配此标识符。将此值视为64位长整数。
	 * 
	 * @return 唯一标识购买的应用
	 */
	public long getAppItemId() {
		return getAsLong("app_item_id");
	}

	/**
	 * 应用程序的版本号。该应用程序的版本号对应于（在iOS中）或（在macOS中）中的值。在生产中，此值为基于的设备上应用程序的当前版本。在沙盒中，该值始终为。CFBundleVersionCFBundleShortVersionStringInfo.plistreceipt_creation_date_ms"1.0"
	 * 
	 * @return 应用程序的版本号
	 */
	public String getApplicationVersion() {
		return getAsString("application_version");
	}

	/**
	 * 收据所属应用的捆绑标识符。您在App Store
	 * Connect上提供此字符串。这相当于价值中的应用程序的文件。CFBundleIdentifierInfo.plist
	 * 
	 * @return 收据所属应用的捆绑标识符
	 */
	public String getBundleId() {
		return getAsString("bundle_id");
	}

	/**
	 * 应用下载交易的唯一标识符。
	 * 
	 * @return 应用下载交易的唯一标识符。
	 */
	public int getDownloadId() {
		return getAsInt("download_id");
	}

	/**
	 * 通过批量购买计划购买的应用程序的收据过期时间.
	 * 
	 * @return 通过批量购买计划购买的应用程序的收据过期时间
	 */
	public ApplePayDate getExpirationDate() {
		return new ApplePayDate(this, "expiration_date");
	}

	/**
	 * 包含所有应用内购买交易的应用内购买收据字段的数组。 以根据purchase_date升序排列
	 * 
	 * @return 包含所有应用内购买交易的应用内购买收据字段的数组
	 */
	public List<InApp> getInApps() {
		return InApp.parse(getJsonArray("in_app"), InApp.class);
	}

	/**
	 * 用户最初购买的应用程序的版本。该值不变，并且与原始购买文件中的（在iOS中）或String（在macOS中）的值相对应。在沙盒环境中，该值始终为。CFBundleVersionCFBundleShortVersionInfo.plist"1.0"
	 * 
	 * @return 用户最初购买的应用程序的版本
	 */
	public String getOriginalApplicationVersion() {
		return getAsString("original_application_version");
	}

	/**
	 * 原始应用购买时间
	 * 
	 * @return 原始应用购买时间
	 */
	public ApplePayDate getOriginalPurchaseDate() {
		return new ApplePayDate(this, "original_purchase_date");
	}

	/**
	 * 用户订购可用于预订的应用的时间
	 * 
	 * @return 用户订购可用于预订的应用的时间
	 */
	public ApplePayDate getPreorderDate() {
		return new ApplePayDate(this, "preorder_date");
	}

	/**
	 * App Store生成收据的时间
	 * 
	 * @return 生成收据的时间
	 */
	public ApplePayDate getReceiptCreationDate() {
		return new ApplePayDate(this, "receipt_creation_date");
	}

	/**
	 * 生成的收据类型。该值对应于购买应用程序或VPP的环境。可能的值： Production, ProductionVPP,
	 * ProductionSandbox, ProductionVPPSandbox
	 * 
	 * @return 生成的收据类型
	 */
	public String getReceiptType() {
		return getAsString("receipt_type");
	}

	/**
	 * 对端点的请求并生成响应的时间
	 * 
	 * @return 响应的时间
	 */
	public ApplePayDate getRequestDate() {
		return new ApplePayDate(this, "request_date");
	}

	/**
	 * 标识应用程序修订版的任意数字。在沙盒中，此键的值为“0”。
	 * 
	 * @return 标识应用程序修订版的任意数字
	 */
	public int getVersionExternalIdentifier() {
		return getAsInt("version_external_identifier");
	}

	/**
	 * 获取凭据的第一个product_id
	 * 
	 * @return 获取凭据的第一个product_id
	 */
	public String getProductId() {
		List<InApp> inApp = getInApps();
		if (!CollectionUtils.isEmpty(inApp)) {
			return inApp.get(inApp.size() - 1).getProductId();
		}

		// ios7之前的
		return getAsString("product_id");
	}
}
