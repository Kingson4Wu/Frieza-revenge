//http://www.cnblogs.com/sydeveloper/archive/2013/05/14/3078295.html>=


//JQuery的ajax函数的返回类型只有xml、text、json、html等类型，没有“流”类型，所以我们要实现ajax下载，不能够使用相应的ajax函数进行文件下载。但可以用js生成一个form，用这个form提交参数，并返回“流”类型的数据。在实现过程中，页面也没有进行刷新。

var form=$("<form>");//定义一个form表单
form.attr("style","display:none");
form.attr("target","");
form.attr("method","post");
form.attr("action","exportData");
var input1=$("<input>");
input1.attr("type","hidden");
input1.attr("name","exportData");
input1.attr("value",(new Date()).getMilliseconds());
$("body").append(form);//将表单放置在web中
form.append(input1);

form.submit();//表单提交