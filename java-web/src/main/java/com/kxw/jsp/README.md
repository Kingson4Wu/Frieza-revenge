+ jsp页面返回上一个页面
```jsp
<div  style="float:right" >
 		<button class="btn btn-primary" onclick="location.href='javascript:history.go(-1);'" align="right">返回</button>
</div>
```

+ jstl解析bean对象自动解析isXX方法为属性（返回Boolean）（不必为bean添加相应属性，只需is方法）
```com.abc.kxw.product.entity.BrandProduct 

	    public boolean isShow(){
	        return (property & ProductConstants.PROPERTY_BIT_SHOW) ==
	        		BrandProductConstants.PROPERTY_BIT_SHOW;
	    }
	    public boolean isHotSell(){
	        return (property & ProductConstants.PROPERTY_BIT_HOTSELL) ==
	        		BrandProductConstants.PROPERTY_BIT_HOTSELL;
	    }
	    
	    <c:if test= "${product.show=='true'}"
```
+ 开发时浏览器不用手动清空js缓存
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/abc.list.js?v=201411071424"></script>
加个版本号

