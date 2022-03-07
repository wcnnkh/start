package io.basc.start.tencent.test;

import io.basc.start.tencent.wx.api.GenerateUrlRequest;
import io.basc.start.tencent.wx.api.WeiXinApplet;

public class WeixinAppletTest {

	public static void main(String[] args) {
		GenerateUrlRequest request = new GenerateUrlRequest();
		request.setPath("pages/coupons/index");
		WeiXinApplet applet = new WeiXinApplet(null, null);
		String url = applet.generatescheme(
				"54_8NvYO06iQbxmYZOCCvKDnOOqITQ-turXidJ6zueP-Rzk-ptatNiK2D1c_DjlqA8HG4kKpWA89udst5gUw4QGfVgrkxzBHvusRife8wo_KG4UsuE0hZab3zlmh3t-0xzk8DjnamtrmpeW9q51RNBaADAUQE",
				request);
		System.out.println(url);
	}
}
