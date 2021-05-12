$(function() {
	let shopId = getQueryString('shopId');
	const shopInfoUrl = '/o2o/shopadmin/getshopmanagementinfo?shopId=' + shopId;
	$.getJSON(shopInfoUrl, function(data) {
		if (data.redirect) {
			//如果需要重定向
			window.location.href = data.url;
		} else {
			//检测id是否为空
			if (data.shopId !== undefined && data.shopId != null) {
				shopId = data.shopId;
			}
			$('#shopInfo')
					.attr('href', '/o2o/shopadmin/shopoperation?shopId=' + shopId);
		}
	});
});